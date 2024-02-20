package com.zara.commerce.controller;

import com.zara.commerce.dto.InditexPriceDTO;
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

import java.time.LocalDateTime;
import java.util.Optional;

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
     * @return ResponseEntity<InditexPriceDTO>
     */
    @GetMapping(value = "/listPrice", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<InditexPriceDTO> getPriceToApply(
            @RequestParam("applyDate") @Nullable @DateTimeFormat(pattern = "yyyy-MM-dd HH.mm.ss") LocalDateTime applyDate,
            @RequestParam("productId") @Nullable String productId,
            @RequestParam(name = "brandID", defaultValue = "1") Integer brandID) {

        log.warn("Request to Prices front controller recieved correctly...");
        log.info("Validating inputData");
        validator.validatePricesInputData(applyDate, productId);
        log.debug("Sending request to service [PricesService]...");
        Optional<InditexPriceDTO> price = pricesService.getPriceToApply(applyDate, productId, brandID);
        log.info("Process completed successfully");
        return price.isPresent()? ResponseEntity.ok().body(price.get()):
             new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
