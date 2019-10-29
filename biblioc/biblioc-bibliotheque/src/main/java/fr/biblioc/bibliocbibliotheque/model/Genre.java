package fr.biblioc.bibliocbibliotheque.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

/**
 * Bean genre
 */
@Entity
public class Genre {

    //------------------------- ATTRIBUTS -------------------------

    @Id
    @GeneratedValue
    private int id_genre;

    @NotNull
    @Max(100)
    private String genre;

    //------------------------- CONSTRUCTEUR -------------------------

    /**
     * constructeur
     */
    public Genre() {
    }

    /**
     * constructeur avec parametres
     * @param id_genre int
     * @param genre string
     */
    public Genre(int id_genre, @NotNull @Max(100) String genre) {
        this.id_genre = id_genre;
        this.genre = genre;
    }

    //------------------------- GETTER/SETTER -------------------------

    public int getid_genre() {
        return id_genre;
    }

    public void setid_genre(int id_genre) {
        this.id_genre = id_genre;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    //------------------------- TO_STRING -------------------------

    @Override
    public String toString() {
        return "Genre{" +
                "id_genre=" + id_genre +
                ", genre='" + genre + '\'' +
                '}';
    }
}
