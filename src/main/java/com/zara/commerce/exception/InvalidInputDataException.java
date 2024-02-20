package com.zara.commerce.exception;

import java.io.Serial;

/**
 * Exception to indicate data input data is incorrect
 */
public class InvalidInputDataException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

    public InvalidInputDataException(String message) {
        super(message);
    }
}
