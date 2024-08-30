package com.an.Enrollment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EnrollmentNotFound extends RuntimeException {
    public EnrollmentNotFound(String message) {

        super(message);
    }
}
