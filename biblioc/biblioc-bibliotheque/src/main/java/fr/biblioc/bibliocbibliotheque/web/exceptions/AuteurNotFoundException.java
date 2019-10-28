package fr.biblioc.bibliocbibliotheque.web.exceptions;

public class AuteurNotFoundException extends RuntimeException {
    public AuteurNotFoundException(String message) {
        super(message);
    }
}
