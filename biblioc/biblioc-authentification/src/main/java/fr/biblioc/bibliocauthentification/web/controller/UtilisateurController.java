package fr.biblioc.bibliocauthentification.web.controller;

import fr.biblioc.bibliocauthentification.dao.UtilisateurDao;
import fr.biblioc.bibliocauthentification.model.Utilisateur;
import fr.biblioc.bibliocauthentification.web.exceptions.ErrorAddException;
import fr.biblioc.bibliocauthentification.web.exceptions.ObjectNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controller de la classe {@link Utilisateur}
 */
@RestController
public class UtilisateurController implements HealthIndicator {

    //------------------------- ATTRIBUTS -------------------------

    @Autowired
    UtilisateurDao utilisateurDao;

    Logger log = LoggerFactory.getLogger(this.getClass());

    //------------------------- METHODE -------------------------

    /**
     * Indique le status du microservice
     * @return etat du microservice
     */
    @Override
    public Health health() {
        List<Utilisateur> utilisateurs = utilisateurDao.findAll();

        if(utilisateurs.isEmpty()) {
            return Health.down().build();
        }
        return Health.up().build();
    }

    /**
     * Affiche la liste de tous les utilisateurs
     * @return liste {@link Utilisateur}
     */
    @GetMapping(value = "/Utilisateurs")
    public List<Utilisateur> listeDesUtilisateurs(){

        List<Utilisateur> utilisateurs = utilisateurDao.findAll();

        if(utilisateurs.isEmpty()){
            throw new ObjectNotFoundException("Aucun utilisateur n'a été trouvée");
        }

        log.info("Récupération de la liste des utilisateurs");

        return utilisateurs;

    }

    /**
     * Récuperer un utilisateur par son id
     * @param id int
     * @return bean {@link Utilisateur}
     */
    @GetMapping( value = "/Utilisateurs/{id}")
    public Optional<Utilisateur> recupererUnUtilisateur(@PathVariable int id) {

        Optional<Utilisateur> utilisateur = utilisateurDao.findById(id);

        if(!utilisateur.isPresent())  throw new ObjectNotFoundException("L'utilisateur correspondant à l'id " + id + " n'existe pas");

        return utilisateur;
    }

    /**
     * Ajouter un utilisateur
     * @param utilisateur bean {@link Utilisateur}
     * @return ResponseEntity<Utilisateur> renvoi un http status.
     */
    @PostMapping(value = "/Nutilisateurs")
    public ResponseEntity<Utilisateur> addUtilisateur(Utilisateur utilisateur){

        Utilisateur newUtilisateur = utilisateurDao.save(utilisateur);

        if(newUtilisateur == null) throw new ErrorAddException("Impossible d'ajouter ce utilisateur");

        return new ResponseEntity<Utilisateur>(utilisateur, HttpStatus.CREATED);
    }

    /**
     * Permet de mettre à jour un utilisateur existant.
     **/
    @PutMapping(value = "/Uutilisateurs")
    public void updateUtilisateur(@RequestBody Utilisateur utilisateur) {

        utilisateurDao.save(utilisateur);
    }
}
