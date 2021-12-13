package com.equip.priceme.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class Commodity {

    @EmbeddedId
    private CommodityId commodityId;
    private double fixedOverhead;
    private double varOverhead;

    public Commodity(CommodityId commodityId, double fixedOverhead, double varOverhead) {
        this.commodityId = commodityId;
        this.fixedOverhead = fixedOverhead;
        this.varOverhead = varOverhead;
    }

    public Commodity() {
    }

    public CommodityId getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(CommodityId commodityId) {
        this.commodityId = commodityId;
    }

    public double getFixedOverhead() {
        return fixedOverhead;
    }

    public void setFixedOverhead(int fixedOverhead) {
        this.fixedOverhead = fixedOverhead;
    }

    public double getVarOverhead() {
        return varOverhead;
    }

    public void setVarOverhead(int varOverhead) {
        this.varOverhead = varOverhead;
    }

    @Override
    public String toString() {
        return "{" + commodityId +
                ", fixedOverhead=" + fixedOverhead +
                ", varOverhead=" + varOverhead +
                '}';
    }
}