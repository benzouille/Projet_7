package fr.biblioc.bibliocclientUi.beans.reservation;

import fr.biblioc.bibliocclientUi.beans.authentification.CompteBean;
import fr.biblioc.bibliocclientUi.beans.utilisateur.UtilisateurBean;

import javax.validation.constraints.NotNull;
import java.sql.Date;

/**
 * Bean Reservation coté client
 */
public class ReservationBean {
    //------------------------- ATTRIBUTS -------------------------

    private int id_reservation;

    @NotNull
    private int id_utilisateur;

    private UtilisateurBean utilisateur;

    @NotNull
    private Date date_emprunt;

    @NotNull
    private Boolean extension;

    @NotNull
    private ExemplaireBean exemplaire;

    //------------------------- CONSTRUCTEUR -------------------------

    /**
     * constructeur
     */
    public ReservationBean() {
    }

    /**
     * constructeur avec parametres
     * @param id_utilisateur int
     * @param date_emprunt Date
     * @param extension boolean
     * @param exemplaire ExemplaireBean
     */
    public ReservationBean(@NotNull int id_utilisateur, @NotNull Date date_emprunt, @NotNull Boolean extension, @NotNull ExemplaireBean exemplaire) {
        this.id_utilisateur = id_utilisateur;
        this.date_emprunt = date_emprunt;
        this.extension = extension;
        this.exemplaire = exemplaire;
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

    public ExemplaireBean getExemplaire() {
        return exemplaire;
    }

    public void setExemplaire(ExemplaireBean exemplaire) {
        this.exemplaire = exemplaire;
    }

    public UtilisateurBean getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(UtilisateurBean utilisateur) {
        this.utilisateur = utilisateur;
    }

    //------------------------- TO_STRING -------------------------

    @Override
    public String toString() {
        return "Reservation{" +
                "id_reservation=" + id_reservation +
                ", id_utilisateur=" + id_utilisateur +
                ", date_emprunt=" + date_emprunt +
                ", extension=" + extension +
                ", exemplaire=" + exemplaire +
                '}';
    }
}
