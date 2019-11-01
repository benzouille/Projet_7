package fr.biblioc.bibliocbibliotheque.web.controller;

import fr.biblioc.bibliocbibliotheque.dao.EditeurDao;
import fr.biblioc.bibliocbibliotheque.model.Editeur;
import fr.biblioc.bibliocbibliotheque.web.exceptions.EditeurNotFoundException;
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
 * Controller de la classe {@link Editeur}
 */
@RestController
public class EditeurController implements HealthIndicator {

    //------------------------- ATTRIBUTS -------------------------

    @Autowired
    EditeurDao editeurDao;

    Logger log = LoggerFactory.getLogger(this.getClass());

    //------------------------- METHODE -------------------------

    /**
     * Indique le status du microservice
     * @return etat du microservice
     */
    @Override
    public Health health() {
        List<Editeur> editeurs = editeurDao.findAll();

        if(editeurs.isEmpty()) {
            return Health.down().build();
        }
        return Health.up().build();
    }

    /**
     * Affiche la liste de tous les editeurs
     * @return liste de {@link Editeur}
     */
    @GetMapping(value = "/Editeurs")
    public List<Editeur> listeDesEditeurs(){

        List<Editeur> editeurs = editeurDao.findAll();

        if(editeurs.isEmpty()){
            throw new EditeurNotFoundException("Aucun editeur n'a été trouvé");
        }

        log.info("Récupération de la liste des editeurs");

        return editeurs;

    }

    /**
     * Récuperer un editeur par son id
     * @param id int
     * @return bean {@link Editeur}
     */
    @GetMapping( value = "/Editeurs/{id}")
    public Optional<Editeur> recupererUnEditeur(@PathVariable int id) {

        Optional<Editeur> editeur = editeurDao.findById(id);

        if(!editeur.isPresent())  throw new EditeurNotFoundException("L'editeur correspondant à l'id " + id + " n'existe pas");

        return editeur;
    }
}
