package com.example.HotelBooking.DAO;

import com.example.HotelBooking.Models.Guest;

import java.util.List;

public interface GuestDao {

    public Guest getGuest(int guestId);

    public Guest createAccount(Guest newGuest);

    public Guest searchGuestByUserName(String username);

    public List<Guest> searchGuestByName(String firstName, String lastName);

    public void deleteGuestAccount(int guestId);
}
