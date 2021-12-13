package com.equip.priceme.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CommodityId implements Serializable {

    private String country;
    private String commodityName;

    public CommodityId(String country, String commodityName) {
        this.country = country;
        this.commodityName = commodityName;
    }

    public CommodityId() {
    }

    public String getCommodityName() {
        return commodityName;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CommodityId commodityId1 = (CommodityId) o;
        return commodityName.equals(commodityId1.commodityName) &&
                country.equals(commodityId1.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commodityName, country);
    }

    @Override
    public String toString() {
        return "country='" + country + '\'' +
                ", name='" + commodityName + '\'';
    }
}