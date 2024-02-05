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
     *
     * @param applyDate Application date
     * @param productId The product id
     * @throws InvalidInputDataException when data es invalid
     */
    public void validatePricesInputData(LocalDateTime applyDate,
                                        String productId) throws InvalidInputDataException {
        if (!Objects.isNull(applyDate)) {
            if (StringUtils.isBlank(productId))
                throw new InvalidInputDataException("El valor productId debe de venir informado y no ser nulo");
        } else {
            throw new InvalidInputDataException("La fecha no puede ser null o vacía");
        }
    }

}
