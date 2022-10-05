package com.timointhebush.donggongapi.service;

import com.timointhebush.donggongapi.model.enums.FileType;
import com.timointhebush.donggongapi.util.FilePathUtils;
import com.timointhebush.donggongapi.util.CustomStringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.nio.file.Path;

@Slf4j
@Service
public class FilePathService {
    private static final int FILE_KEY_LENGTH = 15;

    private final Path storageBasePath;

    public FilePathService(@Value(value = "{environment.local-storage-home}") String localStorageHome) {
        this.storageBasePath = Path.of(localStorageHome).toAbsolutePath().normalize();
    }

    public static String generateFileKey() {
        return CustomStringUtils.generateRandomString(FILE_KEY_LENGTH);
    }

    public String makeRelativePath(FileType fileType, String uniqueValue, String fileName) {
        return Path.of(
                fileType.name().toLowerCase(),
                FilePathUtils.generateCurrentDateTimePath().toString(),
                uniqueValue,
                CustomStringUtils.replaceWhiteSpaceAndSpecialChar(fileName)
        ).toString();
    }

    public Path makeAbsolutePath(String relativePath) {
        if (!StringUtils.hasText(relativePath)) {
            return null;
        } else if (relativePath.startsWith("/")) {
            log.warn("FILE:PATH:____ :: 비정상적인 상대경로 입니다. : {}", relativePath);
            return Path.of(relativePath);
        } else {
            return storageBasePath.resolve(relativePath);
        }
    }
}
