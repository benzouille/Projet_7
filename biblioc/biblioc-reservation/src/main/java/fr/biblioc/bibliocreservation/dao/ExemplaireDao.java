package fr.biblioc.bibliocreservation.dao;

import fr.biblioc.bibliocreservation.model.Exemplaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExemplaireDao extends JpaRepository<Exemplaire, Integer> {

    @Query(value = "SELECT * FROM exemplaire WHERE id_livre = :id_livre", nativeQuery = true)
    List<Exemplaire> findAllById_livre(@Param("id_livre") int id_livre);
}
