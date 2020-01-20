package fr.biblioc.bibliocreservation.dto;

import fr.biblioc.bibliocreservation.model.Bibliotheque;

/**
 * Dto de l'objet Exemplaire
 */
public class ExemplaireDto {

    //------------------------- ATTRIBUTS -------------------------

    private int id_exemplaire;

    private int id_livre;

    private Bibliotheque bibliotheque;

    //------------------------- CONSTRUCTEUR -------------------------

    /**
     * constructeur
     */
    public ExemplaireDto() {
    }

    /**
     * Constructeur avec paramètres
     * @param id_livre id livre
     * @param bibliotheque Objet bibliothèque
     */
    public ExemplaireDto(int id_livre, Bibliotheque bibliotheque) {
        this.id_livre = id_livre;
        this.bibliotheque = bibliotheque;
    }

    //------------------------- GETTER/SETTER -------------------------

    public int getid_exemplaire() {
        return id_exemplaire;
    }

    public void setid_exemplaire(int id_exemplaire) {
        this.id_exemplaire = id_exemplaire;
    }

    public int getId_livre() {
        return id_livre;
    }

    public void setId_livre(int id_livre) {
        this.id_livre = id_livre;
    }

    public Bibliotheque getBibliotheque() {
        return bibliotheque;
    }

    public void setBibliotheque(Bibliotheque id_bibliotheque) {
        this.bibliotheque = bibliotheque;
    }

    //------------------------- TO_STRING -------------------------

    @Override
    public String toString() {
        return "Exemplaire{" +
                "id_exemplaire=" + id_exemplaire +
                ", id_livre=" + id_livre +
                ", id_biblio=" + bibliotheque +
                '}';
    }
}