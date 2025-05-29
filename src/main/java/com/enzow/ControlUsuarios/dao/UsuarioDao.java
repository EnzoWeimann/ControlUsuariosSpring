package com.enzow.ControlUsuarios.dao;

import com.enzow.ControlUsuarios.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioDao extends JpaRepository<Usuario, Long> {
    Usuario findByUsername (String username);
}
