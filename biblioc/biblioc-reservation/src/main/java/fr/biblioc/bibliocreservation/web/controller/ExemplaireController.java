package fr.biblioc.bibliocreservation.web.controller;

import fr.biblioc.bibliocreservation.dao.ExemplaireDao;
import fr.biblioc.bibliocreservation.model.Exemplaire;
import fr.biblioc.bibliocreservation.web.exceptions.ErrorAddException;
import fr.biblioc.bibliocreservation.web.exceptions.ObjectNotFoundException;
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
            throw new ObjectNotFoundException("Aucun exemplaire n'a été trouvée");
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

        if(!exemplaire.isPresent())  throw new ObjectNotFoundException("L'exemplaire correspondant à l'id " + id + " n'existe pas");

        return exemplaire;
    }

    /**
     * Ajouter un exemplaire
     * @param exemplaire bean {@link Exemplaire}
     * @return ResponseEntity<Exemplaire> renvoi un http status.
     */
    @PostMapping(value = "/exemplaires")
    public ResponseEntity<Exemplaire> addExemplaire(Exemplaire exemplaire){

        Exemplaire newExemplaire = exemplaireDao.save(exemplaire);

        if(newExemplaire == null) throw new ErrorAddException("Impossible d'ajouter ce exemplaire");

        return new ResponseEntity<Exemplaire>(exemplaire, HttpStatus.CREATED);
    }

    /**
     * Permet de mettre à jour un exemplaire existant.
     **/
    @PutMapping(value = "/exemplaires")
    public void updateExemplaire(@RequestBody Exemplaire exemplaire) {

        exemplaireDao.save(exemplaire);
    }
}
