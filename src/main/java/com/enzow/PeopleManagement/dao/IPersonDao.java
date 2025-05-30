package com.enzow.PeopleManagement.dao;

import com.enzow.PeopleManagement.domain.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * Interface to access the data of the entity Persona {@link Persona}
 * Spring automatically implements this interface when the app is executed
 *
 * Persona is the class type, and Long is the type of the primary key of the table in the DB that is connected to this class
 */
public interface IPersonDao extends JpaRepository<Persona, Long> {
}
