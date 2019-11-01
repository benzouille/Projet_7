package fr.biblioc.bibliocbibliotheque.web.controller;

import fr.biblioc.bibliocbibliotheque.dao.GenreDao;
import fr.biblioc.bibliocbibliotheque.model.Genre;
import fr.biblioc.bibliocbibliotheque.web.exceptions.GenreNotFoundException;
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
 * Controller de la classe {@link Genre}
 */
@RestController
public class GenreController implements HealthIndicator {
    //------------------------- ATTRIBUTS -------------------------

    @Autowired
    GenreDao genreDao;

    Logger log = LoggerFactory.getLogger(this.getClass());

    //------------------------- METHODE -------------------------

    /**
     * Indique le status du microservice
     * @return etat du microservice
     */
    @Override
    public Health health() {
        List<Genre> genres = genreDao.findAll();

        if(genres.isEmpty()) {
            return Health.down().build();
        }
        return Health.up().build();
    }

    /**
     * Affiche la liste de tous les genres
     * @return liste de {@link Genre}
     */
    @GetMapping(value = "/Genres")
    public List<Genre> listeDesGenres(){

        List<Genre> genres = genreDao.findAll();

        if(genres.isEmpty()){
            throw new GenreNotFoundException("Aucun genre n'a été trouvé");
        }

        log.info("Récupération de la liste des genres");

        return genres;

    }

    /**
     * Récuperer un genre par son id
     * @param id int
     * @return bean {@link Genre}
     */
    @GetMapping( value = "/Genres/{id}")
    public Optional<Genre> recupererUnGenre(@PathVariable int id) {

        Optional<Genre> genre = genreDao.findById(id);

        if(!genre.isPresent())  throw new GenreNotFoundException("Le genre correspondant à l'id " + id + " n'existe pas");

        return genre;
    }
}
