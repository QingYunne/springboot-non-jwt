package com.arstialmq.javaweb.api;

import com.arstialmq.javaweb.builder.BuildingSearchBuilder;
import com.arstialmq.javaweb.converter.BuildingSearchBuilderConverter;
import com.arstialmq.javaweb.model.BuildingDTO;

import com.arstialmq.javaweb.customexception.FieldRequiredException;
import com.arstialmq.javaweb.model.BuildingRequestDTO;
import com.arstialmq.javaweb.repository.entity.BuildingEntity;
import com.arstialmq.javaweb.repository.entity.DistrictEntity;
import com.arstialmq.javaweb.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

//@Controller
@RestController //bao gồm @Controller and @ResponseBody
public class BuildingAPI {

    @Autowired
    private BuildingService buildingService;

    @Autowired
    private BuildingSearchBuilderConverter  builderConverter;

    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping("/api/building")
    public List<BuildingDTO> getBuildings(@RequestParam Map<String, Object> params,
                                          @RequestParam(name = "typeCode", required = false) List<String> typeCode) {
        BuildingSearchBuilder buildingSearchBuilder = builderConverter.toBuildingSearchBuilder(params, typeCode);
        List<BuildingDTO> result = buildingService.findAll(buildingSearchBuilder);
        return result;
    }

    private void validate(@RequestBody BuildingDTO buildingDTO) throws FieldRequiredException {
//
    }

    @PostMapping("/api/building/")
    @Transactional
    public void createBuilding(@RequestBody BuildingRequestDTO buildingDTO) {
        BuildingEntity buildingEntity = new BuildingEntity();
        buildingEntity.setName(buildingDTO.getName());
        buildingEntity.setRentPrice(buildingDTO.getRentPrice());
        buildingEntity.setStreet(buildingDTO.getStreet());
        buildingEntity.setWard(buildingDTO.getWard());
        DistrictEntity district = new DistrictEntity();
        district.setId(buildingDTO.getDistrictId());
        buildingEntity.setDistrict(district);
        entityManager.persist(buildingEntity);
    }

    @DeleteMapping("/api/building/{id}")
    public void deleteBuilding(@PathVariable Long id) {
        BuildingEntity buildingEntity = entityManager.find(BuildingEntity.class, id);
        entityManager.remove(buildingEntity);
    }

}
