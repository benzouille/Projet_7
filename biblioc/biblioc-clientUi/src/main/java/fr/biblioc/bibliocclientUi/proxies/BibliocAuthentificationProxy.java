package fr.biblioc.bibliocclientUi.proxies;

import fr.biblioc.bibliocclientUi.beans.utilisateur.UtilisateurBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * Proxy du microservice authentification.
 */
@FeignClient(name = "biblioc-authentification", url = "localhost:9001")
public interface BibliocAuthentificationProxy {

    @GetMapping(value = "/Utilisateurs")
    List<UtilisateurBean> listUtilisateurs();

    @GetMapping(value = "/Utilisateurs/{id}")
    UtilisateurBean getUtilisateur(@PathVariable("id") int id);

    @PostMapping(value = "/Utilisateurs")
    UtilisateurBean setUtilisateur(@PathVariable("utilisateur") UtilisateurBean utilisateur);
}
