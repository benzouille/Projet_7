package fr.biblioc.bibliocauthentification.dao;

import fr.biblioc.bibliocauthentification.model.Adresse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdresseDao extends JpaRepository<Adresse, Integer> {
}
