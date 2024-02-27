package com.jarkial.users.model.dto.sgd;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import com.jarkial.users.model.dto.ctg.CtgCatalogoModel;

@Data
public class SgdRolPerfil {
    
    @Getter @Setter
    private Long sgdRolPerfilId;

    @Getter @Setter
    private CtgCatalogoModel sgdPerfil;

    @Getter @Setter
    private CtgCatalogoModel sgdRol;
}