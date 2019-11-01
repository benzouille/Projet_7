package fr.biblioc.bibliocreservation.web.controller;

import fr.biblioc.bibliocreservation.dao.ExemplaireDao;
import fr.biblioc.bibliocreservation.model.Exemplaire;
import fr.biblioc.bibliocreservation.web.exceptions.ExemplaireNotFoundException;
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
 * Controller de la classe {@link Exemplaire}
 */
@RestController
public class ExemplaireController implements HealthIndicator {

    //------------------------- ATTRIBUTS -------------------------

    @Autowired
    ExemplaireDao exemplaireDao;

    Logger log = LoggerFactory.getLogger(this.getClass());

    //------------------------- METHODE -------------------------

    /**
     * Indique le status du microservice
     * @return etat du microservice
     */
    @Override
    public Health health() {
        List<Exemplaire> exemplaires = exemplaireDao.findAll();

        if(exemplaires.isEmpty()) {
            return Health.down().build();
        }
        return Health.up().build();
    }

    /**
     * Affiche la liste de tous les exemplaires
     * @return liste de {@link Exemplaire}
     */
    @GetMapping(value = "/Exemplaires")
    public List<Exemplaire> listeDesExemplaires(){

        List<Exemplaire> exemplaires = exemplaireDao.findAll();

        if(exemplaires.isEmpty()){
            throw new ExemplaireNotFoundException("Aucun exemplaire n'a été trouvée");
        }

        log.info("Récupération de la liste des exemplaires");

        return exemplaires;

    }

    /**
     * Récuperer un exemplaire par son id
     * @param id int
     * @return bean {@link Exemplaire}
     */
    @GetMapping( value = "/Exemplaires/{id}")
    public Optional<Exemplaire> recupererUneExemplaire(@PathVariable int id) {

        Optional<Exemplaire> exemplaire = exemplaireDao.findById(id);

        if(!exemplaire.isPresent())  throw new ExemplaireNotFoundException("L'exemplaire correspondant à l'id " + id + " n'existe pas");

        return exemplaire;
    }
}
