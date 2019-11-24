package fr.biblioc.bibliocclientUi.beans.authentification;

import javax.validation.constraints.NotNull;

/**
 * Bean Compte coté client
 */
public class CompteBean {

    //------------------------- ATTRIBUTS -------------------------

    private int id_compte;

    @NotNull
    private String email;

    @NotNull
    private String password;

    private int id_role;

    private int id_utilisateur;

    //------------------------- CONSTRUCTEURS -------------------------

    /**
     * Constructeur
     */
    public CompteBean() {
    }

    /**
     * Constructeur avec paramètres
     * @param id_compte int peux être null
     * @param email String not null
     * @param password String not null
     * @param id_role int peux être null
     * @param id_utilisateur int peux être null
     */
    public CompteBean(int id_compte, @NotNull String email, @NotNull String password, int id_role, int id_utilisateur) {
        this.id_compte = id_compte;
        this.email = email;
        this.password = password;
        this.id_role = id_role;
        this.id_utilisateur = id_utilisateur;
    }

    //------------------------- GETTER/SETTER -------------------------

    public int getId_compte() {
        return id_compte;
    }

    public void setId_compte(int id_compte) {
        this.id_compte = id_compte;
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

    public int getId_role() {
        return id_role;
    }

    public void setId_role(int id_role) {
        this.id_role = id_role;
    }

    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    //------------------------- TO STRING -------------------------

    @Override
    public String toString() {
        return "CompteBean{" +
                "id_compte=" + id_compte +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", id_role=" + id_role +
                ", id_utilisateur=" + id_utilisateur +
                '}';
    }
}
