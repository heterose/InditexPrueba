package com.zara.commerce.exception;

import java.io.Serial;

/**
 * Exception to indicate there is no price to apply
 */
public class DataNotFoundException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

    public DataNotFoundException(String message) {
        super(message);
    }
}
