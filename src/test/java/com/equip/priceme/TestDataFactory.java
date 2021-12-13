package com.equip.priceme;

import com.equip.priceme.dto.CommodityDTO;
import com.equip.priceme.dto.CommodityPricesDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class TestDataFactory {

    private static final String[] testData = {"LEMON HN 32 1.24", "LEMON IN 20 1.89", "LEMON CA 15 1.78", "LEMON US 22 1.67", "LEMON MX 33 1.89",
            "LEMON BA 27 1.02", "LEMON AF 21 1.10", "LEMON AL 29 1.48", "LEMON DZ 14 1.34", "LEMON AS 26 1.45",
            "CORN HN 32 1.24", "CORN IN 20 1.89", "CORN CA 15 1.78", "CORN US 22 1.67", "CORN MX 33 1.89", "CORN BA 27 1.02",
            "CORN AF 21 1.10", "CORN AL 29 1.48", "CORN DZ 14 1.34", "CORN AS 26 1.45"};

    private static final String[] singleRecordTestData = {"LEMON HN 32 1.24"};

    public static List<CommodityDTO> getCommodityDTODataList(String fileName) {
        String[] data = {};

        if ("data/test-data-file.csv".equals(fileName)) {
            data = testData;
        } else if ("data/test-data-file-single-record.csv".equals(fileName)) {
            data = singleRecordTestData;
        }

        return Arrays.asList(data).stream()
                .map(str -> {
                    String[] commodity = str.split(" ");
                    CommodityDTO commodityDTO = new CommodityDTO();

                    if (commodity.length == 4) {
                        commodityDTO.setCommodityName(commodity[0].trim());
                        commodityDTO.setCountry(commodity[1].trim());
                        commodityDTO.setFixedOverhead(Double.parseDouble(commodity[2].trim()));
                        commodityDTO.setVarOverhead(Double.parseDouble(commodity[3].trim()));
                    }
                    return commodityDTO;
                }).collect(Collectors.toList());
    }

    public static List<CommodityPricesDTO> commodityPricesData(String productName, int tons, int pricePerTon) {
        List<CommodityPricesDTO> commodityPricesDTOList = new ArrayList<>();
        if ("LEMON".equals(productName) && tons == 4 && pricePerTon == 1) {
            commodityPricesDTOList.add(getCommodityPricesDTOObject("[MX 44.56 | (2.89*4) +33.00\n"));
            commodityPricesDTOList.add(getCommodityPricesDTOObject(" HN 40.96 | (2.24*4) +32.00\n"));
            commodityPricesDTOList.add(getCommodityPricesDTOObject(" AL 38.92 | (2.48*4) +29.00\n"));
            commodityPricesDTOList.add(getCommodityPricesDTOObject(" AS 35.80 | (2.45*4) +26.00\n"));
            commodityPricesDTOList.add(getCommodityPricesDTOObject(" BA 35.08 | (2.02*4) +27.00\n"));
            commodityPricesDTOList.add(getCommodityPricesDTOObject(" US 32.68 | (2.67*4) +22.00\n"));
            commodityPricesDTOList.add(getCommodityPricesDTOObject(" IN 31.56 | (2.89*4) +20.00\n"));
            commodityPricesDTOList.add(getCommodityPricesDTOObject(" AF 29.40 | (2.10*4) +21.00\n"));
            commodityPricesDTOList.add(getCommodityPricesDTOObject(" CA 26.12 | (2.78*4) +15.00\n"));
            commodityPricesDTOList.add(getCommodityPricesDTOObject(" DZ 23.36 | (2.34*4) +14.00\n]"));
            return commodityPricesDTOList;
        } else if ("CORN".equals(productName) && tons == 5 && pricePerTon == 9) {
            commodityPricesDTOList.add(getCommodityPricesDTOObject("[MX 87.45 | (10.89*5) +33.00\n"));
            commodityPricesDTOList.add(getCommodityPricesDTOObject(" HN 83.20 | (10.24*5) +32.00\n"));
            commodityPricesDTOList.add(getCommodityPricesDTOObject(" AL 81.40 | (10.48*5) +29.00\n"));
            commodityPricesDTOList.add(getCommodityPricesDTOObject(" AS 78.25 | (10.45*5) +26.00\n"));
            commodityPricesDTOList.add(getCommodityPricesDTOObject(" BA 77.10 | (10.02*5) +27.00\n"));
            commodityPricesDTOList.add(getCommodityPricesDTOObject(" US 75.35 | (10.67*5) +22.00\n"));
            commodityPricesDTOList.add(getCommodityPricesDTOObject(" IN 74.45 | (10.89*5) +20.00\n"));
            commodityPricesDTOList.add(getCommodityPricesDTOObject(" AF 71.50 | (10.10*5) +21.00\n"));
            commodityPricesDTOList.add(getCommodityPricesDTOObject(" CA 68.90 | (10.78*5) +15.00\n"));
            commodityPricesDTOList.add(getCommodityPricesDTOObject(" DZ 65.70 | (10.34*5) +14.00\n]"));
            return commodityPricesDTOList;
        }
        commodityPricesDTOList.add(getCommodityPricesDTOObject("[]"));
        return commodityPricesDTOList;
    }

    private static CommodityPricesDTO getCommodityPricesDTOObject(String commodityPrice) {
        CommodityPricesDTO commodityPricesDTO = new CommodityPricesDTO();
        commodityPricesDTO.setDetailedCost(commodityPrice);
        return commodityPricesDTO;
    }

    public static List getUpdatedCommoditiesData() {
        String str = "[{country='HN',  name='LEMON',  fixedOverhead=32.0,  varOverhead=1.24},  {country='IN',  name='LEMON',  fixedOverhead=20.0,  varOverhead=1.89},  {country='CA',  name='LEMON',  fixedOverhead=15.0,  varOverhead=1.78},  {country='US',  name='LEMON',  fixedOverhead=22.0,  varOverhead=1.67},  {country='MX',  name='LEMON',  fixedOverhead=33.0,  varOverhead=1.89},  {country='BA',  name='LEMON',  fixedOverhead=27.0,  varOverhead=1.02},  {country='AF',  name='LEMON',  fixedOverhead=21.0,  varOverhead=1.1},  {country='AL',  name='LEMON',  fixedOverhead=29.0,  varOverhead=1.48},  {country='DZ',  name='LEMON',  fixedOverhead=14.0,  varOverhead=1.34},  {country='AS',  name='LEMON',  fixedOverhead=26.0,  varOverhead=1.45},  {country='HN',  name='CORN',  fixedOverhead=32.0,  varOverhead=1.24},  {country='IN',  name='CORN',  fixedOverhead=20.0,  varOverhead=1.89},  {country='CA',  name='CORN',  fixedOverhead=15.0,  varOverhead=1.78},  {country='US',  name='CORN',  fixedOverhead=22.0,  varOverhead=1.67},  {country='MX',  name='CORN',  fixedOverhead=33.0,  varOverhead=1.89},  {country='BA',  name='CORN',  fixedOverhead=27.0,  varOverhead=1.02},  {country='AF',  name='CORN',  fixedOverhead=21.0,  varOverhead=1.1},  {country='AL',  name='CORN',  fixedOverhead=29.0,  varOverhead=1.48},  {country='DZ',  name='CORN',  fixedOverhead=14.0,  varOverhead=1.34},  {country='AS',  name='CORN',  fixedOverhead=26.0,  varOverhead=1.45}]";

        return new ArrayList(Collections.singleton(str));
    }

    public static String getFilePath(String fileName) {
        return TestDataFactory.class.getClassLoader().getResource(fileName).getPath();
    }
}
