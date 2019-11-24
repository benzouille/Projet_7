package fr.biblioc.bibliocclientUi.controller;

import fr.biblioc.bibliocclientUi.beans.authentification.CompteBean;
import fr.biblioc.bibliocclientUi.proxies.BibliocAuthentificationProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthentificationController {

    @Autowired
    private BibliocAuthentificationProxy authentificationProxy;

    @RequestMapping("/authentification/connexion")
    public String test(Model model){

        CompteBean compte = new CompteBean();

        model.addAttribute("compte", compte);

        return "connexion";
    }

    @PostMapping("/authentification/connexion")
    public String connexion(@ModelAttribute CompteBean compte){

        System.out.println(compte.toString());

        return "connexion";
    }
}
