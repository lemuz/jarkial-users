package com.jarkial.users.services.gst;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jarkial.users.model.entity.gst.GstDocumentoCargado;
import com.jarkial.users.services.AbstractCrudService;

public interface GstDocumentoCargadoService extends AbstractCrudService<GstDocumentoCargado, Long>{
    
    Page<GstDocumentoCargado> findAllBySgdUsuarioAsPage(Long idPadre, Pageable pageable) throws Exception 
}