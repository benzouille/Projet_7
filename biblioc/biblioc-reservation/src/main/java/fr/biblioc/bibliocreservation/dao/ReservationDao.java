package fr.biblioc.bibliocreservation.dao;

import fr.biblioc.bibliocreservation.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationDao extends JpaRepository<Reservation, Integer> {
}
