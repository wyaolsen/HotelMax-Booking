package com.example.HotelBooking.Models;

import org.springframework.stereotype.Service;


public class Room {
    private int roomId;
    private int roomTypeId;
    private String roomNumber;
    private int buildingId;


    public Room(){}
    public Room(int roomId, int roomTypeId, String roomNumber, int buildingId) {
        this.roomId = roomId;
        this.roomTypeId = roomTypeId;
        this.roomNumber = roomNumber;
        this.buildingId = buildingId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(int roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(int buildingId) {
        this.buildingId = buildingId;
    }
}
