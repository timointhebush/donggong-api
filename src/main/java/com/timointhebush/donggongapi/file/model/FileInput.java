package com.timointhebush.donggongapi.file.model;

import com.timointhebush.donggongapi.file.exception.FileProcessingException;
import lombok.Getter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Getter
public class FileInput {
    @ToString.Exclude
    protected InputStream fileStream;
    protected Long fileSize;
    protected String fileName;
    protected String contentType;

    public void setFile(MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                this.fileStream = file.getInputStream();
                this.fileSize = file.getSize();
                this.fileName = file.getOriginalFilename();
                this.contentType = file.getContentType();
            } catch (IOException e) {
                throw new FileProcessingException(e.getMessage() + " : file stream을 읽는데 실패했습니다.");
            }
        }
    }
}
