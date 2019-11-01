package fr.biblioc.bibliocreservation.web.exceptions;

public class ReservationNotFoundException  extends RuntimeException {
    public ReservationNotFoundException(String message) {
        super(message);
    }
}
