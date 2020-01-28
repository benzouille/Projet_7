package fr.biblioc.bibliocclientUi.controller;

import fr.biblioc.bibliocclientUi.beans.reservation.ReservationBean;
import fr.biblioc.bibliocclientUi.beans.utilisateur.UtilisateurBean;
import fr.biblioc.bibliocclientUi.proxies.BibliocBibliothequeProxy;
import fr.biblioc.bibliocclientUi.proxies.BibliocReservationProxy;
import fr.biblioc.bibliocclientUi.proxies.BibliocUtilisateurProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class ReservationController {

    @Autowired
    private BibliocUtilisateurProxy utilisateurProxy;

    @Autowired
    private BibliocBibliothequeProxy bibliothequeProxy;

    @Autowired
    private BibliocReservationProxy reservationProxy;

    @RequestMapping(value= "/mes_emprunts", method = RequestMethod.GET)
    public String empruntsUtilisateur(Model model){

        UtilisateurBean utilisateur = utilisateurProxy.getUtilisateur(2);
        List<ReservationBean> reservations = reservationProxy.getReservationById_compte(2);

        //ajout des objets livres dans les reservations
        for (ReservationBean reservation : reservations){
            reservation.getExemplaire().setLivre(bibliothequeProxy.getLivre(reservation.getExemplaire().getId_livre()));
        }

        model.addAttribute("utilisateur", utilisateur);
        model.addAttribute("reservations", reservations);

        return "mes_emprunts";
    }
}
