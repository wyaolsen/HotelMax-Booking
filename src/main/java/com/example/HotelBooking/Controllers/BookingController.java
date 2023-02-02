package com.example.HotelBooking.Controllers;

import com.example.HotelBooking.DAO.ReservationDao;
import com.example.HotelBooking.DAO.RoomDao;
import com.example.HotelBooking.Models.Reservation;
import com.example.HotelBooking.Models.Room;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController
@RequestMapping("/reservations")
public class BookingController {

    private ReservationDao reservationDao;

    public BookingController(ReservationDao reservationDao){
        this.reservationDao = reservationDao;
    }

    @RequestMapping(path = "/allReservations", method = RequestMethod.GET)
    public List<Reservation> getAllReservation(){
        List<Reservation> reservations = reservationDao.getAllReservations();

        if(reservations == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservations not found");
        } else {
            return reservations;
        }
    }

    @RequestMapping(path = "/reservation/{id}", method = RequestMethod.GET)
    public Reservation getReservation(@PathVariable int id){
        Reservation reservation = reservationDao.getReservationById(id);

        if(reservation == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation not found");
        } else {
            return reservation;
        }
    }

    @RequestMapping(path = "/userReservations/{id}", method = RequestMethod.GET)
    public List<Reservation> getUserReservations(@PathVariable int id){
        List<Reservation> reservations = reservationDao.getReservationsByGuest(id);

        if(reservations == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservations not found");
        } else {
            return reservations;
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/createReservation", method = RequestMethod.POST)
    public Reservation createReservation(@Valid @RequestBody Reservation reservation){

        return reservationDao.addReservation(reservation);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path = "/deleteReservation/{id}", method = RequestMethod.DELETE)
    public void deleteReservation(@PathVariable int id){

        reservationDao.removeReservation(id);
    }
}
