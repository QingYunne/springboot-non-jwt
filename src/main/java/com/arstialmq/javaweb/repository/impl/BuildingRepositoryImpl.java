package com.arstialmq.javaweb.repository.impl;

import com.arstialmq.javaweb.repository.BuildingRepository;
import com.arstialmq.javaweb.repository.entity.BuildingEntity;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
public class BuildingRepositoryImpl implements BuildingRepository {
    static final String DB_URL = "jdbc:mysql://localhost:3306/estatebasic?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    static final String DB_USER = "root";
    static final String DB_PASS = "Root@1234";

    @Override
    public List<BuildingEntity> findAll(Map<String, Object> params) {
        return null;
    }



//    @Override
//    public List<BuildingEntity> findAll(String name, Long districtId) {
//        StringBuilder sql = new StringBuilder("select * from building b where 1 = 1");
//        if (name != null && !"".equals(name)) {
//            sql.append(" and b.name like '%" +  name + "%'");
//        }
//        if (districtId != null) {
//            sql.append(" and b.districtid = " + districtId);
//        }
//        List<BuildingEntity> result = new ArrayList<>();
//        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
//             Statement stmt = conn.createStatement();
//             ResultSet rs = stmt.executeQuery(sql.toString())) {
//            while (rs.next()) {
//                BuildingEntity buildingEntity = new BuildingEntity();
//                buildingEntity.setName(rs.getString("name"));
//                buildingEntity.setWard(rs.getString("ward"));
//                buildingEntity.setStreet(rs.getString("street"));
//                buildingEntity.setNumberOfBasement(rs.getInt("numberofbasement"));
//                result.add(buildingEntity);
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return result;
//    }
}
