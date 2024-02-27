package com.jarkial.users.model.dto.sgd;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class SgdUsuarioToken {

    @Getter @Setter
    private String sgdUsuarioId;

    @Getter @Setter
    private String sgdUsuarioClave;

    @Getter @Setter
    private String sgdUsuarioEmail;

    @Getter @Setter
    private String sgdUsuarioNombre;

    @Getter @Setter
    private String sgdUsuarioUsername;

    @Getter @Setter
    private String sgdUsuarioGrantType;

    @Getter @Setter
    private String sgdUsuarioScope;

    @Getter @Setter
    private String sgdUsuarioAuthorization;
    
}