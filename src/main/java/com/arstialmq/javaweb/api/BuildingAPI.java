package com.arstialmq.javaweb.api;

import com.arstialmq.javaweb.model.BuildingDTO;

import com.arstialmq.javaweb.customexception.FieldRequiredException;
import com.arstialmq.javaweb.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//@Controller
@RestController //bao gồm @Controller and @ResponseBody
public class BuildingAPI {

    @Autowired
    private BuildingService buildingService;

    @GetMapping("/api/building")
    public List<BuildingDTO> getBuildings(@RequestParam Map<String, Object> params,
                                          @RequestParam(name = "typeCode", required = false) List<String> typeCode) {
        List<BuildingDTO> result = buildingService.findAll(params, typeCode);
        return result;
    }

    private void validate(@RequestBody BuildingDTO buildingDTO) throws FieldRequiredException {
//
    }

    @DeleteMapping("/api/building/{id}")
    public void deleteBuilding(@PathVariable String id,
                               @RequestParam(value = "numberOfBasement", required = false) Integer numberOfBasement,
                               @RequestParam(value = "ward", required = false) String ward) {
        System.out.println("Already have deleted building: " + id);
    }

}
