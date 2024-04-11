package com.jarkial.users.repositories.ctg;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.jarkial.users.model.entity.ctg.CtgSubTipoAgencia;
import com.jarkial.users.repositories.AbstractBaseRepository;

@Repository
public interface CtgSubTipoAgenciaRepository extends AbstractBaseRepository<CtgSubTipoAgencia, Long>{
    
    Page<CtgSubTipoAgencia> findAllByCtgTipoAgencia_CtgTipoAgenciaId(Long idPadre, Pageable pageable) throws Exception;
}
