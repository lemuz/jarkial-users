package com.jarkial.users.repositories.sgd;

import org.springframework.stereotype.Repository;

import com.jarkial.users.model.entity.sgd.SgdUsuario;
import com.jarkial.users.repositories.AbstractBaseRepository;

import java.util.Optional;

@Repository
public interface SgdUsuarioRepository extends AbstractBaseRepository<SgdUsuario, Long>{
    
    Optional<SgdUsuario> findBySgdUsuarioUsername(String username);
}