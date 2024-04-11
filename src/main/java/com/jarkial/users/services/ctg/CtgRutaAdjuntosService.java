package com.jarkial.users.services.ctg;

import java.util.List;

import com.jarkial.users.model.entity.ctg.CtgRutaAdjuntos;
import com.jarkial.users.services.AbstractCrudService;

public interface CtgRutaAdjuntosService extends AbstractCrudService<CtgRutaAdjuntos, Long>{
    
    List<CtgRutaAdjuntos> findAllBySgdUsuario(Long idPadre) throws Exception;

}
