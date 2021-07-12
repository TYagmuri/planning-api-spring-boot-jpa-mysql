package com.hugs4bugs.core.exceptions;

public class TimeTableExistsInStudentException extends RuntimeException {

    public TimeTableExistsInStudentException() {
    }

    public TimeTableExistsInStudentException(String message) {
        super(message);
    }

    public TimeTableExistsInStudentException(String message, Throwable cause) {
        super(message, cause);
    }

}
