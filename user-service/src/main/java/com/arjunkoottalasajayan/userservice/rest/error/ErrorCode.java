package com.arjunkoottalasajayan.userservice.rest.error;

import com.google.common.base.MoreObjects;

public enum ErrorCode {
    INVALID_USER_ID("CS-INVALID-USER", "The userid provided doesnt exist"),
    BLOG_NOT_FOUND("CS-INVALID-BLOG-ID", "Provided blog doesnt exist"),
    BLOG_ALREADY_EXIST("CS-BLOG-EXIST", "Provided blog id exist. Please use a new id"),
    WRONG_OWNER("CS-NOT-OWNER", "Sorry! Only the created user can edit blog"),
    INTERNAL_SERVER_ERROR("CS-INTERNAL-SERVER-ERROR", "Internal Server Error");

    private final String code;
    private final String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("code", code)
                .add("message", message)
                .toString();
    }
}
