package com.jarkial.users.webservices.ctg;

import java.util.List;

import com.jarkial.users.model.dto.ctg.CtgTipoAgenciaModel;
import com.jarkial.users.model.exceptions.MyServiceException;
import com.jarkial.users.webservices.AbstractCrudServiceWeb;

public interface CtgTipoAgenciaServiceWeb extends AbstractCrudServiceWeb<CtgTipoAgenciaModel, Long>{
    
    List<CtgTipoAgenciaModel> findAllAsList() throws MyServiceException;
}
