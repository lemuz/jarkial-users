package com.jarkial.users.repositories.ctg;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jarkial.users.model.entity.ctg.CtgRutaAdjuntos;
import com.jarkial.users.repositories.AbstractBaseRepository;

@Repository
public interface CtgRutaAdjuntosRepository extends AbstractBaseRepository<CtgRutaAdjuntos, Long>{
    
    List<CtgRutaAdjuntos> findAllBySgdUsuario_SgdUsuarioId(Long idPadre);
    
}
