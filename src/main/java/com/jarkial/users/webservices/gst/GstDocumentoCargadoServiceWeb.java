package com.jarkial.users.webservices.gst;

import org.springframework.data.domain.Page;

import com.jarkial.users.model.dto.gst.GstDocumentoCargadoModel;
import com.jarkial.users.model.exceptions.MyServiceException;
import com.jarkial.users.webservices.AbstractCrudServiceWeb;

public interface GstDocumentoCargadoServiceWeb extends AbstractCrudServiceWeb<GstDocumentoCargadoModel, Long>{

    Page<GstDocumentoCargadoModel> findAllBySgdUsuarioAsPage(Long idPadre, int page, String orderByProperty, int itemsPerPage) throws MyServiceException;
    
}
