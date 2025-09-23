package com.arstialmq.javaweb.beans;

//DTO: Data Transfer Object
//Java Bean là 1 class tuân thủ các quy tắc sau
//1. Có constructor ko tham số
//2. Tất cả các field là private
//3. Cung cấp getter/setter public cho các field
//4. Có thể được serialize (optional)
public class BuildingDTO {
    private String name;
    private Integer numberOfBasement;
    private String ward;
    private String street;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumberOfBasement() {
        return numberOfBasement;
    }

    public void setNumberOfBasement(Integer numberOfBasement) {
        this.numberOfBasement = numberOfBasement;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
