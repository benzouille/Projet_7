package fr.biblioc.bibliocclientUi.beans.reservation;

import javax.validation.constraints.NotNull;

public class ExemplaireBean {

    //------------------------- ATTRIBUTS -------------------------

    private int id_exemplaire;

    @NotNull
    private int id_livre;

    @NotNull
    private int id_biblio;

    //------------------------- CONSTRUCTEUR -------------------------

    /**
     * constructeur
     */
    public ExemplaireBean() {
    }

    /**
     * Constructeur avec paramètres
     * @param id_livre id livre
     * @param id_biblio id bibliothèque
     */
    public ExemplaireBean(@NotNull int id_livre, @NotNull int id_biblio) {
        this.id_livre = id_livre;
        this.id_biblio = id_biblio;
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

    public int getId_biblio() {
        return id_biblio;
    }

    public void setId_biblio(int id_bibliotheque) {
        this.id_biblio = id_biblio;
    }

    //------------------------- TO_STRING -------------------------

    @Override
    public String toString() {
        return "Exemplaire{" +
                "id_exemplaire=" + id_exemplaire +
                ", id_livre=" + id_livre +
                ", id_biblio=" + id_biblio +
                '}';
    }
}
