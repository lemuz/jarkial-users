package com.jarkial.users.webservices.ctg;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.jarkial.users.configuration.utils.MyUtils;
import com.jarkial.users.configuration.utils.MyUtilsConstant;
import com.jarkial.users.model.dto.ctg.CtgAgenciaModel;
import com.jarkial.users.model.dto.ctg.CtgCatalogoModel;
import com.jarkial.users.model.entity.ctg.CtgAgencia;
import com.jarkial.users.model.entity.ctg.CtgCatalogo;
import com.jarkial.users.model.exceptions.MyServiceException;
import com.jarkial.users.services.AbstractBaseServiceImpl;
import com.jarkial.users.services.ctg.CtgCatalogoService;

@Service
public class CtgCatalogoServiceWebImpl extends AbstractBaseServiceImpl implements CtgCatalogoServiceWeb {

    @Autowired
    CtgCatalogoService service;

    @Override
    public CtgCatalogoModel findByCtgCatNombreAndCtgCatalogoPadre(String ctgCatalogoNombre,
            Long ctgCatalogoPadreId) throws MyServiceException {
        long start = MyUtils.iniciaMetodo();
        CtgCatalogo entity = new CtgCatalogo();
        try {
                entity = service.findByCtgCatalogoNombreAndCtgCatalogoPadreId(ctgCatalogoNombre, ctgCatalogoPadreId);
        } catch (Exception e) {
            MyUtils.errorMetodo(start, MyUtilsConstant.CODE_ERROR_READ, e);
        }
        CtgCatalogoModel model = MyUtils.fullCtgCatalogoModel(entity);
        MyUtils.finMetodo(start);
        return model;
    }

    @Override
    public CtgCatalogoModel findById(Long entityId) throws MyServiceException {
        long start = MyUtils.iniciaMetodo();
        CtgCatalogo entity = new CtgCatalogo();
        try {
            entity = service.findById(entityId);
        } catch (Exception e) {
            MyUtils.errorMetodo(start, MyUtilsConstant.CODE_ERROR_READ, e);
        }
        CtgCatalogoModel model = MyUtils.fullCtgCatalogoModel(entity);
        MyUtils.finMetodo(start);
        return model;
    }

    @Override
    public boolean update(Long entityId, CtgCatalogoModel catalogoModel) throws Exception {
        //agregar validacion para que no se ingresen catalogos con nombre repetido con el mismo catalogo padre
        long start = MyUtils.iniciaMetodo();
        catalogoModel.setCtgCatalogoId(entityId);
        CtgCatalogo catalogoEntity = MyUtils.fullCtgCatalogoEntity(catalogoModel);
        try {
            catalogoEntity = service.update(catalogoEntity);
        } catch (Exception e) {
            MyUtils.errorMetodo(start, MyUtilsConstant.CODE_ERROR_WRITE, e);
        }
        catalogoModel = MyUtils.fullCtgCatalogoModel(catalogoEntity);
        MyUtils.finMetodo(start);
        return catalogoModel.getCtgCatalogoId() != null;
    }

    @Override
    public boolean deleteById(Long entityId) throws Exception {
        //agregar validacion para no borrar catalogo padre que tenga hijos dependientes
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
    public List<CtgCatalogoModel> findAllByCtgCatalogoPadreAsList(Long idPadre) throws MyServiceException {
        long start = MyUtils.iniciaMetodo();
        List<CtgCatalogo> findAll = new ArrayList<>();
        try {
            findAll = service.findAllByCtgCatalogoPadreIdAsList(idPadre);
        } catch (Exception e) {
            MyUtils.errorMetodo(start, MyUtilsConstant.CODE_ERROR_READ, e);
        }
        List<CtgCatalogoModel> lista = new ArrayList<>();
        findAll.stream().filter(catalogo -> catalogo != null).forEach(entity -> {
            CtgCatalogoModel model = MyUtils.fullCtgCatalogoModel(entity);
            lista.add(model);
        });
        MyUtils.finMetodo(start);
        return lista;
    }

    @Override
    public Page<CtgCatalogoModel> findAllByCtgCatalogoPadreAsPage(Long idPadre, int page, String orderByProperty,
            int itemsPerPage) throws MyServiceException {
        Page<CtgCatalogo> pageEntity = Page.empty();
        Page<CtgCatalogoModel> pageModel = Page.empty();
        try {
            pageEntity = service.findAllAsPage(constructPageSpecificationDesc(page, orderByProperty, itemsPerPage));
            pageModel = MyUtils.fullCtgCatalogoModelPage(pageEntity);
        } catch (Exception e) {
            throw new MyServiceException(e);
        }
        return pageModel;
    }

}