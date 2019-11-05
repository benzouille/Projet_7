package fr.biblioc.bibliocclientUi.beans.authentification;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

/**
 * Bean Utilisateur coté client
 */
public class UtilisateurBean {

    //------------------------- ATTRIBUTS -------------------------

    private int id_utilisateur;

    @NotNull
    @Max(100)
    private String nom;

    @NotNull
    @Max(100)
    private String prenom;

    @NotNull
    @Max(100)
    private String email;

    @NotNull
    @Max(100)
    private String password;

    @NotNull
    @Max(100)
    private int telephone;

    @NotNull
    private int id_role;

    @NotNull
    private int id_adresse;

    //------------------------- CONSTRUCTEUR -------------------------

    /**
     * constructeur
     */
    public UtilisateurBean() {
    }

    /**
     * constructeur avec parametres
     * @param nom string
     * @param prenom string
     * @param email string
     * @param password string
     * @param telephone int
     * @param id_role int
     * @param id_adresse int
     */
    public UtilisateurBean(@NotNull @Max(100) String nom, @NotNull @Max(100) String prenom, @NotNull @Max(100) String email, @NotNull @Max(100) String password, @NotNull @Max(100) int telephone, @NotNull int id_role, @NotNull int id_adresse) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.telephone = telephone;
        this.id_role = id_role;
        this.id_adresse = id_adresse;
    }

    //------------------------- GETTER/SETTER -------------------------

    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public int getId_role() {
        return id_role;
    }

    public void setId_role(int id_role) {
        this.id_role = id_role;
    }

    public int getId_adresse() {
        return id_adresse;
    }

    public void setId_adresse(int id_adresse) {
        this.id_adresse = id_adresse;
    }

    //------------------------- TO_STRING -------------------------

    @Override
    public String toString() {
        return "Utilisateur{" +
                "id_utilisateur=" + id_utilisateur +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", telephone=" + telephone +
                ", id_role=" + id_role +
                ", id_adresse=" + id_adresse +
                '}';
    }
}
