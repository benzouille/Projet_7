package fr.biblioc.bibliocbibliotheque.model.dto;

import fr.biblioc.bibliocbibliotheque.model.Auteur;
import fr.biblioc.bibliocbibliotheque.model.Editeur;
import fr.biblioc.bibliocbibliotheque.model.Genre;
import fr.biblioc.bibliocbibliotheque.model.Livre;

import java.util.ArrayList;
import java.util.List;

/**
 * Objet à envoyer vers le client_ui contenant tout le neccessaire pour un livre
 */
public class LivreDto {

    //------------------------- ATTRIBUTS -------------------------

    private Livre livre;

    private Editeur editeur;

    private List<Auteur> auteurs = new ArrayList<>();

    private Genre genre;

    //------------------------- CONSTRUCTEUR -------------------------

    /**
     * constructeur
     */
    protected LivreDto(){

    }

    /**
     * constructeur avec parametres
     * @param livre bean {@link Livre}
     * @param editeur bean {@link Editeur}
     * @param auteurs List de bean {@link Auteur}
     * @param genre bean {@link Genre}
     */
    public LivreDto(Livre livre, Editeur editeur, List<Auteur> auteurs, Genre genre) {
        this.livre = livre;
        this.editeur = editeur;
        this.auteurs = auteurs;
        this.genre = genre;
    }

    //------------------------- GETTER/SETTER -------------------------

    public Livre getLivre() {
        return livre;
    }

    public void setLivre(Livre livre) {
        this.livre = livre;
    }

    public Editeur getEditeur() {
        return editeur;
    }

    public void setEditeur(Editeur editeur) {
        this.editeur = editeur;
    }

    public List<Auteur> getAuteurs() {
        return auteurs;
    }

    public void setAuteurs(List<Auteur> auteurs) {
        this.auteurs = auteurs;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    //------------------------- TO_STRING -------------------------

    @Override
    public String toString() {
        return "LivreDto{" +
                "livre=" + livre +
                ", editeur=" + editeur +
                ", auteurs=" + auteurs +
                ", genre=" + genre +
                '}';
    }
}
