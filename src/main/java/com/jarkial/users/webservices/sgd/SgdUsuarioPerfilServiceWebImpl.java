package com.jarkial.users.webservices.sgd;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.jarkial.users.configuration.utils.MyUtils;
import com.jarkial.users.configuration.utils.MyUtilsConstant;
import com.jarkial.users.model.dto.ctg.CtgCatalogoModel;
import com.jarkial.users.model.dto.sgd.SgdUsuarioPerfilModel;
import com.jarkial.users.model.entity.ctg.CtgCatalogo;
import com.jarkial.users.model.entity.sgd.SgdUsuarioPerfil;
import com.jarkial.users.model.exceptions.MyServiceException;
import com.jarkial.users.services.AbstractBaseServiceImpl;
import com.jarkial.users.services.sgd.SgdUsuarioPerfilService;

@Service
public class SgdUsuarioPerfilServiceWebImpl extends AbstractBaseServiceImpl implements SgdUsuarioPerfilServiceWeb {

    @Autowired
    SgdUsuarioPerfilService service;

    @Override
    public boolean update(Long entityId, SgdUsuarioPerfilModel model) throws Exception {
        long start = MyUtils.iniciaMetodo();
        model.setSgdUsuarioPerfilId(entityId);
        SgdUsuarioPerfil entity = MyUtils.fullSgdUsuarioPerfilEntity(model);
        try {
            entity = service.update(entity);
        } catch (Exception e) {
            MyUtils.errorMetodo(start, MyUtilsConstant.CODE_ERROR_WRITE, e);
        }
        if (entity.getSgdUsuarioPerfilId() != null) {
            model = MyUtils.fullSgdUsuarioPerfilModel(entity);
            MyUtils.finMetodo(start);
            return true;
        } else {
            MyUtils.finMetodo(start);
            return false;
        }
    }

    @Override
    public SgdUsuarioPerfilModel findById(Long entityId) throws Exception {
        long start = MyUtils.iniciaMetodo();
        SgdUsuarioPerfil entity = new SgdUsuarioPerfil();
        try {
            entity = service.findById(entityId);
        } catch (Exception e) {
            MyUtils.errorMetodo(start, MyUtilsConstant.CODE_ERROR_READ, e);
        }
        SgdUsuarioPerfilModel model = MyUtils.fullSgdUsuarioPerfilModel(entity);
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
    public Page<SgdUsuarioPerfilModel> findAllBysgdPerfilAsPage(Long idPadre, int page, String orderByProperty, int itemsPerPage) throws MyServiceException {
        long start = MyUtils.iniciaMetodo();
        Page<SgdUsuarioPerfil> pageEntity = Page.empty();
        Page<SgdUsuarioPerfilModel> pageModel = Page.empty();
        try {
            pageEntity = service.findAllBySgdPerfilAsPage(idPadre, constructPageSpecificationDesc(page, orderByProperty, itemsPerPage));
            pageModel = MyUtils.fullSgdUsuarioPerfilModelPage(pageEntity);
        } catch (Exception e) {
            MyUtils.errorMetodo(start, MyUtilsConstant.CODE_ERROR_READ, e);
        }
        MyUtils.finMetodo(start);
        return pageModel;
    }

    @Override
    public List<SgdUsuarioPerfilModel> findAllBySgdUsuarioAsList(Long idPadre) throws MyServiceException {
        long start = MyUtils.iniciaMetodo();
        List<SgdUsuarioPerfil> listEntity = new ArrayList<>();
        List<SgdUsuarioPerfilModel> listmodel = new ArrayList<>();
        try {
            listEntity = service.findAllBySgdUsuarioAsList(idPadre);
        } catch (Exception e) {
            MyUtils.errorMetodo(start, MyUtilsConstant.CODE_ERROR_READ, e);
        }
        listEntity.stream().filter(entity -> entity != null).forEach(entity -> {
            SgdUsuarioPerfilModel model = MyUtils.fullSgdUsuarioPerfilModel(entity);
            listmodel.add(model);
        });
        MyUtils.finMetodo(start);
        return listmodel;
    }

}
