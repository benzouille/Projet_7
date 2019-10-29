package fr.biblioc.bibliocauthentification.web.controller;

import fr.biblioc.bibliocauthentification.web.exceptions.AdresseNotFoundException;
import fr.biblioc.bibliocauthentification.dao.AdresseDao;
import fr.biblioc.bibliocauthentification.model.Adresse;
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
 * Controller de la classe {@link Adresse}
 */
@RestController
public class AdresseController implements HealthIndicator {

    //------------------------- ATTRIBUTS -------------------------

    @Autowired
    AdresseDao adresseDao;

    Logger log = LoggerFactory.getLogger(this.getClass());

    //------------------------- METHODE -------------------------

    /**
     * Indique le status du microservice
     * @return etat du microservice
     */
    @Override
    public Health health() {
        List<Adresse> adresses = adresseDao.findAll();

        if(adresses.isEmpty()) {
            return Health.down().build();
        }
        return Health.up().build();
    }

    /**
     * Affiche la liste de toutes les adresses
     * @return liste d'adresses
     */
    @GetMapping(value = "/Adresses")
    public List<Adresse> listeDesAdresses(){

        List<Adresse> adresses = adresseDao.findAll();

        if(adresses.isEmpty()){
            throw new AdresseNotFoundException("Aucune adresse n'a été trouvée");
        }

        log.info("Récupération de la liste des adresses");

        return adresses;

    }

    /**
     * Récuperer une adresse par son id
     * @param id int
     * @return bean adresse
     */
    @GetMapping( value = "/adresses/{id}")
    public Optional<Adresse> recupererUneAdresse(@PathVariable int id) {

        Optional<Adresse> adresse = adresseDao.findById(id);

        if(!adresse.isPresent())  throw new AdresseNotFoundException("L'adresse correspondant à l'id " + id + " n'existe pas");

        return adresse;
    }
}
