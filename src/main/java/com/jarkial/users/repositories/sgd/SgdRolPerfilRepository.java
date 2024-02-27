package com.jarkial.users.repositories.sgd;

import org.springframework.stereotype.Repository;

import com.jarkial.users.model.entity.ctg.CtgCatalogo;
import com.jarkial.users.model.entity.sgd.SgdRolPerfil;
import com.jarkial.users.repositories.AbstractBaseRepository;

import java.util.List;

@Repository
public interface SgdRolPerfilRepository extends AbstractBaseRepository<SgdRolPerfil, Long>{
    
    List<SgdRolPerfil> findAllBySgdPerfil(CtgCatalogo sgdPerfil)throws Exception;
}