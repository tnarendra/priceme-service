package com.equip.priceme.util;

import com.equip.priceme.dto.CommodityDTO;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class FileLoader {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileLoader.class);

    public List<CommodityDTO> getDataFromFile(String filePath) {

        List<CommodityDTO> commodityList = new ArrayList<>();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filePath);
        LOGGER.info("Loading the data file - " + filePath);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));) {

            String DELIMITER = " ";
            String line;
            while ((line = br.readLine()) != null) {
                String[] commodity = line.split(DELIMITER);
                if (commodity.length == 4) {

                    CommodityDTO commodityDTO = new CommodityDTO();
                    commodityDTO.setCommodityName(commodity[0].trim());
                    commodityDTO.setCountry(commodity[1].trim());
                    commodityDTO.setFixedOverhead(Double.parseDouble(commodity[2].trim()));
                    commodityDTO.setVarOverhead(Double.parseDouble(commodity[3].trim()));

                    commodityList.add(commodityDTO);
                }
            }

        } catch (IOException ex) {
            LOGGER.error("Failed to load data file " + ex);
        }
        return commodityList;
    }
}
