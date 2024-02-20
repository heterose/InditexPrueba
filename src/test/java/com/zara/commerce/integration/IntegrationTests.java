package com.zara.commerce.integration;

import com.zara.commerce.CommerceApplication;
import com.zara.commerce.dto.InditexPriceDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = CommerceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IntegrationTests {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Sql({"classpath:schema.sql", "classpath:data.sql"})
    @Test
    void testFindPriceToApply() {
        InditexPriceDTO dto = new InditexPriceDTO();
        dto.setStartDate(LocalDateTime.of(2020, 6, 14, 0, 0, 0));
        dto.setEndDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59));
        dto.setBrandID(1);
        dto.setPriceList("1");
        dto.setProductId("35455");
        dto.setPrice(35.5);
        dto.setCurr("EUR");

        InditexPriceDTO result = this.restTemplate
                .getForObject("http://localhost:" + port +
                                "/prices/listPrice?applyDate=2020-06-14 10.00.00&productId=35455&brandID=1",
                        InditexPriceDTO.class);
        assertEquals(dto, result);
    }

}
