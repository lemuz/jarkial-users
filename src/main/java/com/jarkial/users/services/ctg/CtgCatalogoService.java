package com.jarkial.users.services.ctg;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jarkial.users.model.entity.ctg.CtgCatalogo;
import com.jarkial.users.services.AbstractCrudService;

public interface CtgCatalogoService extends AbstractCrudService<CtgCatalogo, Long>{
    
    CtgCatalogo findByCtgCatalogoNombreAndCtgCatalogoPadreId(String nombre, Long ctgCatalogoPadreId) throws Exception;

    List<CtgCatalogo> findAllByCtgCatalogoPadreIdAsList(Long ctgCatalogoPadreId) throws Exception;

    Page<CtgCatalogo> findAllByCtgCatalogoPadreIdAsPage(Long ctgCatalogoPadreId, Pageable pageable) throws Exception;
}