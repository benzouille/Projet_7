package fr.biblioc.bibliocclientUi.controller;

import fr.biblioc.bibliocclientUi.beans.authentification.CompteBean;
import fr.biblioc.bibliocclientUi.beans.utilisateur.AdresseBean;
import fr.biblioc.bibliocclientUi.beans.utilisateur.UtilisateurBean;
import fr.biblioc.bibliocclientUi.beans.utilities.PasswordDigest;
import fr.biblioc.bibliocclientUi.proxies.BibliocAuthentificationProxy;
import fr.biblioc.bibliocclientUi.proxies.BibliocUtilisateurProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;

/**
 * Controller utilisant le proxy vers le microservice authentification
 */
@Controller
public class AuthentificationController {

    @Autowired
    private BibliocAuthentificationProxy authentificationProxy;

    @Autowired
    private BibliocUtilisateurProxy utilisateurProxy;



    @RequestMapping(value= "/authentification/connexion", method = RequestMethod.GET)
    public ModelAndView connexion(Model model){

        return new ModelAndView("connexion");
    }

    @RequestMapping(value = "/authentification/connexion/erreur", method = RequestMethod.GET)
    public ModelAndView connexionError(HttpServletRequest request) {

        ModelAndView modelAndView = new ModelAndView("connexion");

        Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request); // 1
        if (!CollectionUtils.isEmpty(flashMap)) {
            modelAndView.addObject("erreur", flashMap.get("erreur")); // 2
        }

        return modelAndView;
    }

    @PostMapping("/authentification/connexion")
    public ModelAndView connexion(String email, String password, HttpServletRequest request, RedirectAttributes redirectAttributes){

            if (email.length() != 0 && password.length() != 0) {
                CompteBean compte = authentificationProxy.getCompte(email);

                password = PasswordDigest.hashAndSalt(password);

                if (compte != null && compte.getPassword().equals(password)) {

                    request.getSession().setAttribute("compte",compte);
                    return new ModelAndView("redirect:/");
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


    @PostMapping("/authentification/deconnexion")
    public ModelAndView deconnexion(@ModelAttribute CompteBean compte, HttpServletRequest request, RedirectAttributes redirectAttributes){

        request.getSession().removeAttribute("compte");

        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value= "/authentification/inscription", method = RequestMethod.GET)
    public String inscription(Model model){

        return "inscription";
    }

    @PostMapping("/authentification/inscription")
    public String inscription(String email, String password, BindingResult bindingResult, Model model){

        if(bindingResult.hasErrors()){
            //TODO regarder l'erreur
            return "inscription";
        }
        else{
            CompteBean compteComparator = authentificationProxy.getCompte(email);
            if(compteComparator != null){
                System.out.println("double email : " + compteComparator.getEmail() + " et : " + email);
                String erreur = "cette adresse email est déjà utilisée !";

                model.addAttribute("erreur", erreur);

                return "inscription";
            } else{

                password = PasswordDigest.hashAndSalt(password);

                //attribution de l'utilisateur provisoire
                CompteBean compte = new CompteBean(email, password, 1, 1);

                System.out.println(compte.toString());

                authentificationProxy.newCompte(compte);

                return "connexion";
            }

        }
    }

    @RequestMapping(value= "/authentification/profil", method = RequestMethod.GET)
    public String profil(Model model, HttpServletRequest request){

        CompteBean compte = (CompteBean)request.getSession().getAttribute("compte");
        model.addAttribute("compte", compte);

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
