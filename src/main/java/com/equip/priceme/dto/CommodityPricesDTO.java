package com.equip.priceme.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CommodityPricesDTO implements Comparable<CommodityPricesDTO> {

    @JsonIgnore
    private Double totalCost;
    private String detailedCost;

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public String getDetailedCost() {
        return detailedCost;
    }

    public void setDetailedCost(String detailedCost) {
        this.detailedCost = detailedCost;
    }

    @Override
    public int compareTo(CommodityPricesDTO commodityPricesDTO) {
        if (getTotalCost() == 0 || commodityPricesDTO.getTotalCost() == 0) {
            return 0;
        }
        return commodityPricesDTO.getTotalCost().compareTo(getTotalCost());
    }

    @Override
    public String toString() {
        return detailedCost;
    }
}
