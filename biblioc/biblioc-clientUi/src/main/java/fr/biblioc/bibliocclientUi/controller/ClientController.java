package fr.biblioc.bibliocclientUi.controller;

import fr.biblioc.bibliocclientUi.beans.authentification.CompteBean;
import fr.biblioc.bibliocclientUi.beans.bibliotheque.AuteurBean;
import fr.biblioc.bibliocclientUi.beans.bibliotheque.GenreBean;
import fr.biblioc.bibliocclientUi.beans.bibliotheque.LivreBean;
import fr.biblioc.bibliocclientUi.proxies.BibliocAuthentificationProxy;
import fr.biblioc.bibliocclientUi.proxies.BibliocBibliothequeProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class ClientController {

    @Autowired
    private BibliocBibliothequeProxy bibliothequeProxy;

    @Autowired
    private BibliocAuthentificationProxy authentificationProxy;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String accueil(Model model){

        List<AuteurBean> auteurs = bibliothequeProxy.listAuteurs();

        model.addAttribute("auteurs", auteurs);

        return "Accueil";
    }

    @RequestMapping(value = "/accueil", method = RequestMethod.GET)
    public ModelAndView accueilConnecte(HttpServletRequest request) {

        ModelAndView modelAndView = new ModelAndView("accueil");

        Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request); // 1
        if (!CollectionUtils.isEmpty(flashMap)) {
            modelAndView.addObject("compte", flashMap.get("compte")); // 2
        }

        List<AuteurBean> auteurs = bibliothequeProxy.listAuteurs();
        modelAndView.addObject("auteurs", auteurs);

        return modelAndView;
    }
}
