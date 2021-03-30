package com.arjunkoottalasajayan.comment.domain.util;

public enum ReactionResponses {
    LIKED("Successfully liked"),
    DISLIKE("Successfully disliked"),
    NOREACTION("Not Reacted");

    private final String message;

    ReactionResponses(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
