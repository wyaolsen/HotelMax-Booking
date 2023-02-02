package com.example.HotelBooking.DAO;

import com.example.HotelBooking.Models.Room;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface RoomDao {

    public List<Room> getRoomsInBuilding(int buildingId);

    public List<Room> getAvailableRooms(int buildingId, LocalDate startDate, LocalDate endDate);


    public Room getRoom(int roomId);

    public Room addRoom(Room newRoom);

    public void removeRoom(int roomId);

    public void updateRoom(Room updateRoom);

}
