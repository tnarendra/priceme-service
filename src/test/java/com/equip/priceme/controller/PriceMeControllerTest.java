package com.equip.priceme.controller;

import com.equip.priceme.TestDataFactory;
import com.equip.priceme.dto.CommodityDTO;
import com.equip.priceme.dto.CommodityPricesDTO;
import com.equip.priceme.entity.Commodity;
import com.equip.priceme.service.PriceMeService;
import com.equip.priceme.service.impl.PriceMeServiceImpl;
import com.equip.priceme.util.FileLoader;
import io.micronaut.context.annotation.Property;
import io.micronaut.context.annotation.Requires;
import io.micronaut.core.util.StringUtils;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@MicronautTest
@Requires(property = "mockito.test.enabled", defaultValue = StringUtils.FALSE, value = StringUtils.TRUE)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PriceMeControllerTest {

    @Inject
    private PriceMeService priceMeService;

    @Inject
    @Client("/priceme")
    private HttpClient client;

    @Inject
    private FileLoader fileLoader;

    @Property(name = "test.data.file.name", defaultValue = "data/test-data-file.csv")
    private String testFileName;

    @MockBean(PriceMeServiceImpl.class)
    PriceMeService priceMeService() {
        return mock(PriceMeService.class);
    }

    @BeforeAll
    public void setupTest() {
        Assertions.assertNotNull(priceMeService);
        Assertions.assertNotNull(client);
        Assertions.assertNotNull(fileLoader);
        Assertions.assertNotNull(testFileName);
    }

    @Test
    public void testPingServer() {
        when(priceMeService.getHealth()).thenReturn("UP");
        assertEquals(priceMeService.getHealth(), client.toBlocking().retrieve(HttpRequest.GET("/health"), String.class));
        verify(priceMeService).getHealth();
    }

    @Test
    @Order(1)
    public void testUpdateCommodities() {
        List<CommodityDTO> commodityList = new ArrayList<>();
        when(priceMeService.updateCommodities(commodityList)).thenReturn(TestDataFactory.getUpdatedCommoditiesData());

        final List<Commodity> result = client.toBlocking()
                .retrieve(HttpRequest.PUT("/commodities", fileLoader.getDataFromFile(testFileName)), List.class);

        String str = priceMeService.updateCommodities(commodityList).toString();
        assertEquals(str, result.toString());
        verify(priceMeService).updateCommodities(commodityList);
    }

    @ParameterizedTest
    @CsvSource({"LEMON,4,1", "CORN,5,9", "MANGO,5,9"})
    void testGetCommodityPrices(String productName, int tons, int pricePerTon) {

        when(priceMeService.getCommodityPrices(productName, tons, pricePerTon)).thenReturn(TestDataFactory.commodityPricesData(productName, tons, pricePerTon));
        final List<CommodityPricesDTO> result = client.toBlocking()
                .retrieve(HttpRequest.GET("/commodity/prices?commodity=" + productName + "&tons=" + tons + "&pricePerTon=" + pricePerTon), List.class);
        assertEquals(priceMeService.getCommodityPrices(productName, tons, pricePerTon).toString(), result.toString());
        verify(priceMeService).getCommodityPrices(productName, tons, pricePerTon);
    }
}