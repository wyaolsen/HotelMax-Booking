package com.example.HotelBooking.Models;
public class Building {
    private int buildingId;
    private String name;
    private String description;
    private int companyId;
    private int addressId;

    public Building(){}

    public Building(int buildingId, String name, String description, int companyId, int addressId) {
        this.buildingId = buildingId;
        this.name = name;
        this.description = description;
        this.companyId = companyId;
        this.addressId = addressId;
    }

    public int getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(int buildingId) {
        this.buildingId = buildingId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }
}
