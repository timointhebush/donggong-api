package com.timointhebush.donggongapi.auth.exception;

public class UnknownProviderException extends RuntimeException{
    public UnknownProviderException(String message) {
        super(message);
    }
}
