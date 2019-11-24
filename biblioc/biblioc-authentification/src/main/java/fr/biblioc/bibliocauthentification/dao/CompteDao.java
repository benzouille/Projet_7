package fr.biblioc.bibliocauthentification.dao;

import fr.biblioc.bibliocauthentification.model.Compte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface Dao pour JPA Hibernate
 */
@Repository
public interface CompteDao extends JpaRepository<Compte, Integer> {
    Compte findByEmail(String email);
}
