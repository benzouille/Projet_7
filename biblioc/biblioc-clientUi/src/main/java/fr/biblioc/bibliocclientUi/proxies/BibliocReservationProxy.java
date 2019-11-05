package fr.biblioc.bibliocclientUi.proxies;

import fr.biblioc.bibliocclientUi.beans.reservation.ReservationBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * Proxy du microservice reservation.
 */
@FeignClient(name = "biblioc-reservation", url = "localhost:9003")
public interface BibliocReservationProxy {

    @GetMapping(value = "/Reservations")
    List<ReservationBean> listReservations();

    @GetMapping(value = "/Reservations/{id}")
    ReservationBean getReservation(@PathVariable("id") int id);

    @PostMapping(value = "/Reservations/reservation")
    ReservationBean newReservation(@RequestBody ReservationBean reservation);

}
