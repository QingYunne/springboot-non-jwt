package com.arstialmq.javaweb.repository.impl;

import com.arstialmq.javaweb.repository.DistrictRepository;
import com.arstialmq.javaweb.repository.entity.DistrictEntity;
import com.arstialmq.javaweb.utils.ConnectionJDBCUtil;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class DistrictRepositoryImpl implements DistrictRepository {

    @Override
    public DistrictEntity findNameById(Long id) {
        String sql = "SELECT d.name FROM district d WHERE d.id = " + id;
        DistrictEntity district = new DistrictEntity();
        try (Connection conn = ConnectionJDBCUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                district.setName(rs.getString("name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return district;
    }
}
