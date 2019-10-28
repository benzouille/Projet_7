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
    private int id;

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
     * @param id int
     * @param genre string
     */
    public Genre(int id, @NotNull @Max(100) String genre) {
        this.id = id;
        this.genre = genre;
    }

    //------------------------- GETTER/SETTER -------------------------

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
                "id=" + id +
                ", genre='" + genre + '\'' +
                '}';
    }
}
