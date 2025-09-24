package com.arstialmq.javaweb.model;

//DTO: Data Transfer Object
//Java Bean là 1 class tuân thủ các quy tắc sau
//1. Có constructor ko tham số
//2. Tất cả các field là private
//3. Cung cấp getter/setter public cho các field
//4. Có thể được serialize (optional)
public class BuildingDTO {
    private String name;
//    private Integer numberOfBasement;
    private String address;
    private String managerName;
    private String managerPhoneNumber;
    private Long floorArea;
    private String rentArea;
    private String emptyArea;
    private Long rentPrice;
    private String serviceFee;
    private Long brokerageFee;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public Integer getNumberOfBasement() {
//        return numberOfBasement;
//    }
//
//    public void setNumberOfBasement(Integer numberOfBasement) {
//        this.numberOfBasement = numberOfBasement;
//    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getManagerPhoneNumber() {
        return managerPhoneNumber;
    }

    public void setManagerPhoneNumber(String managerPhoneNumber) {
        this.managerPhoneNumber = managerPhoneNumber;
    }

    public Long getFloorArea() {
        return floorArea;
    }

    public void setFloorArea(Long floorArea) {
        this.floorArea = floorArea;
    }

    public String getRentArea() {
        return rentArea;
    }

    public void setRentArea(String rentArea) {
        this.rentArea = rentArea;
    }

    public String getEmptyArea() {
        return emptyArea;
    }

    public void setEmptyArea(String emptyArea) {
        this.emptyArea = emptyArea;
    }

    public Long getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(Long rentPrice) {
        this.rentPrice = rentPrice;
    }

    public String getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(String serviceFee) {
        this.serviceFee = serviceFee;
    }

    public Long getBrokerageFee() {
        return brokerageFee;
    }

    public void setBrokerageFee(Long brokerageFee) {
        this.brokerageFee = brokerageFee;
    }
}
