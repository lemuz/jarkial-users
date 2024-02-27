package com.jarkial.users.model.dto.sgd;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import com.jarkial.users.model.dto.ctg.CtgCatalogoModel;

@Data
public class SgdUsuarioPerfil {

    @Getter @Setter
    private Long sgdUsuarioPerfilId;

    @Getter @Setter
    private CtgCatalogoModel sgdPerfil;
    
    @Getter @Setter
    private SgdUsuarioModel sgdUsuario;
}