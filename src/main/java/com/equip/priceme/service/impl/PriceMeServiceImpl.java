package com.equip.priceme.service.impl;

import com.equip.priceme.dto.CommodityDTO;
import com.equip.priceme.dto.CommodityPricesDTO;
import com.equip.priceme.entity.Commodity;
import com.equip.priceme.entity.CommodityId;
import com.equip.priceme.repository.CommodityRepository;
import com.equip.priceme.service.PriceMeService;
import com.equip.priceme.util.PriceMeUtil;
import io.micronaut.core.annotation.NonNull;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class PriceMeServiceImpl implements PriceMeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PriceMeServiceImpl.class);

    final CommodityRepository commodityRepository;

    public PriceMeServiceImpl(CommodityRepository commodityRepository) {
        this.commodityRepository = commodityRepository;
    }

    @Override
    public String getHealth() {
        return "UP";
    }

    @Override
    public List<CommodityPricesDTO> getCommodityPrices(@NotEmpty String commodityName, @NonNull @Min(1) int tons, @NonNull double pricePerTon) {
        LOGGER.info("Getting commodity prices.");
        return PriceMeUtil.calculateCommodityPrice(commodityRepository.listCommodity(commodityName), tons, pricePerTon);
    }

    @Override
    public Iterable<Commodity> updateCommodities(List<CommodityDTO> commodities) {
        LOGGER.info("Updating commodities.");

        return commodityRepository.updateAll(mapCommodities(commodities));
    }

    private List<Commodity> mapCommodities(List<CommodityDTO> commodities) {

        return commodities.stream().map(commodityDTO ->
                new Commodity(new CommodityId(commodityDTO.getCountry(), commodityDTO.getCommodityName()),
                        commodityDTO.getFixedOverhead(), commodityDTO.getVarOverhead())).collect(Collectors.toList());
    }
}
