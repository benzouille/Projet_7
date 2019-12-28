package fr.biblioc.bibliocclientUi.controller;

import fr.biblioc.bibliocclientUi.beans.authentification.CompteBean;
import fr.biblioc.bibliocclientUi.beans.bibliotheque.AuteurBean;
import fr.biblioc.bibliocclientUi.proxies.BibliocAuthentificationProxy;
import fr.biblioc.bibliocclientUi.proxies.BibliocBibliothequeProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class TestController {

    @Autowired
    private BibliocBibliothequeProxy bibliothequeProxy;

    @Autowired
    private BibliocAuthentificationProxy authentificationProxy;

    @RequestMapping("/test")
    public String test(Model model){

        List<AuteurBean> auteurs = bibliothequeProxy.listAuteurs();

        model.addAttribute("auteurs", auteurs);

        List<CompteBean> comptes = authentificationProxy.listComptes();

        model.addAttribute("comptes", comptes);

        return "test";
    }

    @RequestMapping("/navbar")
    public String navbar(Model model){
        return "navbar";
    }
}
