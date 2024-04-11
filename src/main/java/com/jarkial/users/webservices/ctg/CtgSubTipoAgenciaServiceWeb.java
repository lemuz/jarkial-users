package com.jarkial.users.webservices.ctg;

import org.springframework.data.domain.Page;

import com.jarkial.users.model.dto.ctg.CtgSubTipoAgenciaModel;
import com.jarkial.users.model.exceptions.MyServiceException;
import com.jarkial.users.webservices.AbstractCrudServiceWeb;

public interface CtgSubTipoAgenciaServiceWeb extends AbstractCrudServiceWeb<CtgSubTipoAgenciaModel, Long>{

    Page<CtgSubTipoAgenciaModel> findAllByCtgTipoAgenciaAsPage(Long idPadre, int page, String orderByProperty, int itemsPerPage) throws MyServiceException;
    
}
