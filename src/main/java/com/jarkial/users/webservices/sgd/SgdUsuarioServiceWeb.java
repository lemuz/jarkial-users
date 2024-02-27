package com.jarkial.users.webservices.sgd;

import com.jarkial.users.model.dto.sgd.SgdUsuarioModel;
import com.jarkial.users.webservices.AbstractCrudServiceWeb;

public interface SgdUsuarioServiceWeb extends AbstractCrudServiceWeb<SgdUsuarioModel, Long>{
    
    void actualizarSgdUsuarioLogueado(String sgdUsuarioUsername, Integer i);

}