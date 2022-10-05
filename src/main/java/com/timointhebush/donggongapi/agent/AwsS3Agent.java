package com.timointhebush.donggongapi.agent;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.timointhebush.donggongapi.service.AwsS3BucketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
public class AwsS3Agent {
    private static final String DEFAULT_CACHE_CONTROL = "public, max-age=5184000";
    private static final String DEFAULT_CONTENT_DISPOSITION = "attachment";
    private final AwsS3BucketService awsS3BucketService;
    private final ConcurrentHashMap<String, AmazonS3> amazonS3Map;

    public AwsS3Agent(AwsS3BucketService awsS3BucketService) {
        this.awsS3BucketService = awsS3BucketService;
        amazonS3Map = new ConcurrentHashMap<>();
    }

    public String sanitize(String key) {
        return key.contains(File.separator) ? key.replace(File.separator, "/") : key;
    }

    public ObjectMetadata createObjectMetadata(long contentLength, @Nullable String contentType) {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setCacheControl(DEFAULT_CACHE_CONTROL);
        objectMetadata.setContentDisposition(DEFAULT_CONTENT_DISPOSITION);
        objectMetadata.setContentLength(contentLength);
        if (contentType != null)
            objectMetadata.setContentType(contentType);
        return objectMetadata;
    }

    private AmazonS3 getAmazonS3(String bucket) {
        if (!amazonS3Map.containsKey(bucket)) {
            final AWSCredentials credentials = new BasicAWSCredentials(
                    awsS3BucketService.getAccessKey(),
                    awsS3BucketService.getSecretKey()
            );
            amazonS3Map.putIfAbsent(
                    bucket,
                    AmazonS3ClientBuilder.standard()
                            .withCredentials(new AWSStaticCredentialsProvider(credentials))
                            .withRegion(Regions.fromName(awsS3BucketService.getRegion()))
                            .build()
            );
        }
        return amazonS3Map.get(bucket);
    }

    public String putObjectRequest(String bucket, String key, PutObjectRequest putObjectRequest) {
        log.info("AWS_:S3__:UPLD:: AWS S3 put ObjectRequest to bucket - {}", bucket);
        final AmazonS3 amazonS3 = getAmazonS3(bucket);
        amazonS3.putObject(putObjectRequest);
        return amazonS3.getUrl(bucket, key).toString();
    }

    public String uploadStream(ByteArrayInputStream is, long contentLength, String key, String contentType) {
        log.info("AWS_:S3__:UPLD:: AWS S3 Stream 업로드 - {}", key);
        key = sanitize(key);
        String bucket = awsS3BucketService.getBucket();
        is.reset();
        PutObjectRequest putObjectRequest = new PutObjectRequest(
                bucket,
                key,
                is,
                createObjectMetadata(contentLength, contentType)
        );
        return putObjectRequest(bucket, key, putObjectRequest);
    }
}
