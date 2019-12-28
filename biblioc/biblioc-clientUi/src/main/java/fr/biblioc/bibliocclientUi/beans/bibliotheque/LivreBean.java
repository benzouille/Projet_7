package fr.biblioc.bibliocclientUi.beans.bibliotheque;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

/**
 * Bean livre coté client
 */
public class LivreBean {

    //------------------------- ATTRIBUTS -------------------------

    private int id_livre;

    @NotNull
    private String isbn13;

    @NotNull
    private int id_genre;

    @NotNull
    private String titre;

    @NotNull
    private String resume;

    private String image;

    @NotNull
    @Max(4)
    private int annee_parution;

    @NotNull
    private int id_editeur;

    //------------------------- CONSTRUCTEUR -------------------------

    /**
     * constructeur
     */
    public LivreBean() {
    }

    /**
     * Constructeur avec ses parametres
     * @param isbn13-
     * @param id_genre-
     * @param titre-
     * @param resume-
     * @param image-
     * @param annee_parution-
     * @param id_editeur-
     */
    public LivreBean(@NotNull @Max(50) String isbn13, @NotNull int id_genre, @NotNull @Max(200) String titre, @NotNull @Max(1000) String resume, String image, @NotNull @Max(4) int annee_parution, @NotNull int id_editeur) {
        this.isbn13 = isbn13;
        this.id_genre = id_genre;
        this.titre = titre;
        this.resume = resume;
        this.image = image;
        this.annee_parution = annee_parution;
        this.id_editeur = id_editeur;
    }
//------------------------- GETTER/SETTER -------------------------

    public int getid_livre() {
        return id_livre;
    }

    public void setid_livre(int id_livre) {
        this.id_livre = id_livre;
    }

    public String getIsbn13() {
        return isbn13;
    }

    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }

    public int getId_genre() {
        return id_genre;
    }

    public void setId_genre(int id_genre) {
        this.id_genre = id_genre;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getAnnee_parution() {
        return annee_parution;
    }

    public void setAnnee_parution(int annee_parution) {
        this.annee_parution = annee_parution;
    }

    public int getId_editeur() {
        return id_editeur;
    }

    public void setId_editeur(int id_editeur) {
        this.id_editeur = id_editeur;
    }

//------------------------- TO_STRING -------------------------

    @Override
    public String toString() {
        return "Livre{" +
                "id_livre=" + id_livre +
                ", isbn13='" + isbn13 + '\'' +
                ", id_genre=" + id_genre +
                ", titre='" + titre + '\'' +
                ", resume='" + resume + '\'' +
                ", image='" + image + '\'' +
                ", annee_parution=" + annee_parution +
                ", id_editeur=" + id_editeur +
                '}';
    }
}
