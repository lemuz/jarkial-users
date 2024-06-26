package com.jarkial.users.webservices.ctg;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.jarkial.users.configuration.utils.MyUtils;
import com.jarkial.users.configuration.utils.MyUtilsConstant;
import com.jarkial.users.model.dto.ctg.CtgAgenciaModel;
import com.jarkial.users.model.entity.ctg.CtgAgencia;
import com.jarkial.users.model.exceptions.MyServiceException;
import com.jarkial.users.services.AbstractBaseServiceImpl;
import com.jarkial.users.services.ctg.CtgAgenciaService;

@Service
public class CtgAgenciaServiceWebImpl extends AbstractBaseServiceImpl implements CtgAgenciaServiceWeb {

    @Autowired
    CtgAgenciaService service;

    @Override
    public boolean update(Long entityId, CtgAgenciaModel model) throws Exception {
        long start = MyUtils.iniciaMetodo();
        model.setCtgAgenciaId(entityId);
        CtgAgencia entity = MyUtils.fullCtgAgenciaEntity(model);
        try {
            entity = service.update(entity);
        } catch (Exception e) {
            MyUtils.errorMetodo(start, MyUtilsConstant.CODE_ERROR_WRITE, e);
        }
        if (entity.getCtgAgenciaId() != null) {
            model = MyUtils.fullCtgAgenciaModel(entity);
            MyUtils.finMetodo(start);
            return true;
        } else {
            MyUtils.finMetodo(start);
            return false;
        }
    }

    @Override
    public CtgAgenciaModel findById(Long entityId) throws Exception {
        long start = MyUtils.iniciaMetodo();
        CtgAgencia entity = new CtgAgencia();
        try {
            entity = service.findById(entityId);
        } catch (Exception e) {
            MyUtils.errorMetodo(start, MyUtilsConstant.CODE_ERROR_READ, e);
        }
        CtgAgenciaModel model = MyUtils.fullCtgAgenciaModel(entity);
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
    public Page<CtgAgenciaModel> findAllByCtgSubTipoAgenciaAsPage(Long idPadre, int page, String orderByProperty, int itemsPerPage) throws MyServiceException {
        long start = MyUtils.iniciaMetodo();
        Page<CtgAgencia> pageEntity = Page.empty();
        Page<CtgAgenciaModel> pageModel = Page.empty();
        try {
            pageEntity = service.findAllByCtgSubTipoAgenciaAsPage(idPadre, constructPageSpecificationDesc(page, orderByProperty, itemsPerPage));
            pageModel = MyUtils.fullCtgAgenciaModelPage(pageEntity);
        } catch (Exception e) {
            MyUtils.errorMetodo(start, MyUtilsConstant.CODE_ERROR_READ, e);
        }
        MyUtils.finMetodo(start);
        return pageModel;
    }

}
