package fr.biblioc.bibliocclientUi.controller;

import fr.biblioc.bibliocclientUi.beans.bibliotheque.AuteurBean;
import fr.biblioc.bibliocclientUi.beans.bibliotheque.LivreBean;
import fr.biblioc.bibliocclientUi.proxies.BibliocBibliothequeProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ClientController {

    @Autowired
    private BibliocBibliothequeProxy bibliothequeProxy;

    @RequestMapping("/")
    public String accueil(Model model){

        List<AuteurBean> auteurs = bibliothequeProxy.listAuteurs();

        model.addAttribute("auteurs", auteurs);

        return "Accueil";
    }

    @RequestMapping("/details-auteur/{id}")
    public String ficheAuteur(@PathVariable int id, Model model){

        AuteurBean auteur = bibliothequeProxy.getAuteur(id);

        model.addAttribute("auteur", auteur);

        List<LivreBean> livres = bibliothequeProxy.listLivres();

        model.addAttribute("livres", livres);

        return "fiche-auteur";
    }

    @RequestMapping("/details-livre/{id}")
    public String ficheLivre(@PathVariable int id, Model model){

//        AuteurBean auteur = bibliothequeProxy.getAuteur(id);
//
//        model.addAttribute("auteur", auteur);

        LivreBean livre = bibliothequeProxy.getLivre(id);

        model.addAttribute("livre", livre);

        return "fiche-livre";
    }
}
