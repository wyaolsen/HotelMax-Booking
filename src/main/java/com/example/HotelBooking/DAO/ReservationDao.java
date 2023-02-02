package com.example.HotelBooking.DAO;

import com.example.HotelBooking.Models.Reservation;

import java.util.List;

public interface ReservationDao {

    public List<Reservation> getAllReservations();

    public Reservation getReservationById(int reservationId);

    public List<Reservation> getReservationsByGuest(int guestId);

    public Reservation addReservation(Reservation newReservation);

    public void removeReservation(int reservationId);


}
