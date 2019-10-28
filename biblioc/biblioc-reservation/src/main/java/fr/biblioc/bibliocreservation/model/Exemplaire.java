package fr.biblioc.bibliocbibliotheque.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * Bean Exemplaire correspondant à la table exemplaire de la bdd
 */
@Entity
public class Exemplaire {

    //------------------------- ATTRIBUTS -------------------------

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    private int id_livre;

    @NotNull
    private int id_bibliotheque;

    //------------------------- CONSTRUCTEUR -------------------------

    /**
     * constructeur
     */
    public Exemplaire() {
    }

    /**
     * Constructeur avec paramètres
     * @param id_livre id livre
     * @param id_bibliotheque id bibliothèque
     */
    public Exemplaire(@NotNull int id_livre, @NotNull int id_bibliotheque) {
        this.id_livre = id_livre;
        this.id_bibliotheque = id_bibliotheque;
    }

    //------------------------- GETTER/SETTER -------------------------

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_livre() {
        return id_livre;
    }

    public void setId_livre(int id_livre) {
        this.id_livre = id_livre;
    }

    public int getId_bibliotheque() {
        return id_bibliotheque;
    }

    public void setId_bibliotheque(int id_bibliotheque) {
        this.id_bibliotheque = id_bibliotheque;
    }

    //------------------------- TO_STRING -------------------------

    @Override
    public String toString() {
        return "Exemplaire{" +
                "id=" + id +
                ", id_livre=" + id_livre +
                ", id_bibliotheque=" + id_bibliotheque +
                '}';
    }
}