package com.enzow.ControlUsuarios.servicio;

import com.enzow.ControlUsuarios.dao.UsuarioDao;
import com.enzow.ControlUsuarios.domain.Rol;
import com.enzow.ControlUsuarios.domain.Usuario;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service("userDetailsService")
@Slf4j
public class UsuarioService implements UserDetailsService {

    //Inyección de usuarioDao para interactuar con clases Usuario y Rol
    @Autowired
    private UsuarioDao usuarioDao;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioDao.findByUsername(username);

        if (username == null) {
            //Si no se encuentra, se muestra el error adecuado
            throw new UsernameNotFoundException(username);
        }

        var roles = new ArrayList<GrantedAuthority>();
        //GrantedAuthority es el tipo que requiere spring para manejar roles con JPA

        for (Rol rol: usuario.getRoles()) {
            //Se pasa cada rol encontrado al tipo "SimpleGrantedAuthority"
            roles.add(new SimpleGrantedAuthority(rol.getNombre()));
        }

        //Se reotrna un objeto de tipo UserDetails con todos los datos recuperados
        return new User(usuario.getUsername(), usuario.getPassword(), roles);
    }
}
