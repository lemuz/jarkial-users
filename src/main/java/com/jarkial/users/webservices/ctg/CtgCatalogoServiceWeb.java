package com.jarkial.users.webservices.ctg;

import java.util.List;

import org.springframework.data.domain.Page;

import com.jarkial.users.model.dto.ctg.CtgCatalogoModel;
import com.jarkial.users.model.exceptions.MyServiceException;
import com.jarkial.users.webservices.AbstractCrudServiceWeb;

public interface CtgCatalogoServiceWeb extends AbstractCrudServiceWeb<CtgCatalogoModel, Long>{

    List<CtgCatalogoModel> findByCtgCatNombreAndCtgCatalogoPadreAsList(String ctgCatalogoNombre,Long idPadre) throws MyServiceException;

    Page<CtgCatalogoModel> findAllByCtgCatalogoPadreAsPage(Long idPadre, int page, String orderByProperty, int itemsPerPage) throws MyServiceException;


}