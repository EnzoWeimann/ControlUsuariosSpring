package com.enzow.PeopleManagement.service;

import com.enzow.PeopleManagement.dao.IUserDao;
import com.enzow.PeopleManagement.domain.Rol;
import com.enzow.PeopleManagement.domain.Usuario;
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

/**
 * Implementation of the {@link UserDetailsService} used by Spring Security to authenticate users from the DB
 * Retrieves a user by their username and uses it to construct an objet along with their password and roles
 */
@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    /**
     * User repository injection to access user and role data
     */
    @Autowired
    private IUserDao IUserDao;

    /**
     * Loads a user from the DB using their username.
     * Converts associated roles into {@link GrantedAuthority} instances required by Spring Security for the authorization process
     *
     * @param username User's name used for authentication
     * @return An {@link UserDetails} object with its username, password and roles
     * @throws UsernameNotFoundException Handles situation where there's no user with the specified username
     */
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //Search for the user in the DB
        Usuario usuario = IUserDao.findByUsername(username);

        //Throws error if no user is found
        if (username == null) {
            throw new UsernameNotFoundException(username);
        }

        //List of authorized roles required for Spring Security
        var roles = new ArrayList<GrantedAuthority>();
        for (Rol rol: usuario.getRoles()) {
            roles.add(new SimpleGrantedAuthority(rol.getNombre()));
        }

        return new User(usuario.getUsername(), usuario.getPassword(), roles);
    }
}
