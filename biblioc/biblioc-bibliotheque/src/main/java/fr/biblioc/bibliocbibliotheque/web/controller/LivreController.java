package fr.biblioc.bibliocbibliotheque.web.controller;

import fr.biblioc.bibliocbibliotheque.dao.LivreDao;
import fr.biblioc.bibliocbibliotheque.model.Livre;
import fr.biblioc.bibliocbibliotheque.web.exceptions.LivreNotFoundException;
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
 * Controller de la classe {@link Livre}
 */
@RestController
public class LivreController implements HealthIndicator {
    //------------------------- ATTRIBUTS -------------------------

    @Autowired
    LivreDao livreDao;

    Logger log = LoggerFactory.getLogger(this.getClass());

    //------------------------- METHODE -------------------------

    /**
     * Indique le status du microservice
     * @return etat du microservice
     */
    @Override
    public Health health() {
        List<Livre> livres = livreDao.findAll();

        if(livres.isEmpty()) {
            return Health.down().build();
        }
        return Health.up().build();
    }

    /**
     * Affiche la liste de tous les livres
     * @return liste de {@link Livre}
     */
    @GetMapping(value = "/Livres")
    public List<Livre> listeDesLivres(){

        List<Livre> livres = livreDao.findAll();

        if(livres.isEmpty()){
            throw new LivreNotFoundException("Aucun livre n'a été trouvé");
        }

        log.info("Récupération de la liste des livres");

        return livres;

    }

    /**
     * Récuperer un livre par son id
     * @param id int
     * @return bean {@link Livre}
     */
    @GetMapping( value = "/Livres/{id}")
    public Optional<Livre> recupererUnLivre(@PathVariable int id) {

        Optional<Livre> livre = livreDao.findById(id);

        if(!livre.isPresent())  throw new LivreNotFoundException("Le livre correspondant à l'id " + id + " n'existe pas");

        return livre;
    }
}
