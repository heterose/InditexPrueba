package com.zara.commerce.unit.service;

import com.zara.commerce.exception.DataNotFoundException;
import com.zara.commerce.mapper.PriceMapper;
import com.zara.commerce.repository.PricesRepository;
import com.zara.commerce.service.PricesService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PricesServiceTest {

    @InjectMocks
    PricesService service;

    @Mock
    PricesRepository repo;

    @Mock
    PriceMapper mapper;

    @Test
    void test_price_not_found() {
        LocalDateTime applyDate = LocalDateTime.now();
        String productId = "productId";
        Integer brandID = 1;
        when(repo.findPriceToApply(applyDate, productId, brandID))
                .thenReturn(Optional.empty());
        assertThrows(DataNotFoundException.class, () ->
                service.getPriceToApply(applyDate, productId, brandID));
    }

    @Test
    void test_null_apply_date() {
        String productId = "productId";
        Integer brandID = 1;

        assertThrows(DataNotFoundException.class, () ->
                service.getPriceToApply(null, productId, brandID));
    }


}

