package com.enzow.PeopleManagement.service;

import com.enzow.PeopleManagement.domain.Persona;

import java.util.List;

/**
 * Service interface for people management with CRUD operations
 * Defines the operations related to the entity {@link Persona}
 */
public interface IPersonService {

    /**
     * Returns the complete list of registered persons
     * @return a List of Persona objects
     */
    public List<Persona> listarPersonas();

    /**
     * Save a new person object or update an existing one
     * @param persona is the Persona object to be saved or updated
     */
    public void guardar(Persona persona);

    /**
     * Deletes a person from the system
     * @param persona is the Persona object to delete
     */
    public void eliminar(Persona persona);

    /**
     * Search and return a Persona object based on their ID
     * @param persona contains the ID of the person to search for
     * @return the found Persona object, or null if there's no existing object with that ID
     */
    public Persona encontrarPersona(Persona persona);
}
