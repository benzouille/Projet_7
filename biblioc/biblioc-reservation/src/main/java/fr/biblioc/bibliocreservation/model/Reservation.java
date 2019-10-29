package fr.biblioc.bibliocreservation.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.sql.Date;

/**
 * Bean Reservation representant la table reservation de la bdd
 */
@Entity
public class Reservation {

    //------------------------- ATTRIBUTS -------------------------

    @Id
    @GeneratedValue
    private int id_reservation;

    @NotNull
    private int id_utilisateur;

    @NotNull
    private Date date_emprunt;

    @NotNull
    private Boolean extension;

    @NotNull
    private int id_exemplaire;

    //------------------------- CONSTRUCTEUR -------------------------

    /**
     * constructeur
     */
    public Reservation() {
    }

    /**
     * constructeur avec parametres
     * @param id_utilisateur int
     * @param date_emprunt Date
     * @param extension boolean
     * @param id_exemplaire int
     */
    public Reservation(@NotNull int id_utilisateur, @NotNull Date date_emprunt, @NotNull Boolean extension, @NotNull int id_exemplaire) {
        this.id_utilisateur = id_utilisateur;
        this.date_emprunt = date_emprunt;
        this.extension = extension;
        this.id_exemplaire = id_exemplaire;
    }

    //------------------------- GETTER/SETTER -------------------------

    public int getId_reservation() {
        return id_reservation;
    }

    public void setId_reservation(int id_reservation) {
        this.id_reservation = id_reservation;
    }

    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    public Date getDate_emprunt() {
        return date_emprunt;
    }

    public void setDate_emprunt(Date date_emprunt) {
        this.date_emprunt = date_emprunt;
    }

    public Boolean getExtension() {
        return extension;
    }

    public void setExtension(Boolean extension) {
        this.extension = extension;
    }

    public int getId_exemplaire() {
        return id_exemplaire;
    }

    public void setId_exemplaire(int id_exemplaire) {
        this.id_exemplaire = id_exemplaire;
    }

    //------------------------- TO_STRING -------------------------

    @Override
    public String toString() {
        return "Reservation{" +
                "id_reservation=" + id_reservation +
                ", id_utilisateur=" + id_utilisateur +
                ", date_emprunt=" + date_emprunt +
                ", extension=" + extension +
                ", id_exemplaire=" + id_exemplaire +
                '}';
    }
}
