package com.jarkial.users.model.dto.sgd;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class SgdRolPerfilModel {
    
    @Getter @Setter
    private Long sgdRolPerfilId;

    @Getter @Setter
    private Long sgdPerfil;

    @Getter @Setter
    private Long sgdRol;
}