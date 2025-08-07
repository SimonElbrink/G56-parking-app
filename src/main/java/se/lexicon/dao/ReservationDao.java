package se.lexicon.dao;

import se.lexicon.model.Reservation;

import java.util.List;
import java.util.Optional;

public interface ReservationDao {

    Reservation create(Reservation reservation);

    Optional<Reservation> findById(String reservationId);

    List<Reservation> findAll();

    void update(Reservation reservation);

}
