package com.enzow.PeopleManagement.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Class to configure the app's security using Spring Security
 * defining authorization, authentication and session management rules
 *
 * @author Enzo Weimann
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Injection of the service responsible for loading user data during authentication
     */
    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * Component that provides the password encryption using BCrypt
     *
     * @return Creates an instance of {@link BCryptPasswordEncoder}
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder () {
        return new BCryptPasswordEncoder();
    }

    /**
     * Configures the authentication manager to use {@link UserDetailsService} and the password encryption
     *
     * @param build Constructor of the authentication
     * @throws Exception Handles possible exceptions during the configuration
     */
    public void configure(AuthenticationManagerBuilder build) throws Exception{
        build.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    /**
     * Defines security rules for the app's HTTP routes,
     * enforcing role-permitted access, login/logout settings, and error handling.
     *
     * @param http Object to configure HTTP security
     * @return Configured filter chain {@link SecurityFilterChain}
     * @throws Exception Handles possible exceptions during the configuration
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                                .requestMatchers("/webjars/**", "/login", "/resources/**").permitAll() // Permitir acceso a login y recursos estáticos
                                .requestMatchers("/editar/**", "/agregar/**", "/eliminar/**").hasRole("ADMIN") // Restricción de acceso por rol
                                .requestMatchers("/").hasAnyRole("USER", "ADMIN") // Acceso a usuarios autenticados
                                .anyRequest().authenticated() // Cualquier otra solicitud requiere autenticación
                )
                .formLogin(formLogin -> formLogin
                        .loginPage("/login") // Página de login personalizada
                        .defaultSuccessUrl("/", true) // Redirigir después del login exitoso
                        .permitAll() // Permitir acceso al formulario de login
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                )
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .accessDeniedPage("/errores/403")
                );
        return http.build();
    }


}
