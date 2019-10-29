package fr.biblioc.bibliocauthentification.dao;

import fr.biblioc.bibliocauthentification.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao extends JpaRepository<Role, Integer> {
}
