package com.example.HotelBooking.Controllers;

import com.example.HotelBooking.DAO.GuestDao;
import com.example.HotelBooking.DAO.RoomDao;
import com.example.HotelBooking.Models.Guest;
import com.example.HotelBooking.Models.Room;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private GuestDao guestDao;

    public UserController(GuestDao guestDao){
        this.guestDao = guestDao;
    }
    @RequestMapping(path = "/user/{id}", method = RequestMethod.GET)
    public Guest getAllAvailableRooms(@PathVariable int id){
        Guest guest = guestDao.getGuest(id);
        if (guest == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Guest not found");
        } else {
            return guest;
        }
    }

    @RequestMapping(path = "/guest/{username}", method = RequestMethod.GET)
    public Guest getGuestByUsername(@PathVariable String username){
        Guest guest = guestDao.searchGuestByUserName(username);

        if(guest == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Guest not found");
        } else {
            return guest;
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/createAccount", method = RequestMethod.POST)
    public Guest createAccount(@Valid @RequestBody Guest guest){

        return guestDao.createAccount(guest);
    }




    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path = "/deleteAccount/{id}", method = RequestMethod.DELETE)
    public void deleteAccount(@PathVariable int id){
        guestDao.deleteGuestAccount(id);
    }


}
