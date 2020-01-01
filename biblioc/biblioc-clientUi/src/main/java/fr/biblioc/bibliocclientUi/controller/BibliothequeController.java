package fr.biblioc.bibliocclientUi.controller;

import fr.biblioc.bibliocclientUi.beans.bibliotheque.AuteurBean;
import fr.biblioc.bibliocclientUi.beans.bibliotheque.GenreBean;
import fr.biblioc.bibliocclientUi.beans.bibliotheque.LivreBean;
import fr.biblioc.bibliocclientUi.beans.reservation.BibliothequeBean;
import fr.biblioc.bibliocclientUi.proxies.BibliocBibliothequeProxy;
import fr.biblioc.bibliocclientUi.proxies.BibliocReservationProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class BibliothequeController {

    @Autowired
    private BibliocBibliothequeProxy bibliothequeProxy;

    @Autowired
    private BibliocReservationProxy reservationProxy;

    @RequestMapping(value = "/recherche", method = RequestMethod.GET)
    public String rechercheLivre(Model model){

        List<AuteurBean> auteurs = bibliothequeProxy.listAuteurs();
        model.addAttribute("auteurs", auteurs);

        List<GenreBean> genres = bibliothequeProxy.getGenres();
        model.addAttribute("genres", genres);

        List<BibliothequeBean> biblios = reservationProxy.listBibliotheques();
        model.addAttribute("biblios", biblios);

        return "recherche";
    }

    @RequestMapping(value= "/details-auteur/{id}", method = RequestMethod.GET)
    public String ficheAuteur(@PathVariable int id, Model model){

        AuteurBean auteur = bibliothequeProxy.getAuteur(id);

        model.addAttribute("auteur", auteur);

        List<LivreBean> livres = bibliothequeProxy.listLivres();

        model.addAttribute("livres", livres);

        return "fiche-auteur";
    }

    @RequestMapping(value= "/details-livre/{id}", method = RequestMethod.GET)
    public String ficheLivre(@PathVariable int id, Model model){

//        AuteurBean auteur = bibliothequeProxy.getAuteur(id);
//
//        model.addAttribute("auteur", auteur);

        LivreBean livre = bibliothequeProxy.getLivre(id);

        model.addAttribute("livre", livre);

        return "fiche-livre";
    }
}
