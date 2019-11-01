package fr.biblioc.bibliocauthentification.model.dto;

import fr.biblioc.bibliocauthentification.model.Adresse;
import fr.biblioc.bibliocauthentification.model.Role;
import fr.biblioc.bibliocauthentification.model.Utilisateur;

/**
 * Objet à envoyer vers le client_ui contenant tout le neccessaire pour un utilisateur
 */
public class UtilisateurDto {

    //------------------------- ATTRIBUTS -------------------------

    private Utilisateur utilisateur;

    private Adresse adresse;

    private Role role;

    //------------------------- CONSTRUCTEUR -------------------------

    /**
     * constructeur
     */
    public UtilisateurDto() {
    }

    /**
     * constructeur avec parametres
     * @param utilisateur bean {@link Utilisateur}
     * @param adresse bean {@link Adresse}
     * @param role bean {@link Role}
     */
    public UtilisateurDto(Utilisateur utilisateur, Adresse adresse, Role role) {
        this.utilisateur = utilisateur;
        this.adresse = adresse;
        this.role = role;
    }

    //------------------------- GETTER/SETTER -------------------------

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    //------------------------- TO_STRING -------------------------

    @Override
    public String toString() {
        return "UtilisateurDto{" +
                "utilisateur=" + utilisateur +
                ", adresse=" + adresse +
                ", role=" + role +
                '}';
    }
}
