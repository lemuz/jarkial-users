package com.jarkial.users.services.ctg;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jarkial.users.model.entity.ctg.CtgAgencia;
import com.jarkial.users.services.AbstractCrudService;

public interface CtgAgenciaService extends AbstractCrudService<CtgAgencia, Long>{
 
    Page<CtgAgencia> findAllByCtgSubTipoAgenciaAsPage(Long idPadre, Pageable pageable) throws Exception;
}
