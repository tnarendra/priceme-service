package com.equip.priceme.command;

import com.equip.priceme.dto.CommodityPricesDTO;
import com.equip.priceme.repository.CommodityRepository;
import com.equip.priceme.service.impl.PriceMeServiceImpl;
import com.equip.priceme.util.FileLoader;
import io.micronaut.context.annotation.Value;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;

@Command(name = "commodity", description = "Get commodity prices and update commodities",
        mixinStandardHelpOptions = true)
public class CommodityCommand implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommodityCommand.class);

    @Value("${data.file.name}")
    String fileName;

    @Inject private PriceMeServiceImpl priceMeServiceImpl;
    @Inject private FileLoader fileLoader;

    @Parameters(index = "0", description = "Commodity name")
    private String commodityName;
    @Parameters(index = "1", description = "Commodity quantity in tons")
    private int tons;
    @Parameters(index = "2", description = "Commodity price per ton")
    private double pricePerTon;

    @Override
    public void run() {

        priceMeServiceImpl.updateCommodities(fileLoader.getDataFromFile(fileName));
        List<CommodityPricesDTO> commoditiesPrice = priceMeServiceImpl.getCommodityPrices(commodityName, tons, pricePerTon);

        StringBuilder sb = new StringBuilder("\n");
        commoditiesPrice.forEach(commodityPricesDTO -> sb.append(commodityPricesDTO.getDetailedCost()));
        LOGGER.info(sb.toString());
    }
}
