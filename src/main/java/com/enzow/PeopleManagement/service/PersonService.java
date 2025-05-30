package com.enzow.PeopleManagement.service;

import com.enzow.PeopleManagement.dao.IPersonDao;
import com.enzow.PeopleManagement.domain.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/*Para que spring reconozca que esta clase implementa los servicios
se debe agregar la notación @Service, lo que permite inyectar a esta clase
dentro del controlador y poder acceder a los métodos creados*/

/**
 * Implementation of the {@link IPersonService} to manage CRUD operations related to the entity {@link Persona}
 *
 * This class interacts with the data access layer through {@link IPersonDao}
 * and handles transactions by using Transactional Spring annotations
 */
@Service
@Transactional
public class PersonService implements IPersonService {

    /**
     * Injection of a personaDao instance to access the DB
     */
    @Autowired
    private IPersonDao personaDao;

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<Persona> listarPersonas() {
        return personaDao.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void guardar(Persona persona) {
        personaDao.save(persona);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void eliminar(Persona persona) {
        personaDao.delete(persona);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public Persona encontrarPersona(Persona persona) {
        return personaDao.findById(persona.getIdpersona()).orElse(null);
    }
}

