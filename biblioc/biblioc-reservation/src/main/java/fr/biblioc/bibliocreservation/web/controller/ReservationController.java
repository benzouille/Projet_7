package fr.biblioc.bibliocreservation.web.controller;

import fr.biblioc.bibliocreservation.dao.ReservationDao;
import fr.biblioc.bibliocreservation.model.Reservation;
import fr.biblioc.bibliocreservation.web.exceptions.ErrorAddException;
import fr.biblioc.bibliocreservation.web.exceptions.ObjectNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controller de la classe {@link Reservation}
 */
@RestController
public class ReservationController implements HealthIndicator {

    //------------------------- ATTRIBUTS -------------------------

    @Autowired
    ReservationDao reservationDao;

    Logger log = LoggerFactory.getLogger(this.getClass());

    //------------------------- METHODE -------------------------

    /**
     * Indique le status du microservice
     * @return etat du microservice
     */
    @Override
    public Health health() {
        List<Reservation> reservations = reservationDao.findAll();

        if(reservations.isEmpty()) {
            return Health.down().build();
        }
        return Health.up().build();
    }

    /**
     * Affiche la liste de toutes les reservations
     * @return liste de {@link Reservation}
     */
    @GetMapping(value = "/Reservations")
    public List<Reservation> listeDesReservations(){

        List<Reservation> reservations = reservationDao.findAll();

        if(reservations.isEmpty()){
            throw new ObjectNotFoundException("Aucune reservation n'a été trouvée");
        }

        log.info("Récupération de la liste des reservations");

        return reservations;

    }

    /**
     * Récuperer une reservation par son id
     * @param id int
     * @return bean {@link Reservation}
     */
    @GetMapping( value = "/Reservations/{id}")
    public Optional<Reservation> recupererUneReservation(@PathVariable int id) {

        Optional<Reservation> reservation = reservationDao.findById(id);

        if(!reservation.isPresent())  throw new ObjectNotFoundException("La reservation correspondant à l'id " + id + " n'existe pas");

        return reservation;
    }

    /**
     * Ajouter un reservation
     * @param reservation bean {@link Reservation}
     * @return ResponseEntity<Reservation> renvoi un http status.
     */
    @PostMapping(value = "/reservations")
    public ResponseEntity<Reservation> addReservation(Reservation reservation){

        Reservation newReservation = reservationDao.save(reservation);

        if(newReservation == null) throw new ErrorAddException("Impossible d'ajouter ce reservation");

        return new ResponseEntity<Reservation>(reservation, HttpStatus.CREATED);
    }

    /**
     * Permet de mettre à jour un reservation existant.
     **/
    @PutMapping(value = "/reservations")
    public void updateReservation(@RequestBody Reservation reservation) {

        reservationDao.save(reservation);
    }
}
