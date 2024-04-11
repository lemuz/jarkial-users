package com.jarkial.users.webservices.ctg;

import org.springframework.data.domain.Page;

import com.jarkial.users.model.dto.ctg.CtgAgenciaModel;
import com.jarkial.users.model.exceptions.MyServiceException;
import com.jarkial.users.webservices.AbstractCrudServiceWeb;

public interface CtgAgenciaServiceWeb extends AbstractCrudServiceWeb<CtgAgenciaModel, Long>{

    Page<CtgAgenciaModel> findAllByCtgSubTipoAgenciaAsPage(Long idPadre, int page, String orderByProperty, int itemsPerPage) throws MyServiceException;
    
}
