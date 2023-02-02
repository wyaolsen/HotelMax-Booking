package com.example.HotelBooking.DAO;

import com.example.HotelBooking.Models.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class JdbcRoomDao implements RoomDao{

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcRoomDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public List<Room> getRoomsInBuilding(int buildingId) {
        String sql = "SELECT * FROM room WHERE building_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, buildingId);
        List<Room> rooms = new ArrayList<Room>();
        while(results.next()){
            rooms.add(mapRowToRoom(results));
        }
        return rooms;
    }

    @Override
    public List<Room> getAvailableRooms(int buildingId, LocalDate startDate, LocalDate endDate){
        String sql = "Select room.* FROM room LEFT JOIN reservation ON reservation.room_id = room.room_id \n" +
                "WHERE reservation.reservation_id IS NULL\n" +
                "OR reservation.start_date < '2023-1-20' AND reservation.end_date < '2023-1-20' \n" +
                "OR reservation.start_date > '2023-1-27' AND reservation.end_date > '2023-1-27' AND building_id = 1 \n" +
                "GROUP BY room.room_id\n" +
                "ORDER BY room.room_id;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql,startDate, startDate, endDate, endDate, buildingId);

        List<Room> rooms = new ArrayList<Room>();
        while(results.next()){
            rooms.add(mapRowToRoom(results));
        }

        return rooms;
    }

    @Override
    public Room getRoom(int roomId) {
        String sql = "SELECT * FROM room WHERE room_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, roomId);

        if(results.next()){
            return mapRowToRoom(results);
        } else {
            return null;
        }

    }

    @Override
    public Room addRoom(Room newRoom) {
        String sql = "INSERT INTO room (room_type_id, room_number, building_id) VALUES (?, ?, ?) RETURNING *;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, newRoom.getRoomTypeId(), newRoom.getRoomNumber(), newRoom.getBuildingId());
        if(results.next()){
            return mapRowToRoom(results);
        } else {
            // maybe throw an exception
            return null;
        }

    }

    @Override
    public void removeRoom(int roomId) {
        String sql = "DELETE FROM room WHERE room_id = ?;";
        jdbcTemplate.update(sql, roomId);
    }

    @Override
    public void updateRoom(Room updateRoom) {
        String sql = "UPDATE TABLE room SET room_type_id = ?, room_number = ?, building_id = ? WHERE room_id = ?;";
        jdbcTemplate.update(sql, updateRoom.getRoomTypeId(), updateRoom.getRoomNumber(), updateRoom.getBuildingId(), updateRoom.getRoomId());

    }

    private Room mapRowToRoom(SqlRowSet rowSet){
        Room room = new Room();

        room.setRoomId(rowSet.getInt("room_id"));
        room.setRoomNumber(rowSet.getString("room_number"));
        room.setRoomTypeId(rowSet.getInt("room_type_id"));
        room.setBuildingId(rowSet.getInt("building_id"));

        return room;
    }
}
