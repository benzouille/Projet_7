package fr.biblioc.bibliocbibliotheque.model.dto;

import fr.biblioc.bibliocbibliotheque.model.Auteur;
import fr.biblioc.bibliocbibliotheque.model.Editeur;
import fr.biblioc.bibliocbibliotheque.model.Livre;

import java.util.ArrayList;
import java.util.List;

public class LivreDto {

    private Livre livre;

    private Editeur editeur;

    private List<Auteur> auteurs = new ArrayList<>();



    public LivreDto(Livre livre, Editeur editeur, List<Auteur> auteurs) {
        this.livre = livre;
        this.editeur = editeur;
        this.auteurs = auteurs;
    }

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

    @Override
    public String toString() {
        return "LivreDto{" +
                "livre=" + livre +
                ", editeur=" + editeur +
                ", auteurs=" + auteurs +
                '}';
    }
}
