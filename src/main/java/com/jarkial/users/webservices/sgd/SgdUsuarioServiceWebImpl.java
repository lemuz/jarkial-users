package com.jarkial.users.webservices.sgd;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jarkial.users.configuration.utils.MyUtils;
import com.jarkial.users.configuration.utils.MyUtilsConstant;
import com.jarkial.users.model.dto.sgd.SgdUsuarioModel;
import com.jarkial.users.model.entity.ctg.CtgAgencia;
import com.jarkial.users.model.entity.sgd.SgdUsuario;
import com.jarkial.users.services.AbstractBaseServiceImpl;
import com.jarkial.users.services.sgd.SgdUsuarioService;

@Service
public class SgdUsuarioServiceWebImpl extends AbstractBaseServiceImpl implements SgdUsuarioServiceWeb {

    @Autowired
    SgdUsuarioService sgdUsuarioService;

    @Override
    public void actualizarSgdUsuarioLogueado(String sgdUsuarioUsername, Integer logueado) {
        long start = MyUtils.iniciaMetodo();
        String ms = "OK";
        try {
            if (sgdUsuarioUsername != null && !sgdUsuarioUsername.isEmpty() && logueado != null) {
                SgdUsuario sgdUsuario = sgdUsuarioService.findBySgdUsuarioUsername(sgdUsuarioUsername);
                if (sgdUsuario != null) {
                    sgdUsuario.setSgdUsuarioLogueado(logueado);
                    sgdUsuarioService.update(sgdUsuario);
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
        SgdUsuario sgdUsuario = new SgdUsuario();
        try {
            sgdUsuario = sgdUsuarioService.findById(entityId);
        } catch (Exception e) {
            MyUtils.errorMetodo(start, MyUtilsConstant.CODE_ERROR_READ, e);
        }
        SgdUsuarioModel sgdUsuarioModel = new SgdUsuarioModel();
        BeanUtils.copyProperties(sgdUsuario, sgdUsuarioModel);
        sgdUsuarioModel.setSgdUsuarioPadre(
                sgdUsuario.getSgdUsuarioPadre() != null ? sgdUsuario.getSgdUsuarioPadre().getSgdUsuarioId() : null);
        sgdUsuarioModel.setCtgAgencia(
                sgdUsuario.getCtgAgencia() != null ? sgdUsuario.getCtgAgencia().getCtgAgenciaId() : null);
        MyUtils.finMetodo(start);
        return sgdUsuarioModel;
    }

    @Override
    public boolean update(Long id, SgdUsuarioModel sgdUsuarioModel) throws Exception {
        long start = MyUtils.iniciaMetodo();
        SgdUsuario sgdUsuario = new SgdUsuario();
        BeanUtils.copyProperties(sgdUsuarioModel, sgdUsuario);
        sgdUsuario.setSgdUsuarioPadre(
                sgdUsuarioModel.getSgdUsuarioPadre() != null ? new SgdUsuario(sgdUsuarioModel.getSgdUsuarioPadre())
                        : null);
        sgdUsuario.setCtgAgencia(
                sgdUsuarioModel.getCtgAgencia() != null ? new CtgAgencia(sgdUsuarioModel.getCtgAgencia())
                        : null);
        sgdUsuario.setSgdUsuarioId(id);
        try {
            sgdUsuario = sgdUsuarioService.update(sgdUsuario);
        } catch (Exception e) {
            MyUtils.errorMetodo(start, MyUtilsConstant.CODE_ERROR_WRITE, e);
        }
        sgdUsuarioModel = new SgdUsuarioModel();
        BeanUtils.copyProperties(sgdUsuario, sgdUsuarioModel);
        sgdUsuarioModel.setSgdUsuarioPadre(
                sgdUsuario.getSgdUsuarioPadre() != null ? sgdUsuario.getSgdUsuarioPadre().getSgdUsuarioId()
                        : null);
        sgdUsuarioModel.setCtgAgencia(
                sgdUsuario.getCtgAgencia() != null ? sgdUsuario.getCtgAgencia().getCtgAgenciaId()
                        : null);
        MyUtils.finMetodo(start);
        return sgdUsuarioModel.getSgdUsuarioId() != null;
    }

    @Override
    public boolean deleteById(Long id) throws Exception {
        long start = MyUtils.iniciaMetodo();
        boolean respuesta = false;
        try {
            respuesta = sgdUsuarioService.deleteById(id);
        } catch (Exception e) {
            MyUtils.errorMetodo(start, MyUtilsConstant.CODE_ERROR_DELETE, e);
        }
        MyUtils.finMetodo(start);
        return respuesta;
    }
}