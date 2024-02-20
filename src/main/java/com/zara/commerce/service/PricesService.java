package com.zara.commerce.service;

import com.zara.commerce.dto.InditexPriceDTO;
import com.zara.commerce.entity.InditexPrice;
import com.zara.commerce.exception.DataNotFoundException;
import com.zara.commerce.mapper.PriceMapper;
import com.zara.commerce.repository.PricesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Service class to retrieve price data using user input data
 */
@Service
public class PricesService {
    Logger log = LoggerFactory.getLogger(PricesService.class);

    /**
     * Mapper to map entities
     */
    PriceMapper priceMapper;

    /**
     * Repository to retrieve data from H2 database
     */
    PricesRepository pricesRepository;

    /**
     * Constructs PricesService
     *
     * @param pricesRepository
     */
    @Autowired
    public PricesService(PricesRepository pricesRepository, PriceMapper priceMapper) {
        this.pricesRepository = pricesRepository;
        this.priceMapper = priceMapper;
    }

    /**
     * getPriceToApply a list of prices to apply having a date a brand and a productID
     *
     * @param applyDate [Date to apply in Date range]
     * @param productId [The product Identifier]
     * @param brandID   [The brand Identifier, as default 'ZARA']
     * @return Optional<InditexPriceDTO>
     */
    public Optional<InditexPriceDTO> getPriceToApply(LocalDateTime applyDate, String productId, Integer brandID) {
        log.info("Searching price to apply for product {} and date {} for brand ZARA", productId, applyDate);
        InditexPrice price = pricesRepository.findPriceToApply(applyDate, productId, brandID)
                .orElseThrow(() -> new DataNotFoundException("No se encontro precio con ese criterio"));
        return Optional.of(priceMapper.inditexPriceToInditexPriceDTO(price));
    }

}
