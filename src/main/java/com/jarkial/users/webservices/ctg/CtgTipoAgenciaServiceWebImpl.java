package com.jarkial.users.webservices.ctg;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jarkial.users.configuration.utils.MyUtils;
import com.jarkial.users.configuration.utils.MyUtilsConstant;
import com.jarkial.users.model.dto.ctg.CtgTipoAgenciaModel;
import com.jarkial.users.model.entity.ctg.CtgTipoAgencia;
import com.jarkial.users.model.exceptions.MyServiceException;
import com.jarkial.users.services.AbstractBaseServiceImpl;
import com.jarkial.users.services.ctg.CtgTipoAgenciaService;

@Service
public class CtgTipoAgenciaServiceWebImpl extends AbstractBaseServiceImpl implements CtgTipoAgenciaServiceWeb {

    @Autowired
    CtgTipoAgenciaService service;

    @Override
    public boolean update(Long entityId, CtgTipoAgenciaModel model) throws Exception {
        long start = MyUtils.iniciaMetodo();
        CtgTipoAgencia entity = new CtgTipoAgencia();
        BeanUtils.copyProperties(model, entity);
        entity.setCtgTipoAgenciaId(entityId);
        try {
            entity = service.update(entity);
        } catch (Exception e) {
            MyUtils.errorMetodo(start, MyUtilsConstant.CODE_ERROR_WRITE, e);
        }
        if (entity.getCtgTipoAgenciaId() != null) {
            model = new CtgTipoAgenciaModel();
            BeanUtils.copyProperties(entity, model);
            MyUtils.finMetodo(start);
            return true;
        } else {
            MyUtils.finMetodo(start);
            return false;
        }
    }

    @Override
    public CtgTipoAgenciaModel findById(Long entityId) throws Exception {
        long start = MyUtils.iniciaMetodo();
        CtgTipoAgencia entity = new CtgTipoAgencia();
        try {
            entity = service.findById(entityId);
        } catch (Exception e) {
            MyUtils.errorMetodo(start, MyUtilsConstant.CODE_ERROR_READ, e);
        }
        CtgTipoAgenciaModel model = new CtgTipoAgenciaModel();
        BeanUtils.copyProperties(entity, model);
        MyUtils.finMetodo(start);
        return model;
    }

    @Override
    public boolean deleteById(Long entityId) throws Exception {
        long start = MyUtils.iniciaMetodo();
        boolean respuesta = false;
        try {
            respuesta = service.deleteById(entityId);
        } catch (Exception e) {
            MyUtils.errorMetodo(start, MyUtilsConstant.CODE_ERROR_DELETE, e);
        }
        MyUtils.finMetodo(start);
        return respuesta;
    }

    @Override
    public List<CtgTipoAgenciaModel> findAllAsList()
            throws MyServiceException {
        long start = MyUtils.iniciaMetodo();
        List<CtgTipoAgencia> findAll = new ArrayList<>();
        try {
            findAll = service.findAllAsList();
        } catch (Exception e) {
            MyUtils.errorMetodo(start, MyUtilsConstant.CODE_ERROR_READ, e);
        }
        List<CtgTipoAgenciaModel> lista = new ArrayList<>();
        findAll.stream().filter(entity -> entity != null).forEach(entity -> {
            CtgTipoAgenciaModel model = new CtgTipoAgenciaModel();
            BeanUtils.copyProperties(entity, model);
            lista.add(model);
        });
        MyUtils.finMetodo(start);
        return lista;
    }
}