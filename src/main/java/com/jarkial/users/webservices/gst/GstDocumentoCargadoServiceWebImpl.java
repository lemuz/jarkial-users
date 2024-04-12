package com.jarkial.users.webservices.gst;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.jarkial.users.configuration.utils.MyUtils;
import com.jarkial.users.configuration.utils.MyUtilsConstant;
import com.jarkial.users.model.dto.gst.GstDocumentoCargadoModel;
import com.jarkial.users.model.entity.gst.GstDocumentoCargado;
import com.jarkial.users.model.exceptions.MyServiceException;
import com.jarkial.users.services.AbstractBaseServiceImpl;
import com.jarkial.users.services.gst.GstDocumentoCargadoService;

@Service
public class GstDocumentoCargadoServiceWebImpl extends AbstractBaseServiceImpl implements GstDocumentoCargadoServiceWeb {

    @Autowired
    GstDocumentoCargadoService service;

    @Override
    public boolean update(Long entityId, GstDocumentoCargadoModel model) throws Exception {
        long start = MyUtils.iniciaMetodo();
        model.setGstDocumentoCargadoId(entityId);
        GstDocumentoCargado entity = MyUtils.fullGstDocumentoCargadoEntity(model);
        //agregar logica para subir archivo y guardarlo en el sistema de archivos, se debe colocar la ruta donde se guarde en el entity
        try {
            entity = service.update(entity);
        } catch (Exception e) {
            MyUtils.errorMetodo(start, MyUtilsConstant.CODE_ERROR_WRITE, e);
        }
        if (entity.getGstDocumentoCargadoId() != null) {
            model = MyUtils.fullGstDocumentoCargadoModel(entity);
            MyUtils.finMetodo(start);
            return true;
        } else {
            MyUtils.finMetodo(start);
            return false;
        }
    }

    @Override
    public GstDocumentoCargadoModel findById(Long entityId) throws Exception {
        long start = MyUtils.iniciaMetodo();
        GstDocumentoCargado entity = new GstDocumentoCargado();
        try {
            entity = service.findById(entityId);
        } catch (Exception e) {
            MyUtils.errorMetodo(start, MyUtilsConstant.CODE_ERROR_READ, e);
        }
        GstDocumentoCargadoModel model = MyUtils.fullGstDocumentoCargadoModel(entity);
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
    public Page<GstDocumentoCargadoModel> findAllBySgdUsuarioAsPage(Long idPadre, int page, String orderByProperty, int itemsPerPage) throws MyServiceException {
        long start = MyUtils.iniciaMetodo();
        Page<GstDocumentoCargado> pageEntity = Page.empty();
        Page<GstDocumentoCargadoModel> pageModel = Page.empty();
        try {
            pageEntity = service.findAllBySgdUsuarioAsPage(idPadre, constructPageSpecificationDesc(page, orderByProperty, itemsPerPage));
            pageModel = MyUtils.fullGstDocumentoCargadoModelPage(pageEntity);
        } catch (Exception e) {
            MyUtils.errorMetodo(start, MyUtilsConstant.CODE_ERROR_READ, e);
        }
        MyUtils.finMetodo(start);
        return pageModel;
    }

}
