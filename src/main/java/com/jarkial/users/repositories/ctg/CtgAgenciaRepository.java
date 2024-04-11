package com.jarkial.users.repositories.ctg;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.jarkial.users.model.entity.ctg.CtgAgencia;
import com.jarkial.users.repositories.AbstractBaseRepository;

@Repository
public interface CtgAgenciaRepository extends AbstractBaseRepository<CtgAgencia, Long>{
    
    Page<CtgAgencia> findAllByCtgSubTipoAgencia_CtgSubTipoAgenciaId(Long ctgCatalogoPadreId, Pageable pageable);
}
