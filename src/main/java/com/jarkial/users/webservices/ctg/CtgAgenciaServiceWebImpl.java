package com.jarkial.users.webservices.ctg;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jarkial.users.configuration.utils.MyUtils;
import com.jarkial.users.model.dto.ctg.CtgAgenciaModel;
import com.jarkial.users.model.entity.ctg.CtgAgencia;
import com.jarkial.users.model.entity.ctg.CtgSubTipoAgencia;
import com.jarkial.users.model.exceptions.MyServiceException;
import com.jarkial.users.services.AbstractBaseServiceImpl;
import com.jarkial.users.services.ctg.CtgAgenciaService;

@Service
public class CtgAgenciaServiceWebImpl extends AbstractBaseServiceImpl implements CtgAgenciaServiceWeb {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CtgAgenciaService ctgAgenciaService;

    @Override
    public boolean update(Long id, CtgAgenciaModel model) throws Exception {
        logger.info("[CtgAgenciaServiceWebImpl](update)");
        CtgAgencia entity = new CtgAgencia();
        BeanUtils.copyProperties(model, entity);
        entity.setCtgSubTipoAgencia(
                model.getCtgSubTipoAgencia() != null ? new CtgSubTipoAgencia(model.getCtgSubTipoAgencia())
                        : null);
        entity.setCtgAgenciaId(id);
        try {
            entity = ctgAgenciaService.update(entity);
        } catch (Exception e) {
            e.printStackTrace();
            throw new MyServiceException("00200", MyUtils.getStackTrace(e));
        }
        model = new CtgAgenciaModel();
        BeanUtils.copyProperties(entity, model);
        model.setCtgSubTipoAgencia(
                entity.getCtgSubTipoAgencia() != null ? entity.getCtgSubTipoAgencia().getCtgSubTipoAgenciaId()
                        : null);
        return model.getCtgAgenciaId() != null;
    }

    @Override
    public CtgAgenciaModel findById(Long id) throws Exception {
        logger.info("[CtgAgenciaServiceWebImpl](findById)");
        CtgAgencia entity = new CtgAgencia();
        try {
            entity = ctgAgenciaService.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new MyServiceException("00100", MyUtils.getStackTrace(e));
        }
        CtgAgenciaModel model = new CtgAgenciaModel();
        BeanUtils.copyProperties(entity, model);
        model.setCtgSubTipoAgencia(
                entity.getCtgSubTipoAgencia() != null ? entity.getCtgSubTipoAgencia().getCtgSubTipoAgenciaId() : null);
        return model;
    }

    @Override
    public boolean deleteById(Long id) throws Exception {
        logger.info("[CtgAgenciaServiceWebImpl](deleteById)");
        boolean respuesta = false;
        try {
            respuesta = ctgAgenciaService.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new MyServiceException("00300", MyUtils.getStackTrace(e));
        }
        return respuesta;
    }
    
}
