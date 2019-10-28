package fr.biblioc.bibliocbibliotheque.dao;

import fr.biblioc.bibliocbibliotheque.model.Auteur;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AuteurDao extends CrudRepository<Auteur, Integer> {
    Auteur findById(int id);

    List<Auteur> findAll();
}
