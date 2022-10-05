package com.timointhebush.donggongapi.aws.exception;

public class AwsS3UploadFailException extends RuntimeException {
    public AwsS3UploadFailException(String message) {
        super(message);
    }
}

