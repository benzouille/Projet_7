package fr.biblioc.bibliocclientUi.beans.reservation;

import fr.biblioc.bibliocclientUi.beans.bibliotheque.LivreBean;

import javax.validation.constraints.NotNull;

public class ExemplaireBean {

    //------------------------- ATTRIBUTS -------------------------

    private int id_exemplaire;

    @NotNull
    private int id_livre;

    private LivreBean livre;

    @NotNull
    private BibliothequeBean bibliotheque;

    //------------------------- CONSTRUCTEUR -------------------------

    /**
     * constructeur
     */
    public ExemplaireBean() {
    }

    /**
     * Constructeur avec paramètres
     * @param id_livre id livre
     * @param bibliotheque Objet bibliothèque
     */
    public ExemplaireBean(@NotNull int id_livre, @NotNull BibliothequeBean bibliotheque) {
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

    public LivreBean getLivre() {
        return livre;
    }

    public void setLivre(LivreBean livre) {
        this.livre = livre;
    }

    public BibliothequeBean getbibliotheque() {
        return bibliotheque;
    }

    public void setbibliotheque(BibliothequeBean bibliotheque) {
        this.bibliotheque = bibliotheque;
    }

    //------------------------- TO_STRING -------------------------

    @Override
    public String toString() {
        return "Exemplaire{" +
                "id_exemplaire=" + id_exemplaire +
                ", id_livre=" + id_livre +
                ", bibliotheque=" + bibliotheque +
                '}';
    }
}
