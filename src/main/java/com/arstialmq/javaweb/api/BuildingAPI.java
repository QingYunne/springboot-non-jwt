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
    public List<BuildingDTO> getBuildings(@RequestParam Map<String, Object> params) {
        return null;
    }

//    @GetMapping("/api/building/")
//    public List<BuildingDTO> getBuildings(@RequestParam(name = "name", required = false) String name, @RequestParam(name = "districtId", required = false) Long district, @RequestParam(name = "typeCode", required = false) List<String> typeCode
//    ) {
//        List<BuildingDTO> result = buildingService.findAll(name, district);
//
//        return result;
//    }

    //vi pham SOLID

//    static final String DB_URL = "jdbc:mysql://localhost:3306/estatebasic?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
//    static final String DB_USER = "root";
//    static final String DB_PASS = "Root@1234";

    //nếu extends RuntimeException thì không cần throws vì RuntimeException là unchecked exception
    private void validate(@RequestBody BuildingDTO buildingDTO) throws FieldRequiredException {
        if(buildingDTO.getName() == null || buildingDTO.getName().isEmpty() || buildingDTO.getNumberOfBasement() == null) {
            throw new FieldRequiredException("Name and numberOfBasement are required");
        }
    }

    @DeleteMapping("/api/building/{id}")
    public void deleteBuilding(@PathVariable String id,
                               @RequestParam(value = "numberOfBasement", required = false) Integer numberOfBasement,
                               @RequestParam(value = "ward", required = false) String ward) {
        System.out.println("Already have deleted building: " + id);
    }

//    @GetMapping("/api/building/")
//    public List<BuildingDTO> getBuildings(@RequestParam String name) {
//        String sql = "select * from building where name like '%" + name + "%'";
//        List<BuildingDTO> result = new ArrayList<>();
//        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
//        Statement stmt = conn.createStatement();
//        ResultSet rs = stmt.executeQuery(sql)) {
//            while (rs.next()) {
//                BuildingDTO building = new BuildingDTO();
//                building.setName(rs.getString("name"));
//                building.setNumberOfBasement(rs.getInt("numberOfBasement"));
//                building.setWard(rs.getString("ward"));
//                result.add(building);
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return result;
//    }

//    @GetMapping("/api/building/")
//    public List<BuildingDTO> getBuildings() throws SQLException {
//        String sql = "select * from building";
//        List<BuildingDTO> result = new ArrayList<>();
//        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
//             Statement stmt = conn.createStatement();
//             ResultSet rs = stmt.executeQuery(sql)) {
//            while (rs.next()) {
//                BuildingDTO building = new BuildingDTO();
//                building.setName(rs.getString("name"));
//                building.setStreet(rs.getString("street"));
//                building.setWard(rs.getString("ward"));
//                building.setNumberOfBasement(rs.getInt("numberofbasement"));
//                result.add(building);
//            }
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//            System.out.println("connected database error");
//        }
//        return result;
//    }

//    @PostMapping("/api/building/")
//    public Object getBuilding(@RequestBody BuildingDTO building) {
//            //System.out.println(5/0);
//            validate(building);
//
//        return building;
//    }

//    @PostMapping("/api/building/")
//    public Object getBuilding(@RequestBody BuildingDTO building) {
//        //xử lí dưới DB xong rồi
//        try {
//            //System.out.println(5/0);
//            validate(building);
//        }
//        catch (FieldRequiredException e) {
//            ErrorResponsDTO errorResponsDTO = new ErrorResponsDTO();
//            errorResponsDTO.setError(e.getMessage());
//            List<String> details = new ArrayList<>();
//            details.add("Please provide name and numberOfBasement for building");
//            errorResponsDTO.setDetails(details);
//            return errorResponsDTO;
//        }
//
//        return building;
//    }

    //@RequestMapping(value = "/api/building/", method = RequestMethod.GET)
   // @ResponseBody //Deserialize JSON -> Java Object (Jackson làm hộ)
    //@RequestMapping("/api/building/")
//    public Object getBuilding(@RequestParam(value = "name", required = false) String name,
//                               @RequestParam(value = "numberOfBasement", required = false) Integer numberOfBasement,
//                               @RequestParam(value = "ward", required = false) String ward) {
//        //xử lí dưới DB xong rồi
//        try {
//            System.out.println(5/0);
//        }
//        catch (ArithmeticException e) {
//            ErrorResponsDTO errorResponsDTO = new ErrorResponsDTO();
//            errorResponsDTO.setError(e.getMessage());
//            List<String> details = new ArrayList<>();
//            details.add("Can not divide by zero");
//            return errorResponsDTO;
//        }
//        BuildingDTO result = new BuildingDTO();
//        result.setName(name);
//        result.setNumberOfBasement(numberOfBasement);
//        result.setWard(ward);
//
//        return result;
//    }

//    @RequestMapping(value = "/api/building/", method = RequestMethod.GET)
//    public void getBuilding(@RequestParam(value = "name", required = false) String name,
//                         @RequestParam(value = "numberOfBasement", required = false) Integer numberOfBasement,
//                            @RequestParam(value = "ward", required = false) String ward) {
//        System.out.println(name + " " +  numberOfBasement);
//    }

    //@RequestMapping(value = "/api/building/", method = RequestMethod.POST)
    //@RequestBody Serialize Java Object -> JSON (Jackson) làm hộ
//    @PostMapping("/api/building")
//    public BuildingDTO getBuilding2(@RequestBody BuildingDTO buildingDTO) {
//        //sau khi xử lí đưới DB
//        return buildingDTO;
//    }

//    @RequestMapping(value = "/api/building/", method = RequestMethod.POST)
//    public BuildingDTO getBuilding2(@RequestBody BuildingDTO buildingDTO) {
//        //xử lí dưới DB
//        return buildingDTO;
//    }

//    @RequestMapping(value = "/api/building/", method = RequestMethod.POST)
//    public void getBuilding2(@RequestBody BuildingDTO buildingDTO) {
//        System.out.println("ok");
//    }

//    @RequestMapping(value = "/api/building/", method = RequestMethod.POST)
//    public void getBuilding2(@RequestBody Map<String, String> params) {
//        System.out.println("ok");
//    }

    //    @RequestMapping(value = "/api/building/", method = RequestMethod.POST)
//    public void getBuilding2(@RequestParam Map<String, String> params) {
//        System.out.println("ok");
//    }


}
