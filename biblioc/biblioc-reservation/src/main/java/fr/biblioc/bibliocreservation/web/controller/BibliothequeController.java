package fr.biblioc.bibliocreservation.web.controller;

import fr.biblioc.bibliocreservation.dao.BibliothequeDao;
import fr.biblioc.bibliocreservation.model.Bibliotheque;
import fr.biblioc.bibliocreservation.web.exceptions.BibliothequeNotFoundException;
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
 * Controller de la classe {@link Bibliotheque}
 */
@RestController
public class BibliothequeController implements HealthIndicator {

    //------------------------- ATTRIBUTS -------------------------

    @Autowired
    BibliothequeDao bibliothequeDao;

    Logger log = LoggerFactory.getLogger(this.getClass());

    //------------------------- METHODE -------------------------

    /**
     * Indique le status du microservice
     * @return etat du microservice
     */
    @Override
    public Health health() {
        List<Bibliotheque> bibliotheques = bibliothequeDao.findAll();

        if(bibliotheques.isEmpty()) {
            return Health.down().build();
        }
        return Health.up().build();
    }

    /**
     * Affiche la liste de toutes les bibliotheques
     * @return liste de {@link Bibliotheque}
     */
    @GetMapping(value = "/Bibliotheques")
    public List<Bibliotheque> listeDesBibliotheques(){

        List<Bibliotheque> bibliotheques = bibliothequeDao.findAll();

        if(bibliotheques.isEmpty()){
            throw new BibliothequeNotFoundException("Aucune bibliotheque n'a été trouvée");
        }

        log.info("Récupération de la liste des bibliotheques");

        return bibliotheques;

    }

    /**
     * Récuperer une bibliotheque par son id
     * @param id int
     * @return bean {@link Bibliotheque}
     */
    @GetMapping( value = "/Bibliotheques/{id}")
    public Optional<Bibliotheque> recupererUneBibliotheque(@PathVariable int id) {

        Optional<Bibliotheque> bibliotheque = bibliothequeDao.findById(id);

        if(!bibliotheque.isPresent())  throw new BibliothequeNotFoundException("La bibliotheque correspondant à l'id " + id + " n'existe pas");

        return bibliotheque;
    }
}
