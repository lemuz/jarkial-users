package com.jarkial.users.services.sgd;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jarkial.users.model.entity.sgd.SgdUsuarioPerfil;
import com.jarkial.users.services.AbstractCrudService;

public interface SgdUsuarioPerfilService extends AbstractCrudService<SgdUsuarioPerfil, Long>{

    List<SgdUsuarioPerfil> findAllBySgdUsuarioAsList(Long idPadre)throws Exception;

    Page<SgdUsuarioPerfil> findAllBySgdPerfilAsPage(Long idPadre, Pageable pageable)throws Exception;
}