package com.zara.commerce.mapper;

import com.zara.commerce.dto.InditexPriceDTO;
import com.zara.commerce.entity.InditexPrice;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Maps Data Layer entity to controller DTO entity.
 */
@Mapper
public interface PriceMapper {

    @Mapping(source = "inditexPrice.brandID", target = "brandID")
    @Mapping(source = "inditexPrice.startDate", target = "startDate")
    @Mapping(source = "inditexPrice.endDate", target = "endDate")
    @Mapping(source = "inditexPrice.priceList", target = "priceList")
    @Mapping(source = "inditexPrice.productId", target = "productId")
    @Mapping(source = "inditexPrice.price", target = "price")
    @Mapping(source = "inditexPrice.curr", target = "curr")
    InditexPriceDTO inditexPriceToInditexPriceDTO(InditexPrice inditexPrice);
}
