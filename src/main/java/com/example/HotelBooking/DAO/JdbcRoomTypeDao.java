package com.example.HotelBooking.DAO;

import com.example.HotelBooking.Models.RoomType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;


import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class JdbcRoomTypeDao implements RoomTypeDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcRoomTypeDao(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<RoomType> getAllRoomTypes() {
        String sql = "SELECT * FROM room;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        List<RoomType> roomTypes = new ArrayList<RoomType>();
        while(results.next()){
            roomTypes.add(mapRowToRoomType(results));
        }

        return roomTypes;
    }

    @Override
    public RoomType getRoomType(int roomTypeId) {
        String sql = "SELECT * FROM room_type WHERE room_type_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, roomTypeId);

        return mapRowToRoomType(results);
    }

    @Override
    public void updateRoomType(RoomType updatedRoomType) {
        String sql = "UPDATE TABLE room_type SET type = ?, description = ? WHERE room_type_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, updatedRoomType.getType(), updatedRoomType.getDescription(), updatedRoomType.getRoomTypeId());

    }

    @Override
    public void removeRoomType(int roomTypeId) {
        String sql = "DELETE FROM room_type WHERE room_type_id = ?;";
        jdbcTemplate.update(sql, roomTypeId);
    }

    private RoomType mapRowToRoomType(SqlRowSet rowSet){
        RoomType roomType = new RoomType();
        roomType.setType(rowSet.getString("type"));
        roomType.setDescription(rowSet.getString("description"));
        return roomType;
    }
}
