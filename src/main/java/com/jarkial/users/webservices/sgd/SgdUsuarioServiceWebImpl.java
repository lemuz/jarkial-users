package com.jarkial.users.webservices.sgd;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.jarkial.users.configuration.utils.MyUtils;
import com.jarkial.users.configuration.utils.MyUtilsConstant;
import com.jarkial.users.model.dto.ctg.CtgTipoAgenciaModel;
import com.jarkial.users.model.dto.sgd.SgdUsuarioModel;
import com.jarkial.users.model.dto.sgd.SgdUsuarioPerfilModel;
import com.jarkial.users.model.entity.ctg.CtgTipoAgencia;
import com.jarkial.users.model.entity.sgd.SgdUsuario;
import com.jarkial.users.model.entity.sgd.SgdUsuarioPerfil;
import com.jarkial.users.model.exceptions.MyServiceException;
import com.jarkial.users.services.AbstractBaseServiceImpl;
import com.jarkial.users.services.sgd.SgdUsuarioService;

@Service
public class SgdUsuarioServiceWebImpl extends AbstractBaseServiceImpl implements SgdUsuarioServiceWeb {

    @Autowired
    SgdUsuarioService service;

    @Override
    public void actualizarSgdUsuarioLogueado(String sgdUsuarioUsername, Integer logueado) {
        long start = MyUtils.iniciaMetodo();
        String ms = "OK";
        try {
            if (sgdUsuarioUsername != null && !sgdUsuarioUsername.isEmpty() && logueado != null) {
                SgdUsuario sgdUsuario = service.findBySgdUsuarioUsername(sgdUsuarioUsername);
                if (sgdUsuario != null) {
                    sgdUsuario.setSgdUsuarioLogueado(logueado);
                    service.update(sgdUsuario);
                    logger.info("Usuario actualizado exitosamente!");
                } else {
                    ms = "Usuario no encontrado";
                    logger.warn(ms);
                }
            } else {
                ms = "Parametros incompletos";
                logger.warn(ms);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            MyUtils.finMetodo(start);
        }

    }

    @Override
    public SgdUsuarioModel findById(Long entityId) throws Exception {
        long start = MyUtils.iniciaMetodo();
        SgdUsuario entity = new SgdUsuario();
        try {
            entity = service.findById(entityId);
        } catch (Exception e) {
            MyUtils.errorMetodo(start, MyUtilsConstant.CODE_ERROR_READ, e);
        }
        SgdUsuarioModel model = MyUtils.fullSgdUsuarioModel(entity);
        MyUtils.finMetodo(start);
        return model;
    }

    @Override
    public boolean update(Long id, SgdUsuarioModel model) throws Exception {
        long start = MyUtils.iniciaMetodo();
        model.setSgdUsuarioId(id);
        SgdUsuario entity = MyUtils.fullSgdUsuarioEntity(model);
        try {
            entity = service.update(entity);
        } catch (Exception e) {
            MyUtils.errorMetodo(start, MyUtilsConstant.CODE_ERROR_WRITE, e);
        }
        model = MyUtils.fullSgdUsuarioModel(entity);
        MyUtils.finMetodo(start);
        return model.getSgdUsuarioId() != null;
    }

    @Override
    public boolean deleteById(Long id) throws Exception {
        long start = MyUtils.iniciaMetodo();
        boolean respuesta = false;
        try {
            respuesta = service.deleteById(id);
        } catch (Exception e) {
            MyUtils.errorMetodo(start, MyUtilsConstant.CODE_ERROR_DELETE, e);
        }
        MyUtils.finMetodo(start);
        return respuesta;
    }

    @Override
    public List<SgdUsuarioModel> findAllAsList() throws MyServiceException {
        long start = MyUtils.iniciaMetodo();
        List<SgdUsuario> findAll = new ArrayList<>();
        try {
            findAll = service.findAllAsList();
        } catch (Exception e) {
            MyUtils.errorMetodo(start, MyUtilsConstant.CODE_ERROR_READ, e);
        }
        List<SgdUsuarioModel> lista = new ArrayList<>();
        findAll.stream().filter(entity -> entity != null).forEach(entity -> {
            SgdUsuarioModel model = new SgdUsuarioModel();
            BeanUtils.copyProperties(entity, model);
            lista.add(model);
        });
        MyUtils.finMetodo(start);
        return lista;
    }

    @Override
    public Page<SgdUsuarioModel> findAllBySgdUsuarioAsPage(Long idPadre, int page, String orderByProperty, int itemsPerPage) throws MyServiceException {
        long start = MyUtils.iniciaMetodo();
        Page<SgdUsuario> pageEntity = Page.empty();
        Page<SgdUsuarioModel> pageModel = Page.empty();
        try {
            pageEntity = service.findBySgdUsuarioPadreAsPage(idPadre, constructPageSpecificationDesc(page, orderByProperty, itemsPerPage));
            pageModel = MyUtils.fullSgdUsuarioModelPage(pageEntity);
        } catch (Exception e) {
            MyUtils.errorMetodo(start, MyUtilsConstant.CODE_ERROR_READ, e);
        }
        MyUtils.finMetodo(start);
        return pageModel;
    }
}