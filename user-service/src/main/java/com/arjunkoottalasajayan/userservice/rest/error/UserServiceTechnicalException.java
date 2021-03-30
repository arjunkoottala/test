package com.arjunkoottalasajayan.userservice.rest.error;

public class UserServiceTechnicalException extends RuntimeException {

    private final ErrorCode error;

    private final Exception exception;

    public UserServiceTechnicalException(ErrorCode error, String message, Exception ex) {
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
