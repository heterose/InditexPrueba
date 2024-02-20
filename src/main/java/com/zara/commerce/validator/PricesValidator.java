package com.zara.commerce.validator;

import com.zara.commerce.exception.InvalidInputDataException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Prices Validator to validate user input data
 */
@Component
public class PricesValidator {

    /**
     * Validates user input data
     *
     * @param applyDate Application date
     * @param productId The product id
     * @throws InvalidInputDataException when data es invalid
     */
    public void validatePricesInputData(LocalDateTime applyDate, String productId) {
        if (Objects.isNull(applyDate)) {
            throw new InvalidInputDataException("Los datos de entrada son incorrectos: ApplyDate");
        } else if (StringUtils.isBlank(productId)) {
            throw new InvalidInputDataException("Los datos de entrada son incorrectos: ProductId");
        }
    }

}
