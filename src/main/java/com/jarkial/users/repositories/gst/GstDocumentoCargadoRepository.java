package com.jarkial.users.repositories.gst;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.jarkial.users.model.entity.gst.GstDocumentoCargado;
import com.jarkial.users.repositories.AbstractBaseRepository;

@Repository
public interface GstDocumentoCargadoRepository extends AbstractBaseRepository<GstDocumentoCargado, Long>{
    
    Page<GstDocumentoCargado> findAllBySgdUsuario_SgdUsuarioId(Long idPadre, Pageable pageable);

}
