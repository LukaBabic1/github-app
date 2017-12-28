package com.undabot.babic.domain.utils.exception;

public final class UnimplementedMethodException extends RuntimeException {

    public UnimplementedMethodException() {

    }

    public UnimplementedMethodException(final String message) {
        super(message);
    }
}
