package fr.biblioc.bibliocclientUi.controller;

import fr.biblioc.bibliocclientUi.beans.authentification.CompteBean;
import fr.biblioc.bibliocclientUi.beans.utilisateur.AdresseBean;
import fr.biblioc.bibliocclientUi.beans.utilisateur.UtilisateurBean;
import fr.biblioc.bibliocclientUi.proxies.BibliocAuthentificationProxy;
import fr.biblioc.bibliocclientUi.proxies.BibliocBibliothequeProxy;
import fr.biblioc.bibliocclientUi.proxies.BibliocUtilisateurProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AuthentificationController {

    @Autowired
    private BibliocAuthentificationProxy authentificationProxy;

    @Autowired
    private BibliocUtilisateurProxy utilisateurProxy;

    @Autowired
    private BibliocBibliothequeProxy bibliothequeProxy;

    @RequestMapping("/authentification/connexion")
    public String connexion(Model model){

        CompteBean compte = new CompteBean();

        model.addAttribute("compte", compte);

        return "connexion";
    }

    @PostMapping("/authentification/connexion")
    public ModelAndView connexion(@ModelAttribute CompteBean compte, RedirectAttributes redirectAttributes){

            if (compte.getEmail().length() != 0 && compte.getPassword().length() != 0) {

                CompteBean compteComparator = authentificationProxy.getCompte(compte.getEmail());

                if (compteComparator != null && compteComparator.getPassword().equals(compte.getPassword())) {

                    redirectAttributes.addFlashAttribute("compte", compteComparator);
                    //System.out.println(" id_compte : " + model.getAttribute("id_compte"));
                    //int id_compte = compteComparator.getId_compte();

                    return new ModelAndView("redirect:/");
                } else {

                    compte = new CompteBean();
                    redirectAttributes.addAttribute("compte", compte);

                    String erreur = "Erreur votre email ou votre mot de passe est incorrect !";
                    redirectAttributes.addAttribute("erreur", erreur);

                    return new ModelAndView("authentification/connexion");
                }
            } else {
                compte = new CompteBean();
                redirectAttributes.addAttribute("compte", compte);

                String erreur = "Erreur votre email ou votre mot de passe est incorrect !";
                redirectAttributes.addAttribute("erreur", erreur);

                return new ModelAndView("authentification/connexion");
            }
        }


    @RequestMapping("/authentification/inscription")
    public String inscription(Model model){

        CompteBean compte = new CompteBean();

        model.addAttribute("compte", compte);

        return "inscription";
    }

    @PostMapping("/authentification/inscription")
    public String inscription(@ModelAttribute CompteBean compte, Model model){


        if(1>0){
            //TODO SI inscription ok :
            // aller sur la page connexion

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
        else{
            // TODO SINON
            //  retourner sur la page inscription avec message d'erreur

            return "inscription";
        }
    }

    @RequestMapping("/authentification/profil/{id}")
    public String profil(@PathVariable int id, Model model){

        //TODO recuperer le compte du body
        CompteBean compte = authentificationProxy.getCompte(id);
        UtilisateurBean utilisateur = utilisateurProxy.getUtilisateur(compte.getId_utilisateur());
        AdresseBean adresse = utilisateurProxy.getAdresse(utilisateur.getId_adresse());
        
        model.addAttribute("compte", compte);
        model.addAttribute("utilisateur", utilisateur);
        model.addAttribute("adresse", adresse);

        return "profil";
    }

    @PostMapping("/authentification/profil")
    public String profil(@ModelAttribute CompteBean compte, @ModelAttribute UtilisateurBean utilisateur, Model model){

        System.out.println(compte.toString());

        utilisateurProxy.updateUtilisateur(utilisateur);

        model.addAttribute("compte", compte);
        model.addAttribute("utilisateur", utilisateur);

        return "profil";
    }
}
