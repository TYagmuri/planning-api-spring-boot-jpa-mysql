package com.hugs4bugs.rest.exceptionHandler;

import com.hugs4bugs.core.exceptions.CourseExistsInMasterException;
import com.hugs4bugs.core.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> GlobalHandleExceptions(Exception exception) {
        ErrorResponse response = new ErrorResponse(exception.getMessage(), HttpStatus.BAD_REQUEST, ZonedDateTime.now());
        exception.printStackTrace();
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleCourseExistsInMasterException(CourseExistsInMasterException exception) {
        ErrorResponse response = new ErrorResponse(exception.getMessage(), HttpStatus.BAD_REQUEST, ZonedDateTime.now());
        exception.printStackTrace();
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException exception) {
        ErrorResponse response = new ErrorResponse(exception.getMessage(), HttpStatus.NOT_FOUND, ZonedDateTime.now());
        exception.printStackTrace();
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleAccessDeniedException(AccessDeniedException exception) {
        ErrorResponse response = new ErrorResponse(exception.getMessage(), HttpStatus.valueOf(403), ZonedDateTime.now());
        exception.printStackTrace();
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

}
