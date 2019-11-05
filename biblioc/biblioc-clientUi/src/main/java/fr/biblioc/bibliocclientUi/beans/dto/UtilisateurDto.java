package fr.biblioc.bibliocclientUi.beans.dto;

import fr.biblioc.bibliocclientUi.beans.authentification.AdresseBean;
import fr.biblioc.bibliocclientUi.beans.authentification.RoleBean;
import fr.biblioc.bibliocclientUi.beans.authentification.UtilisateurBean;

/**
 * Objet à envoyer vers la vue contenant tout le neccessaire pour un utilisateur
 */
public class UtilisateurDto {

    //------------------------- ATTRIBUTS -------------------------

    private UtilisateurBean utilisateur;

    private AdresseBean adresse;

    private RoleBean role;

    //------------------------- CONSTRUCTEUR -------------------------

    /**
     * constructeur
     */
    public UtilisateurDto() {
    }

    /**
     * constructeur avec parametres
     * @param utilisateur bean {@link UtilisateurBean}
     * @param adresse bean {@link AdresseBean}
     * @param role bean {@link RoleBean}
     */
    public UtilisateurDto(UtilisateurBean utilisateur, AdresseBean adresse, RoleBean role) {
        this.utilisateur = utilisateur;
        this.adresse = adresse;
        this.role = role;
    }

    //------------------------- GETTER/SETTER -------------------------

    public UtilisateurBean getUtilisateur() {
            return utilisateur;
        }

        public void setUtilisateur(UtilisateurBean utilisateur) {
            this.utilisateur = utilisateur;
        }

        public AdresseBean getAdresse() {
            return adresse;
        }

        public void setAdresse(AdresseBean adresse) {
            this.adresse = adresse;
        }

        public RoleBean getRole() {
            return role;
        }

        public void setRole(RoleBean role) {
            this.role = role;
        }

        //------------------------- TO_STRING -------------------------

        @Override
        public String toString() {
            return "UtilisateurDto{" +
                    "utilisateur=" + utilisateur +
                    ", adresse=" + adresse +
                    ", role=" + role +
                    '}';
        }
}
