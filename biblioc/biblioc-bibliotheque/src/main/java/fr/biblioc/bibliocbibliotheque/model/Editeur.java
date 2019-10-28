package fr.biblioc.bibliocbibliotheque.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

/**
 * Bean editeur
 */
@Entity
public class Editeur {

    //------------------------- ATTRIBUTS -------------------------

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Max(100)
    private String nom;

    //------------------------- CONSTRUCTEUR -------------------------

    /**
     * constructeur
     */
    public Editeur() {
    }

    /**
     * constructeur avec parametres
     * @param nom string
     */
    public Editeur(@NotNull @Max(100) String nom) {
        this.nom = nom;
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

    //------------------------- TO_STRING -------------------------

    @Override
    public String toString() {
        return "Editeur{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                '}';
    }
}
