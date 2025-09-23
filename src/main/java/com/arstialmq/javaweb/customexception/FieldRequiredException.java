package com.arstialmq.javaweb.customexception;

public class FieldRequiredException extends RuntimeException {

    public FieldRequiredException(String message) {
        super(message);
    }
}
