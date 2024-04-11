package com.jarkial.users.webservices.sgd;

import java.util.List;

import org.springframework.data.domain.Page;

import com.jarkial.users.model.dto.sgd.SgdUsuarioModel;
import com.jarkial.users.model.dto.sgd.SgdUsuarioPerfilModel;
import com.jarkial.users.model.exceptions.MyServiceException;
import com.jarkial.users.webservices.AbstractCrudServiceWeb;

public interface SgdUsuarioServiceWeb extends AbstractCrudServiceWeb<SgdUsuarioModel, Long>{
    
    void actualizarSgdUsuarioLoguead(String sgdUsuarioUsername, Integer i);

    List<SgdUsuarioModel> findAllAsList() throws MyServiceException;

    public Page<SgdUsuarioModel> findAllBySgdUsuarioAsPage(Long idPadre, int page, String orderByProperty, int itemsPerPage) throws MyServiceException;

}