package com.equip.priceme.dto;

import com.equip.priceme.util.PriceMeUtil;
import com.fasterxml.jackson.annotation.JsonSetter;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.validation.Validated;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;


@Introspected
public class CommodityDTO {

    @NotEmpty
    private String country;

    @NotEmpty
    private String commodityName;

    @Min(0)
    @NonNull
    private double fixedOverhead;

    @Min(0)
    @NonNull
    private double varOverhead;

    public CommodityDTO() {
    }

    public String getCountry() {
        return country;
    }

    @JsonSetter("COUNTRY")
    @Validated
    public void setCountry(String country) {
        if (!PriceMeUtil.isValidCountryCode(country)) {
            throw new IllegalArgumentException("Invalid country code. Country must be ISO3166-1 Alpha-2 two letters country code - " + country);
        }
        this.country = country;
    }

    public String getCommodityName() {
        return commodityName;
    }

    @JsonSetter("COMMODITY_NAME")
    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public double getFixedOverhead() {
        return fixedOverhead;
    }

    @JsonSetter("FIXED_OVERHEAD")
    public void setFixedOverhead(double fixedOverhead) {
        this.fixedOverhead = fixedOverhead;
    }

    public double getVarOverhead() {
        return varOverhead;
    }

    @JsonSetter("VAR_OVERHEAD")
    public void setVarOverhead(double varOverhead) {
        this.varOverhead = varOverhead;
    }

    @Override
    public String toString() {
        return "{" +
                "country='" + country + '\'' +
                ", commodityName='" + commodityName + '\'' +
                ", fixedOverhead=" + fixedOverhead +
                ", varOverhead=" + varOverhead +
                '}';
    }
}
