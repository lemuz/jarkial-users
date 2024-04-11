package com.jarkial.users.model.dto.sgd;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class SgdUsuarioPerfilModel {

    @Getter @Setter
    private Long sgdUsuarioPerfilId;

    @Getter @Setter
    private Long sgdPerfil;
    
    @Getter @Setter
    private Long sgdUsuario;
}