package com.arstialmq.javaweb.service;

import com.arstialmq.javaweb.model.BuildingDTO;
import com.arstialmq.javaweb.repository.entity.BuildingEntity;

import java.util.List;
import java.util.Map;

public interface BuildingService {
//    List<BuildingDTO> findAll(String name, Long districtId);

    List<BuildingDTO> findAll(Map<String, Object> params);
}
