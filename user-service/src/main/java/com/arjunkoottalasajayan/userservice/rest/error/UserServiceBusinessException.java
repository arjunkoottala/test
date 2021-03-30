package com.arjunkoottalasajayan.userservice.rest.error;

public class UserServiceBusinessException extends RuntimeException {

    private final ErrorCode error;

    private final Exception exception;

    public UserServiceBusinessException(ErrorCode error, String message, Exception ex) {
        super(message);
        this.error = error;
        this.exception = ex;
    }

    public UserServiceBusinessException(ErrorCode error) {
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
