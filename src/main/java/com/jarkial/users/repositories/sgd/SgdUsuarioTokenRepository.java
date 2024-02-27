package com.jarkial.users.repositories.sgd;

import org.springframework.stereotype.Repository;

import com.jarkial.users.model.entity.sgd.SgdUsuarioToken;
import com.jarkial.users.repositories.AbstractBaseRepository;

@Repository
public interface SgdUsuarioTokenRepository extends AbstractBaseRepository<SgdUsuarioToken, String>{
    
}
