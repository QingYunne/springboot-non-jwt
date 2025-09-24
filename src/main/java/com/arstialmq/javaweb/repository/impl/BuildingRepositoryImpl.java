package com.arstialmq.javaweb.repository.impl;

import com.arstialmq.javaweb.repository.BuildingRepository;
import com.arstialmq.javaweb.repository.entity.BuildingEntity;
import com.arstialmq.javaweb.utils.NumberUtil;
import com.arstialmq.javaweb.utils.StringUtil;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

@Repository
public class BuildingRepositoryImpl implements BuildingRepository {
    static final String DB_URL = "jdbc:mysql://localhost:3306/estatebasic?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    static final String DB_USER = "root";
    static final String DB_PASS = "Root@1234";

    public static void joinTable(Map<String, Object> params, List<String> typeCode, StringBuilder sql)  {
        String staffId = (String) params.get("staffId");
        if (StringUtil.checkString(staffId)) {
            sql.append(" INNER JOIN assignmentbuilding ON b.id =  assignmentbuilding.buildingid");
        }
        if (typeCode != null && typeCode.size() != 0) {
            sql.append(" INNER JOIN buildingrenttype ON b.id =  buildingrenttype.buildingid ");
            sql.append(" INNER JOIN renttype ON buildingrenttype.renttypeid = renttype.id ");
        }
        String rentAreaTo = (String) params.get("areaTo");
        String rentAreaFrom = (String) params.get("areaFrom");
        if (StringUtil.checkString(rentAreaTo) && StringUtil.checkString(rentAreaFrom)) {
            sql.append(" INNER JOIN rentarea ON b.id = rentarea.buildingid");
        }
    }

    public static void queryNormal(Map<String, Object> params, StringBuilder where)  {
        for(Map.Entry<String, Object> entry : params.entrySet()) {
            if (!entry.getKey().equals("staffId") && !entry.getKey().equals("typeCode") && !entry.getKey().equals("rentAreaTo") && !entry.getKey().equals("rentAreaFrom") && !entry.getKey().startsWith("area") && !entry.getKey().startsWith("rentPrice")) {
                String value = entry.getValue().toString();
                if (NumberUtil.isNumeric(value)) {
                    where.append(" AND b." + entry.getKey() + " = " + value);
                }
                else {
                    where.append(" AND b." + entry.getKey() + " LIKE '%" + value + "%'");
                }
            }
        }
    }

    public static void querySpecial(Map<String, Object> params, List<String> typeCode, StringBuilder where)  {
        String staffId = (String) params.get("staffId");
        if (StringUtil.checkString(staffId)) {
            where.append(" AND assignmentbuilding.staffid = " + staffId);
        }
        String rentAreaTo = (String) params.get("rentAreaTo");
        String rentAreaFrom = (String) params.get("rentAreaFrom");
        if (StringUtil.checkString(rentAreaTo) && StringUtil.checkString(rentAreaFrom)) {
            if (NumberUtil.isNumeric(rentAreaTo)) {
                where.append(" AND rentarea.value <= '" + rentAreaTo + "'");
            }
           if (NumberUtil.isNumeric(rentAreaFrom)) {
               where.append(" AND rentarea.value >= '" + rentAreaFrom + "'");
           }
        }
        String rentPriceTo = (String) params.get("rentPriceTo");
        String rentPriceFrom = (String) params.get("rentPriceFrom");
        if (StringUtil.checkString(rentPriceFrom) && StringUtil.checkString(rentPriceTo)) {
            if (NumberUtil.isNumeric(rentPriceTo)) {
                where.append(" AND b.rentprice <= '" + rentPriceTo + "'");
            }
            if (NumberUtil.isNumeric(rentPriceFrom)) {
                where.append(" AND b.rentprice >= '" + rentPriceFrom + "'");
            }
        }
        if (typeCode != null && typeCode.size() != 0) {
            List<String> code = new ArrayList<>();
            for(String e : typeCode) {
                code.add("'" + e + "'");
            }
            where.append(" AND renttype.code IN(" + String.join(",", code) + ")" );
        }
    }

    @Override
    public List<BuildingEntity> findAll(Map<String, Object> params, List<String> typeCode) {
        StringBuilder sql = new StringBuilder("Select b.id, b.name, b.districtid, b.street, b.ward, b.numberofbasement, b.floorarea, b.rentprice, b.managername, b.managerphonenumber, b.servicefee, b.brokeragefee FROM building b");
        joinTable(params, typeCode, sql);
        StringBuilder where = new StringBuilder(" WHERE 1 = 1");
        queryNormal(params, where);
        querySpecial(params, typeCode, where);
        sql.append(where);
        System.out.println(sql.toString());

        List<BuildingEntity> resutl = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
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
