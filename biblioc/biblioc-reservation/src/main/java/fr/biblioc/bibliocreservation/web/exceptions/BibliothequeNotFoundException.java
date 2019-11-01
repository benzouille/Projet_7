package fr.biblioc.bibliocreservation.web.exceptions;

public class BibliothequeNotFoundException extends RuntimeException {
    public BibliothequeNotFoundException(String message) {
        super(message);
    }
}
