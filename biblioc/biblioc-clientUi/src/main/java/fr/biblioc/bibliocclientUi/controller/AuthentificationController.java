package fr.biblioc.bibliocclientUi.controller;

import fr.biblioc.bibliocclientUi.beans.authentification.CompteBean;
import fr.biblioc.bibliocclientUi.beans.utilisateur.AdresseBean;
import fr.biblioc.bibliocclientUi.beans.utilisateur.UtilisateurBean;
import fr.biblioc.bibliocclientUi.proxies.BibliocAuthentificationProxy;
import fr.biblioc.bibliocclientUi.proxies.BibliocUtilisateurProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;

@Controller
public class AuthentificationController {

    @Autowired
    private BibliocAuthentificationProxy authentificationProxy;

    @Autowired
    private BibliocUtilisateurProxy utilisateurProxy;

    @RequestMapping(value= "/authentification/connexion", method = RequestMethod.GET)
    public ModelAndView connexion(Model model){

        CompteBean compte = new CompteBean();
        model.addAttribute("compte", compte);

        return new ModelAndView("connexion");
    }

    @RequestMapping(value = "/authentification/connexion/erreur", method = RequestMethod.GET)
    public ModelAndView connectionerror(HttpServletRequest request) {

        ModelAndView modelAndView = new ModelAndView("connexion");

        Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request); // 1
        if (!CollectionUtils.isEmpty(flashMap)) {
            modelAndView.addObject("erreur", flashMap.get("erreur")); // 2
        }

        CompteBean compte = new CompteBean();
        modelAndView.addObject("compte", compte);

        return modelAndView;
    }

    @PostMapping("/authentification/connexion")
    public ModelAndView connexion(@ModelAttribute CompteBean compte, RedirectAttributes redirectAttributes){

            if (compte.getEmail().length() != 0 && compte.getPassword().length() != 0) {
                CompteBean compteComparator = authentificationProxy.getCompte(compte.getEmail());

                if (compteComparator != null && compteComparator.getPassword().equals(compte.getPassword())) {
                    redirectAttributes.addFlashAttribute("compte", compteComparator);

                    return new ModelAndView("redirect:/accueil");
                } else {
                    String erreur = "Erreur votre email ou votre mot de passe est incorrect !";
                    redirectAttributes.addFlashAttribute("erreur", erreur);

                    return new ModelAndView("redirect:/authentification/connexion/erreur");
                }
            } else {
                String erreur = "Erreur votre email ou votre mot de passe est incorrect !";
                redirectAttributes.addFlashAttribute("erreur", erreur);

                return new ModelAndView("redirect:/authentification/connexion/erreur");
            }
        }


    @RequestMapping(value= "/authentification/inscription", method = RequestMethod.GET)
    public String inscription(Model model){

        CompteBean compte = new CompteBean();

        model.addAttribute("compte", compte);

        return "inscription";
    }

    @PostMapping("/authentification/inscription")
    public String inscription(@Valid @ModelAttribute CompteBean compte, BindingResult bindingResult, Model model){

        if(bindingResult.hasErrors()){
            //TODO regarder l'erreur
            return "inscription";
        }
        else{
            CompteBean compteComparator = authentificationProxy.getCompte(compte.getEmail());
            if(compteComparator != null){
                System.out.println("double email : " + compteComparator.getEmail() + " et : " + compte.getEmail());
                String erreur = "cette adresse email est déjà utilisée !";

                model.addAttribute("compte", compte);
                model.addAttribute("erreur", erreur);

                return "inscription";
            } else{
                //attribution de l'utilisateur provisoire
                compte.setId_utilisateur(1);
                compte.setId_role(1);

                System.out.println(compte.toString());

                authentificationProxy.newCompte(compte);

                compte = new CompteBean();

                //TODO ajouter le retour http
//            if(paiement.getStatusCode() == HttpStatus.CREATED)
//                paiementAccepte = true;

                model.addAttribute("compte", compte);

                return "connexion";
            }

        }
    }

    @RequestMapping(value= "/authentification/profil/{id}", method = RequestMethod.GET)
    public String profil(@PathVariable int id, Model model){

        //TODO recuperer le compte du body
        CompteBean compte = authentificationProxy.getCompte(id);
        UtilisateurBean utilisateur = utilisateurProxy.getUtilisateur(compte.getId_utilisateur());
        AdresseBean adresse = utilisateurProxy.getAdresse(utilisateur.getId_adresse());

        model.addAttribute("compte", compte);
        model.addAttribute("utilisateur", utilisateur);
        model.addAttribute("adresse", adresse);

        System.out.println("compte : " + compte.toString() + " utilisateur : " + utilisateur.toString() + " adresse " + adresse.toString());

        return "profil";
    }

    @PostMapping("/authentification/profil")
    public String profil(@ModelAttribute CompteBean compte, @ModelAttribute UtilisateurBean utilisateur,
                         @Valid @ModelAttribute AdresseBean adresse, BindingResult bindingResult, Model model){

        if(bindingResult.hasErrors()){
            System.out.println("a des erreurs !");
        }
        else {
            System.out.println("a pas d'erreur");
            if(compte.getId_utilisateur() != 1){
                System.out.println("on update : " + utilisateur.toString() + " et " + adresse.toString());
                //utilisateurProxy.updateUtilisateur(utilisateur);
            } else {
                //utilisateurProxy.newUtilisateur(utilisateur);
                System.out.println("on crée : " + utilisateur.toString() + " et " + adresse.toString());
            }
        }
        //utilisateurProxy.updateAdresse(adresse);

        model.addAttribute("compte", compte);
        model.addAttribute("utilisateur", utilisateur);
        model.addAttribute("adresse", adresse);

        return "profil";
    }
}
