package fr.biblioc.bibliocclientUi.controller;

import fr.biblioc.bibliocclientUi.beans.reservation.ReservationBean;
import fr.biblioc.bibliocclientUi.beans.utilisateur.UtilisateurBean;
import fr.biblioc.bibliocclientUi.proxies.BibliocAuthentificationProxy;
import fr.biblioc.bibliocclientUi.proxies.BibliocBibliothequeProxy;
import fr.biblioc.bibliocclientUi.proxies.BibliocReservationProxy;
import fr.biblioc.bibliocclientUi.proxies.BibliocUtilisateurProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class GestionController {

    @Autowired
    private BibliocUtilisateurProxy utilisateurProxy;

    @Autowired
    private BibliocBibliothequeProxy bibliothequeProxy;

    @Autowired
    private BibliocReservationProxy reservationProxy;

    @RequestMapping(value = "/gestion", method = RequestMethod.GET)
    public ModelAndView gestion(HttpServletRequest request){

        ModelAndView modelAndView = new ModelAndView("gestion_reservation");

        Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request); // 1
        if (!CollectionUtils.isEmpty(flashMap)) {
            modelAndView.addObject("livres", flashMap.get("livres")); // 2
            System.out.println("on est dans bibliothequeController/recherche en get : " + flashMap.get("livres").toString());
        }


        List<UtilisateurBean> utilisateurs = utilisateurProxy.listUtilisateurs();

        List<ReservationBean> reservations = reservationProxy.listReservations();

        //ajout des objets livres et utilisateurs dans les reservations
        for (ReservationBean reservation : reservations){
            reservation.setUtilisateur(utilisateurProxy.getUtilisateur(reservation.getId_utilisateur()));
            reservation.getExemplaire().setLivre(bibliothequeProxy.getLivre(reservation.getExemplaire().getId_livre()));
        }

        modelAndView.addObject("utilisateurs", utilisateurs);
        modelAndView.addObject("reservations", reservations);

        return modelAndView;
    }

    @PostMapping(value = "/retour")
    public ModelAndView retour(String id_exemplaire, RedirectAttributes redirectAttributes){


        //le retour
        //redirectAttributes.addFlashAttribute("objet", objet);

        return new ModelAndView("redirect:/gestion");
    }
}
