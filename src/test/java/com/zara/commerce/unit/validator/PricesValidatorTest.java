package com.zara.commerce.unit.validator;

import com.zara.commerce.exception.InvalidInputDataException;
import com.zara.commerce.validator.PricesValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class PricesValidatorTest {

    @InjectMocks
    PricesValidator validator;

        @Test
        void test_valid_input_data() {
            LocalDateTime applyDate = LocalDateTime.now();
            String productId = "123";
            assertDoesNotThrow(() ->
                    validator.validatePricesInputData(applyDate, productId));
       }

        @Test
        void test_null_apply_date() {
            LocalDateTime applyDate = null;
            String productId = "123";
            InvalidInputDataException exception =
                    assertThrows(InvalidInputDataException.class, () ->
                    validator.validatePricesInputData(applyDate, productId));
            assertEquals("Los datos de entrada son incorrectos: ApplyDate", exception.getMessage());
        }

        @Test
        void test_blank_product_id() {
            // Given
            LocalDateTime applyDate = LocalDateTime.now();
            String productId = "   ";
            InvalidInputDataException exception = assertThrows(InvalidInputDataException.class, () ->
                    validator.validatePricesInputData(applyDate, productId));
            assertEquals("Los datos de entrada son incorrectos: ProductId",
                    exception.getMessage());
        }

        @Test
        void test_zero_brand_id() {
            LocalDateTime applyDate = LocalDateTime.now();
            String productId = "123";
            assertDoesNotThrow(() ->
                    validator.validatePricesInputData(applyDate, productId));
        }

}
