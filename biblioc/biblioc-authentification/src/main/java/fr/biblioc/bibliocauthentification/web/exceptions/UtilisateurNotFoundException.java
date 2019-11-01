package fr.biblioc.bibliocauthentification.web.exceptions;

public class UtilisateurNotFoundException extends RuntimeException {
    public UtilisateurNotFoundException(String message) {
        super(message);
    }
}
