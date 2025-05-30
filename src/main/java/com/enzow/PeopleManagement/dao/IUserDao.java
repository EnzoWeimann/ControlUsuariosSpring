package com.enzow.PeopleManagement.dao;

import com.enzow.PeopleManagement.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface that extends {@link JpaRepository} to provide advanced CRUD and JPA operations
 * Spring automatically implements this interface when the app is executed
 *
 * Usuario is the class type, and Long is the type of the primary key of the table in the DB that is connected to this class
 */
public interface IUserDao extends JpaRepository<Usuario, Long> {
    Usuario findByUsername (String username);
}
