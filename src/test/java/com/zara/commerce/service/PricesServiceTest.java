package com.zara.commerce.service;

import com.zara.commerce.entity.InditexPrice;
import com.zara.commerce.repository.PricesRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PricesServiceTest {

    @InjectMocks
    PricesService service;

    @Mock
    PricesRepository repo;

    @Test
    void test_valid_parameters() {
        List<InditexPrice> expectedPrices = new ArrayList<>();
        when(repo.findPricesToApply(any(LocalDateTime.class), anyString(),
                anyInt())).thenReturn(expectedPrices);
        List<InditexPrice> actualPrices = service.getPricesToApply(LocalDateTime.now(), "productId", 1);
        assertEquals(expectedPrices, actualPrices);
    }


}

