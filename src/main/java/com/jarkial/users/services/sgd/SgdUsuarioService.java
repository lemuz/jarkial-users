package com.jarkial.users.services.sgd;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jarkial.users.model.entity.sgd.SgdUsuario;
import com.jarkial.users.services.AbstractCrudService;

public interface SgdUsuarioService extends AbstractCrudService<SgdUsuario, Long>{

    SgdUsuario findBySgdUsuarioUsername(String username) throws Exception;

    Page<SgdUsuario> findBySgdUsuarioPadreAsPage(Long idPadre, Pageable pageable) throws Exception;
}