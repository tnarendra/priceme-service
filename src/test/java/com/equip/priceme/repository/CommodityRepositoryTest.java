package com.equip.priceme.repository;

import com.equip.priceme.TestDataFactory;
import com.equip.priceme.entity.Commodity;
import com.equip.priceme.service.impl.PriceMeServiceImpl;
import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@MicronautTest
public class CommodityRepositoryTest {

    @Inject
    CommodityRepository commodityRepository;
    @Inject
    PriceMeServiceImpl priceMeServiceImpl;

    @MockBean(CommodityRepository.class)
    CommodityRepository commodityRepository() {
        return mock(CommodityRepository.class);
    }

    @Test
    public void testUpdateCommodities() {

        List<Commodity> commodityList = new ArrayList<>();
        when(commodityRepository.updateAll(any(List.class))).thenReturn(TestDataFactory.getUpdatedCommoditiesData());
        Iterable<Commodity> commodities = priceMeServiceImpl.updateCommodities(TestDataFactory.getCommodityDTODataList("data/test-data-file.csv"));

        assertEquals(commodityRepository.updateAll(commodityList), commodities);
    }

    @ParameterizedTest
    @CsvSource({"LEMON,4,1", "CORN,5,9", "MANGO,5,9"})
    public void testGetCommodityPrices(String productName, int tons, int pricePerTon) {
        //when( commodityRepository.listCommodity(any(String.class)) ).thenReturn(TestDataFactory.commodityPricesData(productName,tons,pricePerTon).toString());
        //List<CommodityPricesDTO> commodities =  priceMeServiceImpl.getCommodityPrices(productName, tons, pricePerTon);

        //assertEquals(commodityRepository.listCommodity(any(String.class)).toString(), commodities.toString());
    }
}
