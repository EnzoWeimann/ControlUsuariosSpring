package com.enzow.ControlUsuarios.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import java.util.List;

import java.io.Serializable;

@Entity
@Data
@Table(name="usuario")
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_usuario;

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

    //Mapeo entre la clase Usuario y la clase Rol
    //(un usuario puede tener multiples roles)
    @OneToMany
    //Relacion entre las tablas -> llave foranea en tabla Rol = id_usuario
    @JoinColumn(name = "id_usuario")
    private List<Rol> roles; //Lista de roles asociados a un usuario
}
