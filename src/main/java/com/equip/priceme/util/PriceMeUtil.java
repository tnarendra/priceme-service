package com.equip.priceme.util;

import com.equip.priceme.dto.CommodityPricesDTO;
import com.equip.priceme.entity.Commodity;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

public class PriceMeUtil {
    //private static final Logger LOGGER = LoggerFactory.getLogger(PriceMeUtil.class);

    private static final DecimalFormat df = new DecimalFormat("0.00");
    private static final Set<String> isoCountryCodes = Locale.getISOCountries(Locale.IsoCountryCode.PART1_ALPHA2);

    public static List<CommodityPricesDTO> calculateCommodityPrice(final List<Commodity> commodities, final int tons, final double pricePerTon) {

        return commodities.stream().map(commodity -> {

                    CommodityPricesDTO pricesDTO = new CommodityPricesDTO();
                    String cost = calculateCost(commodity, tons, pricePerTon);
                    pricesDTO.setTotalCost(Double.parseDouble(cost));
                    pricesDTO.setDetailedCost(new StringBuilder(commodity.getCommodityId().getCountry()).append(" ")
                            .append(cost)
                            .append(" | (")
                            .append(df.format(pricePerTon + commodity.getVarOverhead()))
                            .append("*")
                            .append(tons)
                            .append(") +")
                            .append(df.format(commodity.getFixedOverhead()))
                            .append("\n").toString());

                    return pricesDTO;
                })
                .sorted()
                .collect(Collectors.toList());
    }

    private static String calculateCost(final Commodity commodity, final int tons, final double pricePerTon) {
        return df.format(((pricePerTon + commodity.getVarOverhead()) * tons) + commodity.getFixedOverhead());
    }

    public static boolean isValidCountryCode(String countryCode) {
        return isoCountryCodes.contains(countryCode);
    }

    private String formatDouble(double value) {
        return df.format(value);
    }
}
