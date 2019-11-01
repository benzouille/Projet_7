package fr.biblioc.bibliocreservation.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

/**
 * Bean Bibliothèque representant la table bibliotheque de la bdd
 */
@Entity
public class Bibliotheque {

    //------------------------- ATTRIBUTS -------------------------

    @Id
    @GeneratedValue
    private int id_biblio;

    @NotNull
    @Max(100)
    private String nom;

    @NotNull
    private int id_adresse;

    //------------------------- CONSTRUCTEUR -------------------------

    /**
     * constructeur
     */
    protected Bibliotheque() {
    }

    /**
     * Constructeur avec paramètres
     * @param nom String nom
     * @param id_adresse int id_adresse
     */
    public Bibliotheque(@NotNull @Max(100) String nom, @NotNull int id_adresse) {
        this.nom = nom;
        this.id_adresse = id_adresse;
    }

    //------------------------- GETTER/SETTER -------------------------

    public int getid_biblio() {
        return id_biblio;
    }

    public void setid_biblio(int id_biblio) {
        this.id_biblio = id_biblio;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getId_adresse() {
        return id_adresse;
    }

    public void setId_adresse(int id_adresse) {
        this.id_adresse = id_adresse;
    }

    //------------------------- TO_STRING -------------------------

    @Override
    public String toString() {
        return "Bibliotheque{" +
                "id_biblio=" + id_biblio +
                ", nom='" + nom + '\'' +
                ", id_adresse=" + id_adresse +
                '}';
    }
}