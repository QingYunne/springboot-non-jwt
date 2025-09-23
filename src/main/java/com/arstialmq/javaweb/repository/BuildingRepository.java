package com.arstialmq.javaweb.repository;

import com.arstialmq.javaweb.repository.entity.BuildingEntity;

import java.util.List;
import java.util.Map;

public interface BuildingRepository {
    List<BuildingEntity> findAll(Map<String, Object> params);
}
