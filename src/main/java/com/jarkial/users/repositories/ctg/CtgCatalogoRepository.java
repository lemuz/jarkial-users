package com.jarkial.users.repositories.ctg;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.jarkial.users.model.entity.ctg.CtgCatalogo;
import com.jarkial.users.repositories.AbstractBaseRepository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CtgCatalogoRepository extends AbstractBaseRepository<CtgCatalogo, Long>{

    Optional<CtgCatalogo> findByCtgCatalogoNombreAndCtgCatalogoPadre_CtgCatalogoId(String ctgCatalogoNombre, Long idPadre);

    List<CtgCatalogo> findAllByCtgCatalogoPadre_CtgCatalogoId(Long idPadre);

    Page<CtgCatalogo> findAllByCtgCatalogoPadre_CtgCatalogoId(Long idPadre, Pageable pageable);
    
}