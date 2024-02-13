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
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PricesServiceTest {

    @InjectMocks
    PricesService service;

    @Mock
    PricesRepository repo;


    @Test
    void test_highest_priority_price() {
        List<InditexPrice> prices = new ArrayList<>();
        InditexPrice price1 = new InditexPrice();
        price1.setPrice(10.0);
        price1.setPriority(0);
        prices.add(price1);
        InditexPrice price2 = new InditexPrice();
        price2.setPrice(20.0);
        price2.setPriority(1);
        prices.add(price2);

        when(repo.findPricesToApply(any(LocalDateTime.class), anyString(), anyInt()))
                .thenReturn(prices);
        InditexPrice result = service.getPriceToApply(LocalDateTime.now(), "productId", 1);

        assertEquals(price2, result);
    }

}

