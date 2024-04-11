package com.jarkial.users.webservices.ctg;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.jarkial.users.configuration.utils.MyUtils;
import com.jarkial.users.configuration.utils.MyUtilsConstant;
import com.jarkial.users.model.dto.ctg.CtgSubTipoAgenciaModel;
import com.jarkial.users.model.entity.ctg.CtgSubTipoAgencia;
import com.jarkial.users.model.exceptions.MyServiceException;
import com.jarkial.users.services.AbstractBaseServiceImpl;
import com.jarkial.users.services.ctg.CtgSubTipoAgenciaService;

@Service
public class CtgSubTipoAgenciaServiceWebImpl extends AbstractBaseServiceImpl implements CtgSubTipoAgenciaServiceWeb {

    @Autowired
    CtgSubTipoAgenciaService service;

    @Override
    public boolean update(Long entityId, CtgSubTipoAgenciaModel model) throws Exception {
        long start = MyUtils.iniciaMetodo();
        model.setCtgSubTipoAgenciaId(entityId);
        CtgSubTipoAgencia entity = MyUtils.fullCtgSubTipoAgenciaEntity(model);
        try {
            entity = service.update(entity);
        } catch (Exception e) {
            MyUtils.errorMetodo(start, MyUtilsConstant.CODE_ERROR_WRITE, e);
        }
        if (entity.getCtgSubTipoAgenciaId() != null) {
            model = MyUtils.fullCtgSubTipoAgenciaModel(entity);
            MyUtils.finMetodo(start);
            return true;
        } else {
            MyUtils.finMetodo(start);
            return false;
        }
    }

    @Override
    public CtgSubTipoAgenciaModel findById(Long entityId) throws Exception {
        long start = MyUtils.iniciaMetodo();
        CtgSubTipoAgencia entity = new CtgSubTipoAgencia();
        try {
            entity = service.findById(entityId);
        } catch (Exception e) {
            MyUtils.errorMetodo(start, MyUtilsConstant.CODE_ERROR_READ, e);
        }
        CtgSubTipoAgenciaModel model = MyUtils.fullCtgSubTipoAgenciaModel(entity);
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
    public Page<CtgSubTipoAgenciaModel> findAllByCtgTipoAgenciaAsPage(Long idPadre, int page, String orderByProperty, int itemsPerPage) throws MyServiceException {
        long start = MyUtils.iniciaMetodo();
        Page<CtgSubTipoAgencia> pageEntity = Page.empty();
        Page<CtgSubTipoAgenciaModel> pageModel = Page.empty();
        try {
            pageEntity = service.findAllByCtgTipoAgenciaAsPage(idPadre, constructPageSpecificationDesc(page, orderByProperty, itemsPerPage));
            pageModel = MyUtils.fullCtgSubTipoAgenciaModelPage(pageEntity);
        } catch (Exception e) {
            MyUtils.errorMetodo(start, MyUtilsConstant.CODE_ERROR_READ, e);
        }
        MyUtils.finMetodo(start);
        return pageModel;
    }

}
