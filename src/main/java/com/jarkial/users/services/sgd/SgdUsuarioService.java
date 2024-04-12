package com.jarkial.users.services.sgd;

import java.util.List;

import com.jarkial.users.model.entity.sgd.SgdUsuario;
import com.jarkial.users.services.AbstractCrudService;

public interface SgdUsuarioService extends AbstractCrudService<SgdUsuario, Long>{

    SgdUsuario findBySgdUsuarioUsername(String username) throws Exception;

    List<SgdUsuario> findBySgdUsuarioPadreAsList(Long idPadre) throws Exception;
}