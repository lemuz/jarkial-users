package com.jarkial.users.webservices.sgd;

import java.util.List;

import org.springframework.data.domain.Page;

import com.jarkial.users.model.dto.sgd.SgdUsuarioPerfilModel;
import com.jarkial.users.model.exceptions.MyServiceException;
import com.jarkial.users.webservices.AbstractCrudServiceWeb;

public interface SgdUsuarioPerfilServiceWeb extends AbstractCrudServiceWeb<SgdUsuarioPerfilModel, Long>{

    List<SgdUsuarioPerfilModel> findAllBySgdUsuarioAsList(Long idPadre) throws MyServiceException;

    Page<SgdUsuarioPerfilModel> findAllBysgdPerfilAsPage(Long idPadre, int page, String orderByProperty, int itemsPerPage) throws MyServiceException;
    
}
