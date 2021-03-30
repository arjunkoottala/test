package com.arjunkoottalasajayan.comment.rest.error;

public class CommentServiceBusinessException extends RuntimeException {

    private final ErrorCode error;

    private final Exception exception;

    public CommentServiceBusinessException(ErrorCode error, String message, Exception ex) {
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
