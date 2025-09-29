package com.arstialmq.javaweb.repository.impl;

import com.arstialmq.javaweb.repository.RentAreaRepository;
import com.arstialmq.javaweb.repository.entity.RentAreaEntity;
import com.arstialmq.javaweb.utils.ConnectionJDBCUtil;
import org.springframework.stereotype.Repository;

import javax.swing.plaf.nimbus.State;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RentAreaRepositoryImpl implements RentAreaRepository {
    @Override
    public List<RentAreaEntity> getValueByBuildingId(Long id) {
        String sql = "SELECT * FROM rentarea WHERE buildingid = " + id;
        List<RentAreaEntity> result = new ArrayList<>();
        try (Connection conn = ConnectionJDBCUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                RentAreaEntity rentAreaEntity = new RentAreaEntity();
//                rentAreaEntity.setBuildingId(rs.getLong("id"));
                rentAreaEntity.setValue(rs.getLong(("value")));
                result.add(rentAreaEntity);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
