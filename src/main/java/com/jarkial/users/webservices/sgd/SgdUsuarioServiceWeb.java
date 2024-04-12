package com.jarkial.users.webservices.sgd;

import java.util.List;

import org.springframework.data.domain.Page;

import com.jarkial.users.model.dto.sgd.SgdUsuarioModel;
import com.jarkial.users.model.exceptions.MyServiceException;
import com.jarkial.users.webservices.AbstractCrudServiceWeb;

public interface SgdUsuarioServiceWeb extends AbstractCrudServiceWeb<SgdUsuarioModel, Long>{
    
    void actualizarSgdUsuarioLogueado(String sgdUsuarioUsername, Integer i);

    Page<SgdUsuarioModel> findAllAsPage(int page, String orderByProperty, int itemsPerPage) throws MyServiceException;

    List<SgdUsuarioModel> findAllBySgdUsuarioAsList(Long idPadre) throws MyServiceException;

}