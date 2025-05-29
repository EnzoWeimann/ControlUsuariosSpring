package com.enzow.ControlUsuarios.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    //Se define tipo de encriptamiento a utilizar (BCrypt)
    @Bean
    public BCryptPasswordEncoder passwordEncoder () {
        return new BCryptPasswordEncoder();
    }

    //Se indica que se usa el UserDetailsService y BCrypt, inyectando de manera automatica un objeto de autenticacion
    public void configure(AuthenticationManagerBuilder build) throws Exception{
        build.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

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
