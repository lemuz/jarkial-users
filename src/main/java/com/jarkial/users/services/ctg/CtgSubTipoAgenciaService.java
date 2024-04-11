package com.jarkial.users.services.ctg;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jarkial.users.model.entity.ctg.CtgSubTipoAgencia;
import com.jarkial.users.services.AbstractCrudService;

public interface CtgSubTipoAgenciaService extends AbstractCrudService<CtgSubTipoAgencia, Long>{
    
    Page<CtgSubTipoAgencia> findAllByCtgTipoAgenciaAsPage(Long idPadre, Pageable pageable) throws Exception;
}
