package fr.biblioc.bibliocbibliotheque.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

/**
 * Bean bibliothèque avec un nom et un bean adresse
 */
@Entity
public class Bibliotheque {

    //------------------------- ATTRIBUTS -------------------------

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Max(100)
    private String nom;

    @NotNull
    private int id_addresse;

    //------------------------- CONSTRUCTEUR -------------------------

    /**
     * constructeur
     */
    public Bibliotheque() {
    }

    /**
     * Constructeur avec paramètres
     * @param nom String nom
     * @param id_addresse int id_adresse
     */
    public Bibliotheque(@NotNull @Max(100) String nom, @NotNull int id_addresse) {
        this.nom = nom;
        this.id_addresse = id_addresse;
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

    public int getId_addresse() {
        return id_addresse;
    }

    public void setId_addresse(int id_addresse) {
        this.id_addresse = id_addresse;
    }

    //------------------------- TO_STRING -------------------------

    @Override
    public String toString() {
        return "Bibliotheque{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", id_addresse=" + id_addresse +
                '}';
    }
}
