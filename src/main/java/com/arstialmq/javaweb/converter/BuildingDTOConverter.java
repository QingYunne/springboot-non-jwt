package com.arstialmq.javaweb.converter;

import com.arstialmq.javaweb.model.BuildingDTO;
import com.arstialmq.javaweb.repository.DistrictRepository;
import com.arstialmq.javaweb.repository.RentAreaRepository;
import com.arstialmq.javaweb.repository.entity.BuildingEntity;
import com.arstialmq.javaweb.repository.entity.DistrictEntity;
import com.arstialmq.javaweb.repository.entity.RentAreaEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BuildingDTOConverter {
    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private RentAreaRepository rentAreaRepository;

    @Autowired
    private ModelMapper modelMapper;

    public BuildingDTO toBuildingDTO(BuildingEntity buildingEntity) {
        BuildingDTO result = modelMapper.map(buildingEntity, BuildingDTO.class);
        DistrictEntity district = buildingEntity.getDistrict();
        result.setAddress(buildingEntity.getStreet() + "," + buildingEntity.getWard() + "," + district.getName());
        List<RentAreaEntity> rentAreas = buildingEntity.getRentAreas();
        String areaResult = rentAreas.stream()
                .map((rentArea) -> rentArea.getValue().toString())
                .collect(Collectors.joining(","));
        result.setRentArea(areaResult);

//        result.setName(buildingEntity.getName());
//        result.setRentArea(rentAreas.toString());
//        result.setBrokerageFee(buildingEntity.getBrokerageFee());
//        result.setEmptyArea(buildingEntity.getEmptyArea());
//        result.setFloorArea(buildingEntity.getFloorArea());
//        result.setManagerName(buildingEntity.getManagerName());
//        result.setManagerPhoneNumber(buildingEntity.getManagerPhoneNumber());
        return result;
    }

//    public BuildingDTO toBuildingDTO(BuildingEntity buildingEntity) {
//        BuildingDTO result = modelMapper.map(buildingEntity, BuildingDTO.class);
//        DistrictEntity district = districtRepository.findNameById(buildingEntity.getDistrictId());
//        //result.setAddress(buildingEntity.getStreet() + "," + buildingEntity.getWard() + "," + district.getName());
//        List<RentAreaEntity> rentAreas = rentAreaRepository.getValueByBuildingId(buildingEntity.getId());
//        String areaResult = rentAreas.stream()
//                .map((rentArea) -> rentArea.getValue().toString())
//                .collect(Collectors.joining(","));
//        result.setRentArea(areaResult);
//
//        result.setName(buildingEntity.getName());
//        result.setRentArea(rentAreas.toString());
//        result.setBrokerageFee(buildingEntity.getBrokerageFee());
//        result.setEmptyArea(buildingEntity.getEmptyArea());
//        result.setFloorArea(buildingEntity.getFloorArea());
//        result.setManagerName(buildingEntity.getManagerName());
//        result.setManagerPhoneNumber(buildingEntity.getManagerPhoneNumber());
//        return result;
//    }
}
