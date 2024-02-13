package com.zara.commerce;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zara.commerce.controller.PricesController;
import com.zara.commerce.entity.InditexPrice;
import com.zara.commerce.service.PricesService;
import com.zara.commerce.validator.PricesValidator;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(controllers = PricesController.class)
@ExtendWith(MockitoExtension.class)
class ApplicationTests {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private PricesService service;

    @MockBean
    PricesValidator validator;

    @Autowired
    private ObjectMapper objectMapper;

    private InditexPrice price;

    private ResultActions response;

    @BeforeEach
    void before() {
        price = new InditexPrice();
    }

    @AfterEach
    void after() throws Exception {
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.curr", CoreMatchers.is(price.getCurr())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.priority", CoreMatchers.is(price.getPriority())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.startDate", CoreMatchers.is(price.getStartDate().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price", CoreMatchers.is(price.getPrice())));
        response = null;
    }

    //Test 1: petición a las 10:00 del día 14 del producto 35455   para la brand 1 (ZARA)
    @Test
    void test_10_00_00_deldía_14() throws JsonProcessingException, Exception {
        price.setPrice(25.45);
        price.setPriceList("2");
        price.setPriority(1);
        price.setStartDate(LocalDateTime.of(2020, 6, 14, 0, 0, 0));
        price.setEndDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59));

        when(service.getPriceToApply(LocalDateTime.of(2020, 6, 14, 10, 0, 0),
                "35455", 1)).thenReturn(price);
        response = mockMvc.perform(get("/prices/listPrice?applyDate=2020-06-14 10.00.00&productId=35455&brandID=1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(price)));
    }

    //Test 2: petición a las 16:00 del día 14 del producto 35455   para la brand 1 (ZARA)
    @Test
    void test_16_00_00_deldía_14() throws JsonProcessingException, Exception {
        price.setPrice(25.45);
        price.setPriceList("2");
        price.setPriority(1);
        price.setStartDate(LocalDateTime.of(2020, 6, 14, 15, 0, 0));
        price.setEndDate(LocalDateTime.of(2020, 6, 14, 18, 30, 0));


        when(service.getPriceToApply(LocalDateTime.of(2020, 6, 14, 16, 0, 0),
                "35455", 1)).thenReturn(price);

        response = mockMvc.perform(get("/prices/listPrice?applyDate=2020-06-14 16.00.00&productId=35455&brandID=1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(price)));
    }

    //Test 3: petición a las 21:00 del día 14 del producto 35455   para la brand 1 (ZARA)
    @Test
     void test_21_00_00_deldía_14() throws JsonProcessingException, Exception {
        price.setPrice(38.95);
        price.setPriceList("4");
        price.setPriority(1);
        price.setStartDate(LocalDateTime.of(2020, 6, 15, 16, 0, 0));
        price.setEndDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59));

        when(service.getPriceToApply(LocalDateTime.of(2020, 6, 14, 21, 0, 0),
                "35455", 1)).thenReturn(price);

        response = mockMvc.perform(get("/prices/listPrice?applyDate=2020-06-14 21.00.00&productId=35455&brandID=1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(price)));
    }

    //Test 4: petición a las 10:00 del día 15 del producto 35455   para la brand 1 (ZARA)
    @Test
    void test_10_00_00_deldía_15() throws JsonProcessingException, Exception {
        price.setPrice(20.0);
        price.setPriceList("3");
        price.setPriority(1);
        price.setStartDate(LocalDateTime.of(2020, 6, 15, 0, 0, 0));
        price.setEndDate(LocalDateTime.of(2020, 6, 15, 11, 0, 0));

        when(service.getPriceToApply(LocalDateTime.of(2020, 6, 15, 10, 0, 0),
                "35455", 1)).thenReturn(price);

        response = mockMvc.perform(get("/prices/listPrice?applyDate=2020-06-15 10.00.00&productId=35455&brandID=1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(price)));
    }

    //Test 5: petición a las 21:00 del día 16 del producto 35455   para la brand 1 (ZARA)
    @Test
    void test_21_00_00_deldía_16() throws JsonProcessingException, Exception {
        price.setPrice(38.95);
        price.setPriceList("4");
        price.setPriority(1);
        price.setStartDate(LocalDateTime.of(2020, 6, 15, 16, 0, 0));
        price.setEndDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59));

        when(service.getPriceToApply(LocalDateTime.of(2020, 6, 16, 21, 0, 0),
                "35455", 1)).thenReturn(price);

        response = mockMvc.perform(get("/prices/listPrice?applyDate=2020-06-16 21.00.00&productId=35455&brandID=1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(price)));
    }
}
