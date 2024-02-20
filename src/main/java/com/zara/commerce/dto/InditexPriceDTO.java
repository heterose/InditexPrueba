package com.zara.commerce.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * DTO Entity to carry out data from/to Controller
 */
@Setter
@Getter
@Data
public class InditexPriceDTO {

    /**
     * Brand Id for product. Default Values is 1 -> "ZARA"
     */
    private Integer brandID;

    /**
     * The start date for dates range
     */
    private LocalDateTime startDate;

    /**
     * The end date for dates range
     */
    private LocalDateTime endDate;

    /**
     * The price list
     */
    private String priceList;

    /**
     * The product Id
     */
    private String productId;

    /**
     * The price for a product
     */
    private Double price;

    /**
     * The currency for a product
     */
    private String curr;

}

