package com.enzow.ControlUsuarios.servicio;

import com.enzow.ControlUsuarios.domain.Persona;

import java.util.List;

//Interfaz para implementar funciones CRUD desde la web
public interface InterfazPersonaService {

    public List<Persona> listarPersonas();

    public void guardar(Persona persona);

    public void eliminar(Persona persona);

    public Persona encontrarPersona(Persona persona);
}
