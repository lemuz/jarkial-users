package com.jarkial.users.model.dto.sgd;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class SgdUsuarioModel {

    @Getter @Setter
    private Long sgdUsuarioId;

    @Getter @Setter
    private String sgdUsuarioActivo;

    @Getter @Setter
    private String sgdUsuarioClave;

    @Getter @Setter
    private String sgdUsuarioEmail;

    @Getter @Setter
    private String sgdUsuarioPrimerApellido;

    @Getter @Setter
    private String sgdUsuarioPrimerNombre;

    @Getter @Setter
    private String sgdUsuarioSegundoApellido;

    @Getter @Setter
    private String sgdUsuarioSegundoNombre;

    @Getter @Setter
    private Long ctgAgencia;

    @Getter @Setter
    private Long sgdUsuarioPadre;

    @Getter @Setter
    private String sgdUsuarioUsername;

    @Getter @Setter
    private String sgdUsuarioEjecutivo;

    @Getter @Setter
    private String sgdUsuarioCambiarClave;

    @Getter @Setter
    private String sgdUsuarioBloqueado;

    @Getter @Setter
    private Integer sgdUsuarioLoginIntento;

    @Getter @Setter
    private String sgdUsuarioFechaExpiracion;

    @Getter @Setter
    private String sgdUsuarioTelefono;

    @Getter @Setter
    private String sgdUsuarioExtencion;

    @Getter @Setter
    private String sgdUsuarioCelular;

    @Getter @Setter
    private String sgdUsuarioCodigo;

    @Getter @Setter
    private String sgdUsuarioSupervisor;

    @Getter @Setter
    private Integer sgdUsuarioLogueado;
}