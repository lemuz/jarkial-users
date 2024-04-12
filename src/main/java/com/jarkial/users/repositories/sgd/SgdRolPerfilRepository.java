package com.jarkial.users.repositories.sgd;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.jarkial.users.model.entity.sgd.SgdRolPerfil;
import com.jarkial.users.repositories.AbstractBaseRepository;

@Repository
public interface SgdRolPerfilRepository extends AbstractBaseRepository<SgdRolPerfil, Long>{
    
    Page<SgdRolPerfil> findAllBySgdPerfil_CtgCatalogoId(Long idPadre, Pageable pageable)throws Exception;

    List<SgdRolPerfil> findAllBySgdPerfil_CtgCatalogoId(Long idPadre)throws Exception;
}