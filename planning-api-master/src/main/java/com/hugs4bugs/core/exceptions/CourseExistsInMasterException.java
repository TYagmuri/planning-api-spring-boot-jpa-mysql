package com.hugs4bugs.core.exceptions;

public class CourseExistsInMasterException extends RuntimeException {

    public CourseExistsInMasterException() {
    }

    public CourseExistsInMasterException(String message) {
        super(message);
    }

    public CourseExistsInMasterException(String message, Throwable cause) {
        super(message, cause);
    }

}
