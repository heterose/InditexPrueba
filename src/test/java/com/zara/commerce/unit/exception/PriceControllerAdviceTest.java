package com.zara.commerce.unit.exception;

import com.zara.commerce.exception.DataNotFoundException;
import com.zara.commerce.exception.InvalidInputDataException;
import com.zara.commerce.exception.PriceControllerAdvice;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class PriceControllerAdviceTest {

    @InjectMocks
    PriceControllerAdvice advice;

        @Test
        void test_invalid_input_data_exception() {
            InvalidInputDataException exception = new InvalidInputDataException("Invalid input data");

            ResponseEntity<Object> response = advice.invalidInputDataException(exception);
            assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
            assertEquals("Invalid input data", response.getBody());
        }

        // Returns a ResponseEntity with status code 404 and the exception message when a DataNotFoundException is thrown.
        @Test
        void test_data_not_found_exception() {
            DataNotFoundException exception = new DataNotFoundException("Data not found");
            ResponseEntity<Object> response = advice.dataNotFoundException(exception);
            assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
            assertEquals("Data not found", response.getBody());
        }

    }
