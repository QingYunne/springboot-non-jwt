package com.arstialmq.javaweb.repository;

import com.arstialmq.javaweb.repository.entity.RentAreaEntity;

import java.util.List;

public interface RentAreaRepository {
    List<RentAreaEntity> getValueByBuildingId(Long id);
}
