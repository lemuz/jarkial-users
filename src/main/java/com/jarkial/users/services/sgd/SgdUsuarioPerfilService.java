package com.jarkial.users.services.sgd;

import java.util.List;

import com.jarkial.users.model.entity.sgd.SgdUsuario;
import com.jarkial.users.model.entity.sgd.SgdUsuarioPerfil;
import com.jarkial.users.services.AbstractCrudService;

public interface SgdUsuarioPerfilService extends AbstractCrudService<SgdUsuarioPerfil, Long>{

    List<SgdUsuarioPerfil> findAllBySgdUsuario(SgdUsuario sgdUsuario)throws Exception;
    
/*
    List<SgdUsuarioPerfil> findAll() throws Exception;

    SgdUsuarioPerfil findById(Long id) throws Exception;

    SgdUsuarioPerfil update(SgdUsuarioPerfil sgdUsuarioPerfil) throws Exception;

    boolean deleteById(Long id) throws Exception;
 */    
}