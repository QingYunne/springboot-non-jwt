package com.arstialmq.javaweb.repository.impl;

import com.arstialmq.javaweb.builder.BuildingSearchBuilder;
import com.arstialmq.javaweb.repository.BuildingRepository;
import com.arstialmq.javaweb.repository.entity.BuildingEntity;
import com.arstialmq.javaweb.utils.ConnectionJDBCUtil;
import com.arstialmq.javaweb.utils.NumberUtil;
import com.arstialmq.javaweb.utils.StringUtil;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BuildingRepositoryImpl implements BuildingRepository {

    public static void joinTable(BuildingSearchBuilder builder, StringBuilder sql) {
        Long staffId = builder.getStaffId();
        if (StringUtil.checkString(String.valueOf(staffId))) {
            sql.append(" INNER JOIN assignmentbuilding ON b.id =  assignmentbuilding.buildingid");
        }
        List<String> typeCode = builder.getTypeCode();
        if (typeCode != null && typeCode.size() != 0) {
            sql.append(" INNER JOIN buildingrenttype ON b.id =  buildingrenttype.buildingid ");
            sql.append(" INNER JOIN renttype ON buildingrenttype.renttypeid = renttype.id");
        }
        Long rentAreaTo = builder.getRentAreaTo();
        Long rentAreaFrom = builder.getRentAreaFrom()   ;
        if (StringUtil.checkString(String.valueOf(rentAreaFrom)) && StringUtil.checkString(String.valueOf(rentAreaTo))) {
            sql.append(" INNER JOIN rentarea ON b.id = rentarea.buildingid");
        }
    }

    public static void queryNormal(BuildingSearchBuilder builder, StringBuilder where) {
        try {
            Field[] fields = BuildingSearchBuilder.class.getDeclaredFields();
            for (Field item : fields) {
                item.setAccessible(true);
                String fieldName = item.getName();
                if (!fieldName.equals("staffId") && !fieldName.equals("typeCode") && !fieldName.equals("rentAreaTo") && !fieldName.equals("rentAreaFrom") && !fieldName.startsWith("area") && !fieldName.startsWith("rentPrice")) {
                    Object valueObj = item.get(builder);
                    if (valueObj != null && StringUtil.checkString(valueObj.toString())) {
                        String value = valueObj.toString();
                        if (NumberUtil.isNumeric(value)) {
                            where.append(" AND b.").append(fieldName).append(" = " + value);
                        }
                        else {
                            where.append(" AND b.").append(fieldName).append(" like '%").append(value).append("%'");
                        }
                    }
//                    String value = String.valueOf(fieldName);
//                    if (NumberUtil.isNumeric(value)) {
//                        where.append(" AND b.id = " + value);
//                    } else {
//                        where.append(" AND b.id LIKE '%" + value + "%'");
//                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//        for(Map.Entry<String, Object> entry : params.entrySet()) {
//            if (!entry.getKey().equals("staffId") && !entry.getKey().equals("typeCode") && !entry.getKey().equals("rentAreaTo") && !entry.getKey().equals("rentAreaFrom") && !entry.getKey().startsWith("area") && !entry.getKey().startsWith("rentPrice")) {
//                String value = entry.getValue().toString();
//                if (NumberUtil.isNumeric(value)) {
//                    where.append(" AND b." + entry.getKey() + " = " + value);
//                }
//                else {
//                    where.append(" AND b." + entry.getKey() + " LIKE '%" + value + "%'");
//                }
//            }
//        }

        public static void querySpecial (BuildingSearchBuilder builder, StringBuilder where){
            String staffId = String.valueOf(builder.getStaffId());
            if (StringUtil.checkString(String.valueOf(staffId)) && !staffId.toLowerCase().equals("null")) {
                where.append(" AND assignmentbuilding.staffid = " + staffId);
            }
            Long rentAreaTo = builder.getRentAreaTo();
            Long rentAreaFrom = builder.getRentAreaFrom();
            if (rentAreaFrom != null || rentAreaTo != null) {
                where.append(" AND EXISTS (SELECT * FROM rentarea r WHERE b.id = r.buildingid");
                if (rentAreaTo != null) {
                    where.append(" AND r.value <=  " + rentAreaTo);
                }
                if (rentAreaFrom != null) {
                    where.append(" AND r.value >= " + rentAreaFrom);
                }
                where.append(")");
            }
            Long rentPriceTo = builder.getRentPriceTo();
            Long rentPriceFrom = builder.getRentPriceFrom();
            if (StringUtil.checkString(String.valueOf(rentPriceTo)) && StringUtil.checkString(String.valueOf(rentPriceFrom))) {
                if (NumberUtil.isNumeric(String.valueOf(rentPriceTo))) {
                    where.append(" AND b.rentprice <= " + rentPriceTo);
                }
                if (NumberUtil.isNumeric(String.valueOf(rentPriceFrom))) {
                    where.append(" AND b.rentprice >= " + rentPriceFrom);
                }
            }
            //jav\a 7
//        if (typeCode != null && typeCode.size() != 0) {
//            List<String> code = new ArrayList<>();
//            for(String e : typeCode) {
//                code.add("'" + e + "'");
//            }
//            where.append(" AND renttype.code IN(" + String.join(",", code) + ")" );
//        }

            //java 8
            List<String> typeCode = builder.getTypeCode();
            if (typeCode != null && !typeCode.isEmpty()) {
                where.append(" AND renttype.code IN(")
                        .append(typeCode.stream()
                                .map(code -> "'" + code + "'")
                                .collect(Collectors.joining(",")))
                        .append(")");
            }
        }


    @Override
    public List<BuildingEntity> findAll(BuildingSearchBuilder buildingBuilder) {
        StringBuilder sql = new StringBuilder("Select b.id, b.name, b.districtid, b.street, b.ward, b.numberofbasement, b.floorarea, b.rentprice, b.managername, b.managerphonenumber, b.servicefee, b.brokeragefee FROM building b");
        joinTable(buildingBuilder, sql);
        StringBuilder where = new StringBuilder(" WHERE 1 = 1");
        queryNormal(buildingBuilder, where);
        querySpecial(buildingBuilder, where);
        sql.append(where);
        System.out.println(sql.toString());

        List<BuildingEntity> resutl = new ArrayList<>();
        try (Connection conn = ConnectionJDBCUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql.toString())) {
            while (rs.next()) {
                BuildingEntity buildingEntity = new BuildingEntity();
                buildingEntity.setId(rs.getLong("b.id"));
                buildingEntity.setName(rs.getString("b.name"));
                buildingEntity.setWard(rs.getString("b.ward"));
                buildingEntity.setDistrictId(rs.getLong("b.districtid"));
                buildingEntity.setStreet(rs.getString("b.street"));
                buildingEntity.setFloorArea(rs.getLong("b.floorarea"));
                buildingEntity.setRentPrice(rs.getLong("b.rentprice"));
                buildingEntity.setServiceFee(rs.getString("b.servicefee"));
                buildingEntity.setBrokerageFee(rs.getLong("b.brokeragefee"));
                buildingEntity.setManagerName(rs.getString("b.managername"));
                buildingEntity.setManagerPhoneNumber(rs.getString("b.managerphonenumber"));
                resutl.add(buildingEntity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resutl;
    }

}
