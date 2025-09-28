package com.arstialmq.javaweb.converter;

import com.arstialmq.javaweb.builder.BuildingSearchBuilder;
import com.arstialmq.javaweb.utils.MapUtil;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class BuildingSearchBuilderConverter {
    public BuildingSearchBuilder toBuildingSearchBuilder(Map<String, Object> params, List<String> typeCode) {
        BuildingSearchBuilder buildingSearchBuilder = new BuildingSearchBuilder.Builder().setName(MapUtil.getObject(params, "name", String.class))
                .setFloorArea(MapUtil.getObject(params, "floorArea", Long.class))
                .setWard(MapUtil.getObject(params, "ward", String.class))
                .setStreet(MapUtil.getObject(params, "street", String.class))
                .setDistrictId(MapUtil.getObject(params, "districtid", String.class))
                .setNumberOfBasement(MapUtil.getObject(params, "numberofbasement", Integer.class))
                .setTypeCode(typeCode)
                .setManagerName(MapUtil.getObject(params, "managerName", String.class))
                .setManagerPhoneNumber(MapUtil.getObject(params, "managerPhoneNumber", String.class))
                .setRentPriceFrom(MapUtil.getObject(params, "rentPriceFrom", Long.class))
                .setRentPriceTo(MapUtil.getObject(params, "rentPriceTo", Long.class))
                .setAreaFrom(MapUtil.getObject(params, "rentAreaFrom", Long.class))
                .setRentPriceTo(MapUtil.getObject(params, "rentAreaTo", Long.class))
                .build();

        return buildingSearchBuilder;
    }
}
