package com.jarkial.users.repositories.sgd;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.jarkial.users.model.entity.sgd.SgdUsuarioPerfil;
import com.jarkial.users.repositories.AbstractBaseRepository;

import java.util.List;

@Repository
public interface SgdUsuarioPerfilRepository extends AbstractBaseRepository<SgdUsuarioPerfil, Long>{
    
    List<SgdUsuarioPerfil> findAllBySgdUsuario_SgdUsuarioId(Long idPadre)throws Exception;

    Page<SgdUsuarioPerfil> findAllBySgdPerfil_CtgCatalogoId(Long idPadre, Pageable pageable)throws Exception;
}