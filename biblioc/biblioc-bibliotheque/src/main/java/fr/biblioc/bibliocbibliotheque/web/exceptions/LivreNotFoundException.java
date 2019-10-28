package fr.biblioc.bibliocbibliotheque.web.exceptions;

public class LivreNotFoundException extends RuntimeException {
    public LivreNotFoundException(String message) {
        super(message);
    }
}
