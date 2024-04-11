package com.jarkial.users.webservices.ctg;

import java.util.List;

import org.springframework.data.domain.Page;

import com.jarkial.users.model.dto.ctg.CtgRutaAdjuntosModel;
import com.jarkial.users.model.exceptions.MyServiceException;
import com.jarkial.users.webservices.AbstractCrudServiceWeb;

public interface CtgRutaAdjuntosServiceWeb extends AbstractCrudServiceWeb<CtgRutaAdjuntosModel, Long>{
    
    List<CtgRutaAdjuntosModel> findAllBySgdUsuarioAsList(Long idPadre) throws MyServiceException;

    Page<CtgRutaAdjuntosModel> findAllAsPage(int page, String orderByProperty, int itemsPerPage) throws MyServiceException;
}
