package com.zara.commerce.controller;

import com.zara.commerce.entity.InditexPrice;
import com.zara.commerce.exception.InvalidInputDataException;
import com.zara.commerce.service.PricesService;
import com.zara.commerce.validator.PricesValidator;
import jakarta.annotation.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

/**
 * PricesController
 * Rest Controller to get Product data as price to be applied using
 * certain criteria as product names, product brand ...
 */
@RestController
@RequestMapping(value = "/prices")
public class PricesController {

    /**
     * Logger to trace execution
     */
    Logger log = LoggerFactory.getLogger(PricesController.class);

    /**
     * Service to product prices operations.
     */
    PricesService pricesService;

    /**
     * Component to validate Input Data
     */
    PricesValidator validator;

    /**
     * Constructor
     *
     * @param pricesService [Service to Prices]
     * @param validator     [Input Data Validator]
     */
    @Autowired
    public PricesController(PricesService pricesService, PricesValidator validator) {
        this.pricesService = pricesService;
        this.validator = validator;
    }

    /**
     * Returns a list of prices to apply given a current date, a productId and
     * product brand
     *
     * @param applyDate [Application Date]
     * @param productId [Product identifier]
     * @param brandID   [Brand identifier (default value 'ZARA')]
     * @return a list containing the prices to apply for a certain product and brand
     */
    @GetMapping(value = "/listPrice", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<InditexPrice>> getPricesToApply(
            @RequestParam("applyDate") @Nullable @DateTimeFormat(pattern = "yyyy-MM-dd HH.mm.ss") LocalDateTime applyDate,
            @RequestParam("productId") @Nullable String productId,
            @RequestParam(name="brandID" , defaultValue = "1") Integer brandID) {
        try {
            log.warn("Request to Prices front controller recieved correctly...");
            log.info("Validating inputData");
            validator.validatePricesInputData(applyDate, productId);
            log.debug("Sending request to service [PricesService]...");
            List<InditexPrice> prices = pricesService.getPricesToApply(applyDate, productId, brandID);
            log.info("Process completed successfully");
            return ResponseEntity.ok().body(prices);
        } catch (InvalidInputDataException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
