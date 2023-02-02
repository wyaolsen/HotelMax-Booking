package com.example.HotelBooking.DAO;

import com.example.HotelBooking.Models.Address;
import javax.sql.DataSource;
import javax.sql.RowSet;

import com.example.HotelBooking.Models.State;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class JdbcAddressDao implements AddressDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcAddressDao(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void addAddress(Address newAddress, String stateName) {
        String sql = "INSERT INTO address (street_address, city, state_id, zipcode) VALUES (?,?,(SELECT state_id FROM state WHERE name = ?),?) RETURNING *;";

        jdbcTemplate.queryForObject(sql, Long.class,newAddress.getStreetAddress(), newAddress.getCity(), stateName, newAddress.getZipcode() );
    }

    @Override
    public void updateAddress(Address updatedAddress) {
        String sql = "UPDATE TABLE address SET street_address = ?, city = ?, state_id = ?, zipcode = ? WHERE address_id = ?;";
        jdbcTemplate.update(sql,updatedAddress.getStreetAddress(), updatedAddress.getCity(), updatedAddress.getStateId(), updatedAddress.getZipcode(), updatedAddress.getAddressId());

    }

    @Override
    public void removeAddress(int addressId) {
        String sql = "DELETE FROM address WHERE address_id = ?;";
        jdbcTemplate.update(sql, addressId);
    }

    private Address mapRowToAddress(SqlRowSet rowSet){
        Address newAddress = new Address();
        newAddress.setAddressId(rowSet.getInt("address_id"));
        newAddress.setStreetAddress(rowSet.getString("street_address"));
        newAddress.setCity(rowSet.getString("city"));
        newAddress.setStateId(rowSet.getInt("state_id"));
        newAddress.setZipcode(rowSet.getString("zipcode"));

        return newAddress;
    }
}
