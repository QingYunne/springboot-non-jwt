package com.arstialmq.javaweb.service.impl;

import com.arstialmq.javaweb.converter.BuildingDTOConverter;
import com.arstialmq.javaweb.model.BuildingDTO;
import com.arstialmq.javaweb.repository.BuildingRepository;
import com.arstialmq.javaweb.repository.entity.BuildingEntity;
import com.arstialmq.javaweb.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class BuildingServiceImpl  implements BuildingService {

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private BuildingDTOConverter buildingDTOConverter;

//    @Autowired
//    private DistricRepository districRepository;
//
//    @Autowired
//    private RentAreaRepository rentAreaRepository;

    @Override
    public List<BuildingDTO> findAll(Map<String, Object> params, List<String> typeCode) {
        List<BuildingEntity> buildingEntities = buildingRepository.findAll(params, typeCode);

        List<BuildingDTO> result = new ArrayList<>();

        for (BuildingEntity item : buildingEntities) {
            BuildingDTO building = buildingDTOConverter.toBuildingDTO(item);
            result.add(building);
        }

        return result;
    }


//    @Override
//    public List<BuildingDTO> findAll(String name,  Long districtId) {
//        List<BuildingEntity> buildingEntities =buildingRepository.findAll(name, districtId);
//        List<BuildingDTO> result = new ArrayList<>();
//
//        for (BuildingEntity item : buildingEntities) {
//            BuildingDTO building = new BuildingDTO();
//            building.setName(item.getName());
//            building.setNumberOfBasement(item.getNumberOfBasement());
//            building.setAddress(item.getStreet() + "," + item.getWard());
//            result.add(building);
//        }
//
//        return result;
//    }
}
