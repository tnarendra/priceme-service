package com.equip.priceme.service;

import com.equip.priceme.dto.CommodityDTO;
import com.equip.priceme.dto.CommodityPricesDTO;
import com.equip.priceme.entity.Commodity;

import java.util.List;

public interface PriceMeService {

    String getHealth();

    List<CommodityPricesDTO> getCommodityPrices(String productName, int tons, double pricePerTon);

    Iterable<Commodity> updateCommodities(List<CommodityDTO> commodities);
}
