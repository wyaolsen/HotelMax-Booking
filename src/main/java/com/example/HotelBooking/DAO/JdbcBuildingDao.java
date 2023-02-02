package com.example.HotelBooking.DAO;

import com.example.HotelBooking.Models.Building;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;


import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class JdbcBuildingDao implements BuildingDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcBuildingDao(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    @Override
    public List<Building> getCompanyBuildings(int companyId) {
        String sql = "SELECT * FROM building WHERE company_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, companyId);

        List<Building> buildings = new ArrayList<Building>();
        while(results.next()){
            buildings.add(mapRowToBuilding(results));
        }

        return buildings;
    }

    @Override
    public List<Building> getBuildingsInState(String stateName) {
        String sql = "SELECT * FROM building WHERE state_id = (SELECT state_id FROM state WHERE name = ?);";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, stateName);

        List<Building> buildings = new ArrayList<Building>();
        while(results.next()){
            buildings.add(mapRowToBuilding(results));
        }

        return buildings;
    }

    @Override
    public Building getBuildingById(int buildingId) {
        String sql = "SELECT * FROM building WHERE building_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, buildingId);
        return mapRowToBuilding(results);
    }

    @Override
    public Building searchBuildingByName(String buildingNameSearch) {
        String sql = "SELECT * FROM building WHERE name LIKE ? LIMIT 1;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, "%" + buildingNameSearch + "%");

        return mapRowToBuilding(results);
    }

    @Override
    public void addBuilding(Building newBuilding) {
        String sql = "INSERT INTO building (name, description, company_id, address_id) RETURNING *;";
        jdbcTemplate.queryForObject(sql, Long.class, newBuilding.getName(), newBuilding.getDescription(), newBuilding.getCompanyId(), newBuilding.getAddressId());

    }

    @Override
    public void updateBuilding(Building updatedBuilding) {
        String sql = "UPDATE FROM building SET name = ?, description = ?, company_id = ?, address_id = ? WHERE building_id = ?;";
        jdbcTemplate.update(sql, updatedBuilding.getName(), updatedBuilding.getDescription(), updatedBuilding.getCompanyId(), updatedBuilding.getAddressId(), updatedBuilding.getBuildingId());
    }

    @Override
    public void removeBuilding(int buildingId) {
        String sql = "DELETE FROM building WHERE building_id = ?";
        jdbcTemplate.update(sql,buildingId);
    }

    private Building mapRowToBuilding(SqlRowSet rowSet){
        Building newBuilding = new Building();

        newBuilding.setBuildingId(rowSet.getInt("building_id"));
        newBuilding.setName(rowSet.getString("name"));
        newBuilding.setDescription(rowSet.getString("description"));
        newBuilding.setCompanyId(rowSet.getInt("company_id"));
        newBuilding.setAddressId(rowSet.getInt("address_id"));

        return newBuilding;
    }
}
