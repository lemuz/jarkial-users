package com.jarkial.users.services.sgd;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jarkial.users.model.entity.sgd.SgdRolPerfil;
import com.jarkial.users.services.AbstractCrudService;


public interface SgdRolPerfilService extends AbstractCrudService<SgdRolPerfil, Long>{

    Page<SgdRolPerfil> findAllBySgdPerfilAsPage(Long idPadre, Pageable pageable)throws Exception;

    List<SgdRolPerfil> findAllBySgdPerfilAsList(Long idPadre) throws Exception;

}