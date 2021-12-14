package com.equip.priceme.util;

import com.equip.priceme.TestDataFactory;
import com.equip.priceme.dto.CommodityDTO;
import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@MicronautTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FileLoaderTest {

    private static final String FileAbsolutePath = "/Users/user/develop/priceme/src/test/resources/data/";
    @Inject
    FileLoader fileLoader;

    @MockBean(FileLoader.class)
    FileLoader fileLoader() {
        return mock(FileLoader.class);
    }

    @BeforeAll
    public void setupTest() {
        Assertions.assertNotNull(fileLoader);
    }

    @ParameterizedTest()
    @CsvSource({"data/test-data-file.csv", "data/test-data-file-single-record.csv"})
    void testGetDataFromFile_ValidData(String testFileName) {

        List<CommodityDTO> commodityDTOList = TestDataFactory.getCommodityDTODataList(testFileName);
        when(fileLoader.getDataFromFile(testFileName)).thenReturn(commodityDTOList);

        String result = new FileLoader().getDataFromFile(testFileName).toString();
        assertEquals(fileLoader.getDataFromFile(testFileName).toString(), result);

        verify(fileLoader).getDataFromFile(testFileName);
    }

    @ParameterizedTest()
    @CsvSource({"data/test-data-file-invalid-country.csv"})
    public void testGetDataFromFile_InvalidCountry(String testFileName) {

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new FileLoader().getDataFromFile(testFileName);
        });

        String expectedMessage = "Invalid country code. Country must be ISO3166-1 Alpha-2 two letters country code";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @ParameterizedTest()
    @CsvSource({"data/test-data-file-invalid-price.csv"})
    public void testGetDataFromFile_InvalidPrice(String testFileName) {

        Exception exception = assertThrows(NumberFormatException.class, () -> {
            new FileLoader().getDataFromFile(testFileName);
        });

        String expectedMessage = "For input string";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

}
