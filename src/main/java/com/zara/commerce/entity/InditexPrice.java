package com.zara.commerce.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * JPA Entity to carry out data from H2 PRICES table
 */
@Setter
@Getter
@Entity
@Table(name = "PRICES")
public class InditexPrice {

    /**
     * ID as primary Key
     */
    @Id
    private Long id;

    /**
     * Brand Id for product. Default Values is 1 -> "ZARA"
     */
    @Column(name = "brand_Id")
    private Integer brandID;

    /**
     * The start date for dates range
     */
    @Column(name = "start_date")
    private LocalDateTime startDate;

    /**
     * The end date for dates range
     */
    @Column(name = "end_date")
    private LocalDateTime endDate;

    /**
     * The price list
     */
    @Column(name = "price_list")
    private String priceList;

    /**
     * The product Id
     */
    @Column(name = "product_id")
    private String productId;

    /**
     * price disambiguator for a product
     */
    @Column(name = "priority")
    private Integer priority;

    /**
     * The price for a product
     */
    @Column(name = "price")
    private Double price;

    /**
     * The currency for a product
     */
    @Column(name = "curr")
    private String curr;

}

