package com.example.HotelBooking.Models;

public class RoomType {
    private int roomTypeId;
    private String type;
    private String description;

    public RoomType(){}
    public RoomType(int roomTypeId, String type, String description) {
        this.roomTypeId = roomTypeId;
        this.type = type;
        this.description = description;
    }

    public int getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(int roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
