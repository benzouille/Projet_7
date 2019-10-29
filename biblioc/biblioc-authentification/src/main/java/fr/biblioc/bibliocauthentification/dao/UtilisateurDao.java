package fr.biblioc.bibliocauthentification.dao;

import fr.biblioc.bibliocauthentification.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilisateurDao extends JpaRepository<Utilisateur, Integer> {
}
