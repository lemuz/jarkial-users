package com.jarkial.users.webservices.sgd;

import org.springframework.data.domain.Page;

import com.jarkial.users.model.dto.sgd.SgdRolPerfilModel;
import com.jarkial.users.model.exceptions.MyServiceException;
import com.jarkial.users.webservices.AbstractCrudServiceWeb;

public interface SgdRolPerfilServiceWeb extends AbstractCrudServiceWeb<SgdRolPerfilModel, Long>{

    Page<SgdRolPerfilModel> findAllBysgdPerfilAsPage(Long idPadre, int page, String orderByProperty, int itemsPerPage) throws MyServiceException;
    
}
