package com.zara.commerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

/**
 * Exception to indicate data input data is incorrect
 */
@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="Input Data Error")
public class InvalidInputDataException extends Exception{

    @Serial
    private static final long serialVersionUID = 1L;

    public InvalidInputDataException(String message) {
        super(message);
    }
}
