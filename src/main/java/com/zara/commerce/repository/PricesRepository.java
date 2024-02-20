package com.zara.commerce.repository;

import com.zara.commerce.entity.InditexPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 *  Repository class to access Prices table and retrieve price data
 */
@Repository
public interface PricesRepository extends JpaRepository<InditexPrice, Long> {

    /**
     * Creates and launch a query to H2 prices having the following parameters
     *
     * @param applicationDate Date of application on prices
     * @param productId       Id for product
     * @param brandID         id for brand. Sefault value is 1(ZARA)
     * @return list of products given certain searching criteria as Applying price date, a productID and a brandId
     */
    @Query("FROM InditexPrice p "
            + "WHERE p.startDate<=:applyDate AND p.endDate>=:applyDate AND p.brandID=:brandID AND "
            + "p.productId=:productId order by p.priority desc limit 1 offset 0")
    Optional<InditexPrice> findPriceToApply(@Param("applyDate") LocalDateTime applicationDate,
                                            @Param("productId") String productId,
                                            @Param("brandID") Integer brandID);
}