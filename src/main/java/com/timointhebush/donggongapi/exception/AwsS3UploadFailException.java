package com.timointhebush.donggongapi.exception;

public class AwsS3UploadFailException extends RuntimeException {
    public AwsS3UploadFailException(String message) {
        super(message);
    }
}

