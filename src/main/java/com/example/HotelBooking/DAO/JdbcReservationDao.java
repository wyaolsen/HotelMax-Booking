package com.example.HotelBooking.DAO;

import com.example.HotelBooking.Models.Reservation;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;


@Component
public class JdbcReservationDao implements ReservationDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcReservationDao(DataSource datasource){
        this.jdbcTemplate = new JdbcTemplate(datasource);
    }
    @Override
    public List<Reservation> getAllReservations() {
        String sql = "SELECT * FROM reservation;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        List<Reservation> reservations = new ArrayList<Reservation>();
        while(results.next()){
            reservations.add(mapRowToReservation(results));
        }
        return reservations;
    }

    @Override
    public Reservation getReservationById(int reservationId) {
        String sql = "SELECT * FROM reservation WHERE reservation_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, reservationId);

        if(results.next()){
            return mapRowToReservation(results);
        } else {
            return null;
        }

    }

    @Override
    public List<Reservation> getReservationsByGuest(int guestId) {
        String sql = "SELECT * FROM reservation WHERE guest_id = ?;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, guestId);
        List<Reservation> reservations = new ArrayList<Reservation>();

        while(rowSet.next()){
            reservations.add(mapRowToReservation(rowSet));
        }

        return reservations;
    }

    @Override
    public Reservation addReservation(Reservation newReservation) {
        // test if the start date is before the end date
        if(newReservation.getStartDate().isAfter(newReservation.getEndDate())){
            //throw invalid date span
            return null;
        } else {
            // Looks to see if anyone put in a reservation that interferes with the one being made
            String sql = "SELECT * FROM reservation " +
                    "WHERE room_id = ? AND (start_date BETWEEN ? AND ? " +
                    "OR end_date BETWEEN ? AND ? " +
                    "OR start_date < ? AND end_date > ?);";

            System.out.println(newReservation.getEndDate());
            SqlRowSet rowSet = null;

            try {

                rowSet = jdbcTemplate.queryForRowSet(sql, newReservation.getRoomId(), newReservation.getStartDate(), newReservation.getEndDate(), newReservation.getStartDate(), newReservation.getEndDate(), newReservation.getStartDate(), newReservation.getEndDate());
            }catch(Exception e){

            }
            //Creates a reservation only if room is clear for those days
            if (rowSet.next()) {
                //throw error if returns any interference
                throw new RuntimeException();
                //return null;
            } else {
                String insertSql = "INSERT INTO reservation (guest_id, room_id, start_date, end_date) VALUES (?, ?, ?, ?) RETURNING * ;";
                SqlRowSet results = jdbcTemplate.queryForRowSet(insertSql, newReservation.getGuestId(), newReservation.getRoomId(), newReservation.getStartDate(), newReservation.getEndDate());

                if(results.next()){
                    return mapRowToReservation(results);
                } else {
                    return null;
                }
            }
        }
    }

    @Override
    public void removeReservation(int reservationId) {
        String sql = "DELETE FROM reservation WHERE reservation_id = ?;";
        jdbcTemplate.update(sql, reservationId);
    }

    private Reservation mapRowToReservation(SqlRowSet rowSet){
        Reservation reservation = new Reservation();
        reservation.setReservationId(rowSet.getInt("reservation_id"));
        reservation.setGuestId(rowSet.getInt("guest_id"));
        reservation.setRoomId(rowSet.getInt("room_id"));
        reservation.setStartDate(rowSet.getDate("start_date").toLocalDate());
        reservation.setEndDate(rowSet.getDate("end_date").toLocalDate());

        return reservation;
    }
}
