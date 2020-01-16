package fr.biblioc.bibliocbibliotheque.web.controller;

import fr.biblioc.bibliocbibliotheque.dao.AuteurDao;
import fr.biblioc.bibliocbibliotheque.dao.LivreDao;
import fr.biblioc.bibliocbibliotheque.dto.AuteurDto;
import fr.biblioc.bibliocbibliotheque.dto.LivreDto;
import fr.biblioc.bibliocbibliotheque.mapper.AuteurMapper;
import fr.biblioc.bibliocbibliotheque.mapper.LivreMapper;
import fr.biblioc.bibliocbibliotheque.model.Auteur;
import fr.biblioc.bibliocbibliotheque.model.Livre;
import fr.biblioc.bibliocbibliotheque.web.exceptions.ObjectNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Controller des recherches
 */
@RestController
public class RechercheController {

    //------------------------- ATTRIBUTS -------------------------

    @Autowired
    LivreDao livreDao;

    @Autowired
    LivreMapper livreMapper;

    @Autowired
    AuteurController auteurController;

    Logger log = LoggerFactory.getLogger(this.getClass());

    //------------------------- METHODE -------------------------

    @GetMapping(value = "/Recherche")
    List<Livre> rechercheSimple(String type, String value) {
        log.info("rechercheSimple : [ type : " + type + ", value : " + value + " ]");

        List<Livre> livres = null;

        if (type.equals("titre")) {
            livres = livreDao.findByTitre(value);
        } else if (type.equals("auteur")) {
            System.out.println("passe par auteur");
            livres = livreDao.findByAuteurs(value);
        } else if(type.equals("genre")){
            System.out.println("passe par genre");
            livres = livreDao.findByGenre(value);
        }

//        List<LivreDto> livresDto = new ArrayList<>();
//        for (Livre livre : livres) {

//            livresDto.add(livreMapper.livreToLivreDto(livre));
//        }

//        if (livresDto.isEmpty()) {
//            throw new ObjectNotFoundException("Aucun livre n'a été trouvé");
//        }

        return livres;
    }

    @GetMapping(value = "/Recherches")
    List<Livre> rechercheMulti(String type, String value) {
        System.out.println("je passe par RechercheController/Recherches");
        return null;
    }
}
