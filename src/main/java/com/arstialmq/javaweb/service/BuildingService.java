package com.arstialmq.javaweb.service;

import com.arstialmq.javaweb.builder.BuildingSearchBuilder;
import com.arstialmq.javaweb.model.BuildingDTO;

import java.util.List;

public interface BuildingService {

    List<BuildingDTO> findAll(BuildingSearchBuilder buildingBuilder);
}
