package com.example.HotelBooking.Controllers;

import com.example.HotelBooking.DAO.RoomDao;
import com.example.HotelBooking.Models.Room;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    private RoomDao roomDao;

    public RoomController(RoomDao roomDao){
        this.roomDao = roomDao;
    }

    @RequestMapping(path = "/allRooms/{id}", method = RequestMethod.GET)
    public List<Room> getAllRooms(@PathVariable int id){
        List<Room> rooms = roomDao.getRoomsInBuilding(id);

        if(rooms == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Rooms not found");
        } else {
            return rooms;
        }
    }

    @RequestMapping(path = "/room/{id}", method = RequestMethod.GET)
    public Room getAllAvailableRooms(@PathVariable int id){
        Room room = roomDao.getRoom(id);
        if (room == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Room not found");
        } else {
            return room;
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/addRoom", method = RequestMethod.POST)
    public Room add(@Valid @RequestBody Room room){

        return roomDao.addRoom(room);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path = "/deleteRoom/{id}", method = RequestMethod.DELETE)
    public void deleteRoom(@PathVariable int id){
        roomDao.removeRoom(id);
    }


}
