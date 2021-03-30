package com.arjunkoottalasajayan.blogservice.domain.util;

public enum ResponseMessage {
    UPDATED_SUCCESSFULLY("Successfully Updated"),
    DELETED_SUCCESSFULLY("Deleted Updated");

    private final String message;

    ResponseMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
