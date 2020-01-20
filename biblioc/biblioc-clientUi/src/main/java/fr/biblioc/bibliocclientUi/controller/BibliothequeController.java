package fr.biblioc.bibliocclientUi.controller;

import fr.biblioc.bibliocclientUi.beans.bibliotheque.AuteurBean;
import fr.biblioc.bibliocclientUi.beans.bibliotheque.GenreBean;
import fr.biblioc.bibliocclientUi.beans.bibliotheque.LivreBean;
import fr.biblioc.bibliocclientUi.beans.reservation.BibliothequeBean;
import fr.biblioc.bibliocclientUi.beans.reservation.ExemplaireBean;
import fr.biblioc.bibliocclientUi.proxies.BibliocBibliothequeProxy;
import fr.biblioc.bibliocclientUi.proxies.BibliocReservationProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class BibliothequeController {

    @Autowired
    private BibliocBibliothequeProxy bibliothequeProxy;

    @Autowired
    private BibliocReservationProxy reservationProxy;

    Logger log = LoggerFactory.getLogger(this.getClass());

    //------------------------- METHODE -------------------------

    @RequestMapping(value = "/recherche", method = RequestMethod.GET)
    public ModelAndView recherches(HttpServletRequest request) {

        ModelAndView modelAndView = new ModelAndView("recherche");

        Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request); // 1
        if (!CollectionUtils.isEmpty(flashMap)) {
            modelAndView.addObject("livres", flashMap.get("livres")); // 2
            System.out.println("on est dans bibliothequeController/recherche en get : " + flashMap.get("livres").toString());
        }

        LivreBean livre = new LivreBean();
        modelAndView.addObject("livre", livre);

        AuteurBean auteur = new AuteurBean();
        modelAndView.addObject("auteur", auteur);

        List<AuteurBean> auteurs = bibliothequeProxy.listAuteurs();
        modelAndView.addObject("auteurs", auteurs);

        List<GenreBean> genres = bibliothequeProxy.getGenres();
        modelAndView.addObject("genres", genres);

        List<BibliothequeBean> biblios = reservationProxy.listBibliotheques();
        modelAndView.addObject("biblios", biblios);

        return modelAndView;
    }

    /**
     * Recherche simple
     * @param titre
     * @param id_auteur
     * @param id_genre
     * @param redirectAttributes
     * @return
     */
    @PostMapping(value = "/recherche")
    public ModelAndView rechercheLivre(String titre, String id_auteur, String id_genre, RedirectAttributes redirectAttributes) {

        List<LivreBean> livres = null;

        if (titre != null) {
            livres = bibliothequeProxy.rechercheSimple("titre", titre);
        }
        if (id_auteur != null) {
            livres = bibliothequeProxy.rechercheSimple("auteur", id_auteur);
        }
        if (id_genre != null) {
            livres = bibliothequeProxy.rechercheSimple("genre", id_genre);
        }

        //le retour
        redirectAttributes.addFlashAttribute("livres", livres);

        return new ModelAndView("redirect:/recherche");
    }

    /**
     * recherche multiple
     * @param id_auteur
     * @param id_genre
     * @param id_biblio
     * @param redirectAttributes
     * @return
     */
    @PostMapping(value = "/recherches")
    public ModelAndView rechercheLivre(Integer id_auteur, Integer id_genre, Integer id_biblio, RedirectAttributes redirectAttributes) {

        List<Object> multicrit = new ArrayList<>();

        System.out.println(" les differentes infos : id_auteur : " + id_auteur + ", id_genre : " + id_genre + ", id_biblio : " + id_biblio);

        if (id_auteur.equals(-1) && id_genre.equals(-1) && id_biblio.equals(-1)) {
            System.out.println("pas de recherche");
            String erreur = "aucun critère sélectionné !";


        } else {
            if (id_auteur != -1) {
                AuteurBean auteur = new AuteurBean();
                auteur.setId(id_auteur);
                multicrit.add(auteur);
            }
            if (id_genre != -1) {
                GenreBean genre = new GenreBean();
                genre.setid_genre(id_genre);
                multicrit.add(genre);
            }
            if (id_biblio != -1) {
                BibliothequeBean biblio = new BibliothequeBean();
                biblio.setid_biblio(id_biblio);
                multicrit.add(biblio);
            }
        }

        return new ModelAndView("redirect:/recherche");
    }

    @RequestMapping(value = "/details-auteur/{id}", method = RequestMethod.GET)
    public String ficheAuteur(@PathVariable int id, Model model) {

        AuteurBean auteur = bibliothequeProxy.getAuteur(id);

        model.addAttribute("auteur", auteur);

        List<LivreBean> livres = bibliothequeProxy.listLivres();

        model.addAttribute("livres", livres);

        return "fiche-auteur";
    }

    @RequestMapping(value = "/details-livre/{id}", method = RequestMethod.GET)
    public String ficheLivre(@PathVariable int id, Model model) {

        LivreBean livre = bibliothequeProxy.getLivre(id);
        model.addAttribute("livre", livre);

        List<BibliothequeBean> bibliotheques = reservationProxy.listBibliotheques();
        model.addAttribute("bibliotheques", bibliotheques);

        List<ExemplaireBean> exemplaires = reservationProxy.getExemplairesByIdLivre(id);

        return "fiche-livre";
    }
}
