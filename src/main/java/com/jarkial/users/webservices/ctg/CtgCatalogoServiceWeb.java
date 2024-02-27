package com.jarkial.users.webservices.ctg;

import java.util.List;

import com.jarkial.users.model.dto.ctg.CtgCatalogoModel;
import com.jarkial.users.model.exceptions.MyServiceException;
import com.jarkial.users.webservices.AbstractCrudServiceWeb;

public interface CtgCatalogoServiceWeb extends AbstractCrudServiceWeb<CtgCatalogoModel, Long>{

    List<CtgCatalogoModel> findAllByCtgCatalogoPadre(Long idPadre) throws MyServiceException;

}