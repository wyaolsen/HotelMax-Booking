package com.example.HotelBooking.DAO;

import com.example.HotelBooking.Models.RoomType;

import java.util.List;

public interface RoomTypeDao {

    public List<RoomType> getAllRoomTypes();

    public RoomType getRoomType(int roomTypeId);

    public void updateRoomType(RoomType updatedRoomType);

    public void removeRoomType(int roomTypeId);
}
