package com.jarkial.users.repositories.sgd;

import org.springframework.stereotype.Repository;

import com.jarkial.users.model.entity.sgd.SgdUsuario;
import com.jarkial.users.model.entity.sgd.SgdUsuarioPerfil;
import com.jarkial.users.repositories.AbstractBaseRepository;

import java.util.List;

@Repository
public interface SgdUsuarioPerfilRepository extends AbstractBaseRepository<SgdUsuarioPerfil, Long>{
    
    List<SgdUsuarioPerfil> findAllBySgdUsuario(SgdUsuario sgdUsuario)throws Exception;
}