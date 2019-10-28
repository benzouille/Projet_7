package fr.biblioc.bibliocbibliotheque.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Bean auteur
 */
@Entity
@Table(name = "auteur", schema = "public", catalog = "postgres")
public class Auteur {

    //------------------------- ATTRIBUTS -------------------------

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_auteur")
    private int id;

    @NotBlank
    @Max(50)
    @Column(name = "nom")
    private String nom;

    @NotBlank
    @Max(50)
    @Column(name = "prenom")
    private String prenom;

    @NotBlank
    @Column(name = "date_naissance")
    private Date date_naissance;

    @Column(name = "date_deces")
    private Date date_deces;

    //------------------------- CONSTRUCTEUR -------------------------

    /**
     * constructeur
     */
    protected Auteur() {
    }

    /**
     * constructeur avec parametres
     * @param nom string
     * @param prenom string
     * @param date_naissance Date
     * @param date_deces Date
     */
    public Auteur(@NotNull @Max(50) String nom, @NotNull @Max(50) String prenom, @NotNull Date date_naissance, Date date_deces) {
        this.nom = nom;
        this.prenom = prenom;
        this.date_naissance = date_naissance;
        this.date_deces = date_deces;
    }

    //------------------------- GETTER/SETTER -------------------------

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Date getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(Date date_naissance) {
        this.date_naissance = date_naissance;
    }

    public Date getDate_deces() {
        return date_deces;
    }

    public void setDate_deces(Date date_deces) {
        this.date_deces = date_deces;
    }

    //------------------------- TO_STRING -------------------------

    @Override
    public String toString() {
        return "Auteur{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", date_naissance=" + date_naissance +
                ", date_deces=" + date_deces +
                '}';
    }
}
