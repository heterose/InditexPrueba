package com.zara.commerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Class to manage Application Global exceptions
 */
@ControllerAdvice
public class PriceControllerAdvice {

    /**
     * invalidInputDataException method
     *
     * @param exception to be thrown
     * @return ResponseEntity within the specific error
     */
    @ExceptionHandler({InvalidInputDataException.class})
    public ResponseEntity<Object> invalidInputDataException(InvalidInputDataException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exception.getMessage());
    }


    /**
     * DataNotFoundException method
     *
     * @param exception to be thrown
     * @return ResponseEntity within the specific error
     */
    @ExceptionHandler({DataNotFoundException.class})
    public ResponseEntity<Object> dataNotFoundException(DataNotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }

}
