package com.timointhebush.donggongapi.performance.service;

import com.amazonaws.SdkClientException;
import com.timointhebush.donggongapi.aws.agent.AwsS3Agent;
import com.timointhebush.donggongapi.aws.exception.AwsS3UploadFailException;
import com.timointhebush.donggongapi.file.service.FilePathService;
import com.timointhebush.donggongapi.performance.model.Performance;
import com.timointhebush.donggongapi.performance.model.PerformanceFile;
import com.timointhebush.donggongapi.performance.model.PerformanceFileCreateInput;
import com.timointhebush.donggongapi.performance.repository.PerformanceFileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class PerformanceFileService {
    private final PerformanceFileRepository performanceFileRepository;
    private final FilePathService filePathService;
    private final AwsS3Agent awsS3Agent;

    public PerformanceFile create(PerformanceFileCreateInput input, Performance performance) {
        PerformanceFile performanceFile = storeFile(input, performance);
        return performanceFileRepository.save(performanceFile);
    }

    private PerformanceFile storeFile(PerformanceFileCreateInput input, Performance performance) {
        try {
            String key = FilePathService.generateFileKey();
            String s3Path = filePathService.makeRelativePath(input.getType(), key, input.getFileName());
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(input.getFileStream().readAllBytes());
            String url = awsS3Agent.uploadStream(
                    byteArrayInputStream,
                    input.getFileSize(),
                    s3Path,
                    input.getContentType()
            );
            log.info("PFMC:FILE:UPLD:: Uploaded file to S3: {}", url);
            return PerformanceFile.builder()
                    .performance(performance)
                    .key(key)
                    .name(input.getFileName())
                    .type(input.getType())
                    .fileSize(input.getFileSize())
                    .filePath(s3Path)
                    .build();
        } catch (IOException e) {
            throw new AwsS3UploadFailException(e.getMessage() + " : file stream을 읽는데 실패했습니다.");
        } catch (SdkClientException e) {
            throw new AwsS3UploadFailException(e.getMessage() + " : S3 업로드에 실패했습니다.");
        }
    }
}
