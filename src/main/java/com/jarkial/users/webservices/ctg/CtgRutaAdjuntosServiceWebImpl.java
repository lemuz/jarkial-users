package com.jarkial.users.webservices.ctg;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.jarkial.users.configuration.utils.MyUtils;
import com.jarkial.users.configuration.utils.MyUtilsConstant;
import com.jarkial.users.model.dto.ctg.CtgRutaAdjuntosModel;
import com.jarkial.users.model.entity.ctg.CtgRutaAdjuntos;
import com.jarkial.users.model.exceptions.MyServiceException;
import com.jarkial.users.services.AbstractBaseServiceImpl;
import com.jarkial.users.services.ctg.CtgRutaAdjuntosService;

@Service
public class CtgRutaAdjuntosServiceWebImpl extends AbstractBaseServiceImpl implements CtgRutaAdjuntosServiceWeb {

    @Autowired
    CtgRutaAdjuntosService service;

    @Override
    public boolean update(Long entityId, CtgRutaAdjuntosModel model) throws Exception {
        long start = MyUtils.iniciaMetodo();
        model.setCtgRutaAdjuntosId(entityId);
        CtgRutaAdjuntos entity = MyUtils.fullCtgRutaAdjuntosEntity(model);
        try {
            entity = service.update(entity);
        } catch (Exception e) {
            MyUtils.errorMetodo(start, MyUtilsConstant.CODE_ERROR_WRITE, e);
        }
        if (entity.getCtgRutaAdjuntosId() != null) {
            model = MyUtils.fullCtgRutaAdjuntosModel(entity);
            MyUtils.finMetodo(start);
            return true;
        } else {
            MyUtils.finMetodo(start);
            return false;
        }
    }

    @Override
    public CtgRutaAdjuntosModel findById(Long entityId) throws Exception {
        long start = MyUtils.iniciaMetodo();
        CtgRutaAdjuntos entity = new CtgRutaAdjuntos();
        try {
            entity = service.findById(entityId);
        } catch (Exception e) {
            MyUtils.errorMetodo(start, MyUtilsConstant.CODE_ERROR_READ, e);
        }
        CtgRutaAdjuntosModel model = MyUtils.fullCtgRutaAdjuntosModel(entity);
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
    public List<CtgRutaAdjuntosModel> findAllBySgdUsuarioAsList(Long idPadre) throws MyServiceException {
        long start = MyUtils.iniciaMetodo();
        List<CtgRutaAdjuntos> findAll = new ArrayList<>();
        try {
            findAll = service.findAllBySgdUsuario(idPadre);
        } catch (Exception e) {
            MyUtils.errorMetodo(start, MyUtilsConstant.CODE_ERROR_READ, e);
        }
        List<CtgRutaAdjuntosModel> lista = new ArrayList<>();
        findAll.stream().filter(catalogo -> catalogo != null).forEach(entity -> {
            CtgRutaAdjuntosModel model = MyUtils.fullCtgRutaAdjuntosModel(entity);
            lista.add(model);
        });
        MyUtils.finMetodo(start);
        return lista;
    }

    @Override
    public Page<CtgRutaAdjuntosModel> findAllAsPage(int page, String orderByProperty, int itemsPerPage) throws MyServiceException {
        Page<CtgRutaAdjuntos> pageEntity = Page.empty();
        Page<CtgRutaAdjuntosModel> pageModel = Page.empty();
        try {
            pageEntity = service.findAllAsPage(constructPageSpecificationDesc(page, orderByProperty, itemsPerPage));
            pageModel = MyUtils.fullCtgRutaAdjuntosModelPage(pageEntity);
        } catch (Exception e) {
            throw new MyServiceException(e);
        }
        return pageModel;
    }
}