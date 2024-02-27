package com.jarkial.users.services.ctg;

import java.util.List;

import com.jarkial.users.model.entity.ctg.CtgCatalogo;
import com.jarkial.users.services.AbstractCrudService;

public interface CtgCatalogoService extends AbstractCrudService<CtgCatalogo, Long>{
    
    CtgCatalogo findByCtgCatalogoNombreAndCtgCatalogoPadreId(String nombre, Long ctgCatalogoPadreId) throws Exception;

    List<CtgCatalogo> findAllByCtgCatalogoPadreId(Long ctgCatalogoPadreId) throws Exception;
}