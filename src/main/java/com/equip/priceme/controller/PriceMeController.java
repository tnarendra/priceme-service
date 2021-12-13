package com.equip.priceme.controller;

import com.equip.priceme.dto.CommodityDTO;
import com.equip.priceme.dto.CommodityPricesDTO;
import com.equip.priceme.entity.Commodity;
import com.equip.priceme.service.impl.PriceMeServiceImpl;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Put;

import java.util.List;

@Controller("/priceme")
public class PriceMeController {

    private final PriceMeServiceImpl priceMeServiceImpl;

    public PriceMeController(PriceMeServiceImpl priceMeServiceImpl) {
        this.priceMeServiceImpl = priceMeServiceImpl;
    }

    @Get(value = "/health", consumes = MediaType.TEXT_PLAIN, produces = MediaType.TEXT_PLAIN)
    public HttpResponse<String> getHealth() {
        return HttpResponse.ok("UP");
    }

    @Get(value = "/commodity/prices", consumes = MediaType.TEXT_PLAIN, produces = MediaType.TEXT_PLAIN)
    public HttpResponse<List<CommodityPricesDTO>> getCommodityPrice(String commodity, int tons, double pricePerTon) {
        return HttpResponse.ok(priceMeServiceImpl.getCommodityPrices(commodity, tons, pricePerTon));
    }

    @Put(value = "/commodities", consumes = MediaType.APPLICATION_JSON, produces = MediaType.TEXT_PLAIN)
    public HttpResponse<Iterable<Commodity>> updateCommodities(@Body List<CommodityDTO> commodities) {
        return HttpResponse.ok(priceMeServiceImpl.updateCommodities(commodities));
    }


}
