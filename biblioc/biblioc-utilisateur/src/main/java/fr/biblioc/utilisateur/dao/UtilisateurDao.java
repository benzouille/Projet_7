package fr.biblioc.utilisateur.dao;

import fr.biblioc.utilisateur.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface Dao pour JPA Hibernate
 */
@Repository
public interface UtilisateurDao extends JpaRepository<Utilisateur, Integer> {
}
