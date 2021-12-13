package com.equip.priceme.repository;

import com.equip.priceme.entity.Commodity;
import com.equip.priceme.entity.CommodityId;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import java.util.List;

@Repository
public interface CommodityRepository extends CrudRepository<Commodity, CommodityId> {

    @Query("FROM Commodity AS c WHERE lower(c.commodityId.commodityName) = lower(:commodityName)")
    List<Commodity> listCommodity(String commodityName);
}
