package com.arjunkoottalasajayan.blogservice.rest.error;

public class BlogServiceBusinessException extends RuntimeException {

    private final ErrorCode error;

    private final Exception exception;

    public BlogServiceBusinessException(ErrorCode error, String message, Exception ex) {
        super(message);
        this.error = error;
        this.exception = ex;
    }

    public BlogServiceBusinessException(ErrorCode error) {
        super(error.getMessage());
        this.error = error;
        this.exception = null;
    }

    public Exception getException() {
        return exception;
    }

    public ErrorCode getError() {
        return error;
    }

}
