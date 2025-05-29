package com.enzow.ControlUsuarios.dao;

import com.enzow.ControlUsuarios.domain.Persona;
import org.springframework.data.repository.CrudRepository;

public interface InterfazPersonaDao extends CrudRepository<Persona, Long> {
}
