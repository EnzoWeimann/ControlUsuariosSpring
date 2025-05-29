package com.enzow.ControlUsuarios.servicio;

import com.enzow.ControlUsuarios.dao.InterfazPersonaDao;
import com.enzow.ControlUsuarios.domain.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/*Para que spring reconozca que esta clase implementa los servicios
se debe agregar la notación @Service, lo que permite inyectar a esta clase
dentro del controlador y poder acceder a los métodos creados*/
@Service
public class PersonaService implements InterfazPersonaService{

    @Autowired
    private InterfazPersonaDao personaDao;

    @Override
    @Transactional(readOnly = true)
    public List<Persona> listarPersonas() {
        return (List<Persona>) personaDao.findAll();
        //findAll() devuelve objetos, por eso hay que hacer un casteo para que quede como lista
    }

    @Override
    @Transactional
    public void guardar(Persona persona) {
        personaDao.save(persona);
    }

    @Override
    @Transactional
    public void eliminar(Persona persona) {
        personaDao.delete(persona);
    }

    @Override
    @Transactional(readOnly = true)
    public Persona encontrarPersona(Persona persona) {
        return personaDao.findById(persona.getIdpersona()).orElse(null);
        //Si no se encuentra el objeto, devuelve null
    }
}

