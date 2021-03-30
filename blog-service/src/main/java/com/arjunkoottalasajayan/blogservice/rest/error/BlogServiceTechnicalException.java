package com.arjunkoottalasajayan.blogservice.rest.error;

public class BlogServiceTechnicalException extends RuntimeException {

    private final ErrorCode error;

    private final Exception exception;

    public BlogServiceTechnicalException(ErrorCode error, String message, Exception ex) {
        super(message);
        this.error = error;
        this.exception = ex;
    }

    public Exception getException() {
        return exception;
    }

    public ErrorCode getError() {
        return error;
    }

}
