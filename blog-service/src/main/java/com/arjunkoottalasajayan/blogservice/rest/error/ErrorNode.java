package com.arjunkoottalasajayan.blogservice.rest.error;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize
public class ErrorNode implements Serializable {

    @JsonIgnore
    private ErrorCode error;
    private String message;

    private ErrorNode() {
    }

    public ErrorNode(ErrorCode error, String message) {
        this();
        this.message = message;
        this.error = error;
    }
}
