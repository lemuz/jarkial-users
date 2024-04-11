package com.jarkial.users.webservices.sgd;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.jarkial.users.configuration.utils.MyUtils;
import com.jarkial.users.configuration.utils.MyUtilsConstant;
import com.jarkial.users.model.dto.sgd.SgdRolPerfilModel;
import com.jarkial.users.model.entity.sgd.SgdRolPerfil;
import com.jarkial.users.model.exceptions.MyServiceException;
import com.jarkial.users.services.AbstractBaseServiceImpl;
import com.jarkial.users.services.sgd.SgdRolPerfilService;

@Service
public class SgdRolPerfilServiceWebImpl extends AbstractBaseServiceImpl implements SgdRolPerfilServiceWeb {

    @Autowired
    SgdRolPerfilService service;

    @Override
    public boolean update(Long entityId, SgdRolPerfilModel model) throws Exception {
        long start = MyUtils.iniciaMetodo();
        model.setSgdRolPerfilId(entityId);
        SgdRolPerfil entity = MyUtils.fullSgdRolPerfilEntity(model);
        try {
            entity = service.update(entity);
        } catch (Exception e) {
            MyUtils.errorMetodo(start, MyUtilsConstant.CODE_ERROR_WRITE, e);
        }
        if (entity.getSgdRolPerfilId() != null) {
            model = MyUtils.fullSgdRolPerfilModel(entity);
            MyUtils.finMetodo(start);
            return true;
        } else {
            MyUtils.finMetodo(start);
            return false;
        }
    }

    @Override
    public SgdRolPerfilModel findById(Long entityId) throws Exception {
        long start = MyUtils.iniciaMetodo();
        SgdRolPerfil entity = new SgdRolPerfil();
        try {
            entity = service.findById(entityId);
        } catch (Exception e) {
            MyUtils.errorMetodo(start, MyUtilsConstant.CODE_ERROR_READ, e);
        }
        SgdRolPerfilModel model = MyUtils.fullSgdRolPerfilModel(entity);
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
    public Page<SgdRolPerfilModel> findAllBysgdPerfilAsPage(Long idPadre, int page, String orderByProperty, int itemsPerPage) throws MyServiceException {
        long start = MyUtils.iniciaMetodo();
        Page<SgdRolPerfil> pageEntity = Page.empty();
        Page<SgdRolPerfilModel> pageModel = Page.empty();
        try {
            pageEntity = service.findAllBySgdPerfilAsPage(idPadre, constructPageSpecificationDesc(page, orderByProperty, itemsPerPage));
            pageModel = MyUtils.fullSgdRolPerfilModelPage(pageEntity);
        } catch (Exception e) {
            MyUtils.errorMetodo(start, MyUtilsConstant.CODE_ERROR_READ, e);
        }
        MyUtils.finMetodo(start);
        return pageModel;
    }

}
