package com.example.HotelBooking.DAO;

import com.example.HotelBooking.Models.Guest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

@Component
public class JdbcGuestDao implements GuestDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcGuestDao(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    @Override
    public Guest getGuest(int guestId) {
        String sql = "SELECT * FROM guest WHERE guest_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, guestId);

        if(results.next()){
            return mapRowToGuestWithOutPassword(results);
        } else {
            return null;
        }
    }

    @Override
    public Guest createAccount(Guest newGuest) {
        String sql = "INSERT INTO guest (name, username, password) VALUES (?, ?, ?) RETURNING *;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, newGuest.getName(), newGuest.getUsername(), newGuest.getPassword());


        if(results.next()){
            return mapRowToGuestWithOutPassword(results);
        } else {
            // maybe throw an exception
            return null;
        }
    }

    @Override
    public Guest searchGuestByUserName(String username) {
        String sql = "SELECT * FROM guest WHERE username = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, username);

        if(results.next()){
            return mapRowToGuestWithOutPassword(results);
        } else {
            // maybe throw an exception
            return null;
        }
    }

    @Override
    public List<Guest> searchGuestByName(String firstName, String lastName) {

        return null;
    }

    @Override
    public void deleteGuestAccount(int guestId) {
        String sql = "DELETE FROM guest WHERE guest_id = ?;";
        jdbcTemplate.update(sql, guestId);
    }

    private Guest mapRowToGuest(SqlRowSet rowSet){
        Guest guest = new Guest();
        guest.setGuestId(rowSet.getInt("guest_id"));
        guest.setName(rowSet.getString("name"));
        guest.setUsername(rowSet.getString("username"));
        guest.setPassword(rowSet.getString("password"));

        return guest;
    }

    private Guest mapRowToGuestWithOutPassword(SqlRowSet rowSet){
        Guest guest = new Guest();
        guest.setGuestId(rowSet.getInt("guest_id"));
        guest.setName(rowSet.getString("name"));
        guest.setUsername(rowSet.getString("username"));


        return guest;
    }


}
