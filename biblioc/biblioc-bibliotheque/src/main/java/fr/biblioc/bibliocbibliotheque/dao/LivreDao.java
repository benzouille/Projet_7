package fr.biblioc.bibliocbibliotheque.dao;

import fr.biblioc.bibliocbibliotheque.model.Livre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Interface Dao pour JPA Hibernate
 */
@Repository
public interface LivreDao extends JpaRepository<Livre, Integer> {
    List<Livre> findByTitre(String titre);
    List<Livre> findByAuteurs(String auteurs);
    List<Livre> findByGenre(String genre);

}
