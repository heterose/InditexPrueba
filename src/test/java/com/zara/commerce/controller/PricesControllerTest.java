package com.zara.commerce.controller;

import com.zara.commerce.entity.InditexPrice;
import com.zara.commerce.exception.InvalidInputDataException;
import com.zara.commerce.service.PricesService;
import com.zara.commerce.validator.PricesValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;

@ExtendWith(MockitoExtension.class)
class PricesControllerTest {

    @InjectMocks
    PricesController controller;

    @Mock
    PricesService pricesService;

    @Mock
    PricesValidator validator;



    @Test
    void test_returns_empty_list_for_valid_input_data_withoutRows() {
        LocalDateTime applyDate = LocalDateTime.now();
        String productId = "123";
        Integer brandID = 1;
        List<InditexPrice> expectedPrices = new ArrayList<>();
        ResponseEntity<List<InditexPrice>> response = controller.getPricesToApply(applyDate, productId, brandID);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedPrices, response.getBody());
    }

    @Test
    void test_validates_input_data_before_sending_request_to_service() throws InvalidInputDataException{
        LocalDateTime applyDate = null;
        String productId = null;
        Integer brandID = 1;
        doThrow(InvalidInputDataException.class).when(validator).validatePricesInputData(applyDate, productId);
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> controller.getPricesToApply(applyDate, productId, brandID));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
    }

}




