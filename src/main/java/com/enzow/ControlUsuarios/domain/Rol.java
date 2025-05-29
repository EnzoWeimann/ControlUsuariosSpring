package com.enzow.ControlUsuarios.domain;

import jakarta.persistence.*;
import lombok.Data;
import jakarta.validation.constraints.NotEmpty;

import java.io.Serializable;

@Entity
@Data
@Table(name="rol") //En base de datos se uso con minusculas, por eso se hace esta aclaracion
public class Rol implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//autoincremental en BD
    private Long id_rol;

    @NotEmpty
    private String nombre;

}
