package fr.biblioc.bibliocclientUi.controller;

import fr.biblioc.bibliocclientUi.beans.authentification.CompteBean;
import fr.biblioc.bibliocclientUi.beans.bibliotheque.AuteurBean;
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

    @RequestMapping("/")
    public String accueil(Model model){

        List<AuteurBean> auteurs = bibliothequeProxy.listAuteurs();

        model.addAttribute("auteurs", auteurs);

        return "Accueil";
    }

//    @RequestMapping("/{id_compte}")
//    public String accueil(@PathVariable("id_compte") int id_compte, Model model){
//
//        System.out.println("je passe par /{id_compte}");
//
//        List<AuteurBean> auteurs = bibliothequeProxy.listAuteurs();
//        CompteBean compte = authentificationProxy.getCompte(id_compte);
//
//        //CompteBean compte2 = (CompteBean) model.getAttribute("compte");
//        //System.out.println("objet CompteBean passé en parametre" + compte2.getEmail());
//
//        model.addAttribute("auteurs", auteurs);
//        model.addAttribute("compte", compte);
//
//        return "Accueil";
//    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView get(HttpServletRequest request) {

        ModelAndView modelAndView = new ModelAndView("accueil");

        Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request); // 1
        if (!CollectionUtils.isEmpty(flashMap)) {
            modelAndView.addObject("compte", flashMap.get("compte")); // 2
        }

        List<AuteurBean> auteurs = bibliothequeProxy.listAuteurs();
        modelAndView.addObject("auteurs", auteurs);

        return modelAndView;
    }

//    @PostMapping("/")
//    public String accueil(@PathVariable("compte") CompteBean compte, Model model){
//
//        List<AuteurBean> auteurs = bibliothequeProxy.listAuteurs();
//
//        model.addAttribute("auteurs", auteurs);
//        model.addAttribute("compte", compte);
//
//
//        return "Accueil";
//    }

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
