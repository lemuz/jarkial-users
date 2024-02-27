package com.jarkial.users.services.sgd;

import java.util.List;

import com.jarkial.users.model.entity.ctg.CtgCatalogo;
import com.jarkial.users.model.entity.sgd.SgdRolPerfil;
import com.jarkial.users.services.AbstractCrudService;


public interface SgdRolPerfilService extends AbstractCrudService<SgdRolPerfil, Long>{

    List<SgdRolPerfil> findAllBySgdPerfil(CtgCatalogo sgdPerfil)throws Exception;

/* 
    List<SgdRolPerfil> findAll() throws Exception;

    SgdRolPerfil findById(Long id) throws Exception;

    SgdRolPerfil update(SgdRolPerfil sgdRolPerfil) throws Exception;

    boolean deleteById(Long id) throws Exception;
*/
}