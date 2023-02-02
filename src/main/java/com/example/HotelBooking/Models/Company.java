package com.example.HotelBooking.Models;

public class Company {
    private int companyId;
    private String name;
    private String description;
    private String website;

    public Company(){}
    public Company(int companyId, String name, String description, String website) {
        this.companyId = companyId;
        this.name = name;
        this.description = description;
        this.website = website;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
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

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}
