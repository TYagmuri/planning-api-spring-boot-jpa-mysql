package com.hugs4bugs.core.exceptions;

public class TimeTableBellExistsException extends RuntimeException {

    public TimeTableBellExistsException() {
    }

    public TimeTableBellExistsException(String message) {
        super(message);
    }

    public TimeTableBellExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
