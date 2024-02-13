package com.zara.commerce.service;

import com.zara.commerce.entity.InditexPrice;
import com.zara.commerce.repository.PricesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class PricesService {
    Logger log = LoggerFactory.getLogger(PricesService.class);

    PricesRepository pricesRepository;

    @Autowired
    public PricesService(PricesRepository pricesRepository) {
        this.pricesRepository = pricesRepository;
    }

    /**
     * @param applyDate [Date to apply in Date range]
     * @param productId [The product Identifier]
     * @param brandID   [The brand Identifier, as default 'ZARA']
     * @return a list of prices to apply having a date a brand and a productID
     */
    public InditexPrice getPriceToApply(LocalDateTime applyDate, String productId, Integer brandID) {
        log.info("Searching price to apply for product {} and date {} for brand ZARA", productId, applyDate);
        List<InditexPrice> prices = pricesRepository.findPricesToApply(applyDate, productId, brandID);
        Optional<InditexPrice> price = prices.stream().max(Comparator.comparing(p -> p.getPriority()));
        return price.isPresent() ? price.get() : null;
    }

}
