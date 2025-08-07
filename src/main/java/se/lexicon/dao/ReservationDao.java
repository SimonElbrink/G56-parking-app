package se.lexicon.dao;

import se.lexicon.model.Reservation;

import java.util.List;

public interface ReservationDao {

    Reservation create(Reservation reservation);

    Reservation findById(String reservationId);

    List<Reservation> findAll();

    void update(Reservation reservation);

}
