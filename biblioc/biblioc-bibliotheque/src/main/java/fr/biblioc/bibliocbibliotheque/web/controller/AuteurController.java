package fr.biblioc.bibliocbibliotheque.web.controller;

import fr.biblioc.bibliocbibliotheque.dao.AuteurDao;
import fr.biblioc.bibliocbibliotheque.model.Auteur;
import fr.biblioc.bibliocbibliotheque.web.exceptions.AuteurNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * Controller de la classe {@link Auteur}
 */
@RestController
public class AuteurController implements HealthIndicator {

    //------------------------- ATTRIBUTS -------------------------

    @Autowired
    AuteurDao auteurDao;

    Logger log = LoggerFactory.getLogger(this.getClass());

    //------------------------- METHODE -------------------------

    /**
     * Indique le status du microservice
     * @return etat du microservice
     */
    @Override
    public Health health() {
        List<Auteur> auteurs = auteurDao.findAll();

        if(auteurs.isEmpty()) {
            return Health.down().build();
        }
        return Health.up().build();
    }

    /**
     * Affiche la liste de tous les auteurs
     * @return liste d'auteurs
     */
    @GetMapping(value = "/Auteurs")
    public List<Auteur> listeDesAuteurs(){

        List<Auteur> auteurs = auteurDao.findAll();

        if(auteurs.isEmpty()){
            throw new AuteurNotFoundException("Aucun auteur n'a été trouvé");
        }

        log.info("Récupération de la liste des auteurs");

        return auteurs;

    }

    /**
     * Récuperer un auteur par son id
     * @param id int
     * @return bean {@link Auteur}
     */
    @GetMapping( value = "/Auteurs/{id}")
    public Optional<Auteur> recupererUnAuteur(@PathVariable int id) {

        Optional<Auteur> auteur = auteurDao.findById(id);

        if(!auteur.isPresent())  throw new AuteurNotFoundException("L'auteur correspondant à l'id " + id + " n'existe pas");

        return auteur;
    }
}
