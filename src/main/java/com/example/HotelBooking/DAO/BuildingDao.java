package com.example.HotelBooking.DAO;

import com.example.HotelBooking.Models.Building;
import com.example.HotelBooking.Models.Company;

import java.util.List;

public interface BuildingDao {

    public List<Building> getCompanyBuildings(int companyId);

    public List<Building> getBuildingsInState(String stateName);

    public Building getBuildingById(int buildingId);

    public Building searchBuildingByName(String buildingNameSearch);

    public void addBuilding(Building newBuilding);

    public void updateBuilding(Building updatedBuilding);

    public void removeBuilding(int buildingId);


}
