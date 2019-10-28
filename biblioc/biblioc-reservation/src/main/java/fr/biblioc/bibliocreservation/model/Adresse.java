package fr.biblioc.bibliocbibliotheque.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

/**
 * Bean adresse contenant l'adresse complète pour les utilisateurs et pour les bibliothèques.
 */
@Entity
public class Adresse {

    //------------------------- ATTRIBUTS -------------------------

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Max(6)
    private String cp;

    @NotNull
    @Max(200)
    private String rue;

    @NotNull
    @Max(5)
    private String num;

    @NotNull
    @Max(200)
    private String commune;

    //------------------------- CONSTRUCTEUR -------------------------

    /**
     * constructeur
     */
    public Adresse() {
    }

    /**
     * Constructeur avec parametres
     * @param cp String code postal
     * @param rue String rue
     * @param num String numéro
     * @param commune String commune
     */
    public Adresse(@NotNull @Max(6) String cp, @NotNull @Max(200) String rue, @NotNull @Max(5) String num, @NotNull @Max(200) String commune) {
        this.cp = cp;
        this.rue = rue;
        this.num = num;
        this.commune = commune;
    }

    //------------------------- GETTER/SETTER -------------------------

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getCommune() {
        return commune;
    }

    public void setCommune(String commune) {
        this.commune = commune;
    }

    //------------------------- TO_STRING -------------------------

    @Override
    public String toString() {
        return "Adresse{" +
                "id=" + id +
                ", cp='" + cp + '\'' +
                ", rue='" + rue + '\'' +
                ", num='" + num + '\'' +
                ", commune='" + commune + '\'' +
                '}';
    }
}
