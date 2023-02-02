package com.example.HotelBooking.Models;

public class Guest {
    private int guestId;
    private String name;
    private String username;
    private String password;

    public Guest(){}
    public Guest(int guestId, String name, String username, String password) {
        this.guestId = guestId;
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public int getGuestId() {
        return guestId;
    }

    public void setGuestId(int guestId) {
        this.guestId = guestId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
