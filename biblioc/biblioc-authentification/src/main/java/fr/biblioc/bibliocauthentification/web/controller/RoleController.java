package fr.biblioc.bibliocauthentification.web.controller;

import fr.biblioc.bibliocauthentification.dao.RoleDao;
import fr.biblioc.bibliocauthentification.model.Role;
import fr.biblioc.bibliocauthentification.web.exceptions.RoleNotFoundException;
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
 * Controller de la classe {@link Role}
 */
@RestController
public class RoleController implements HealthIndicator {

    //------------------------- ATTRIBUTS -------------------------

    @Autowired
    RoleDao roleDao;

    Logger log = LoggerFactory.getLogger(this.getClass());

    //------------------------- METHODE -------------------------

    /**
     * Indique le status du microservice
     * @return etat du microservice
     */
    @Override
    public Health health() {
        List<Role> roles = roleDao.findAll();

        if(roles.isEmpty()) {
            return Health.down().build();
        }
        return Health.up().build();
    }

    /**
     * Affiche la liste de tous les roles
     * @return liste de {@link Role}
     */
    @GetMapping(value = "/Roles")
    public List<Role> listeDesRoles(){

        List<Role> roles = roleDao.findAll();

        if(roles.isEmpty()){
            throw new RoleNotFoundException("Aucun role n'a été trouvée");
        }

        log.info("Récupération de la liste des roles");

        return roles;

    }

    /**
     * Récuperer un role par son id
     * @param id int
     * @return bean {@link Role}
     */
    @GetMapping( value = "/Roles/{id}")
    public Optional<Role> recupererUnRole(@PathVariable int id) {

        Optional<Role> role = roleDao.findById(id);

        if(!role.isPresent())  throw new RoleNotFoundException("Le role correspondant à l'id " + id + " n'existe pas");

        return role;
    }
}
