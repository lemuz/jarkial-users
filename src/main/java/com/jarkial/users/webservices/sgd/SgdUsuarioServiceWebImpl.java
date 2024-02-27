package com.jarkial.users.webservices.sgd;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jarkial.users.configuration.utils.MyUtils;
import com.jarkial.users.model.dto.sgd.SgdUsuarioModel;
import com.jarkial.users.model.entity.ctg.CtgAgencia;
import com.jarkial.users.model.entity.sgd.SgdUsuario;
import com.jarkial.users.model.exceptions.MyServiceException;
import com.jarkial.users.services.AbstractBaseServiceImpl;
import com.jarkial.users.services.sgd.SgdUsuarioService;

@Service
public class SgdUsuarioServiceWebImpl extends AbstractBaseServiceImpl implements SgdUsuarioServiceWeb{

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    SgdUsuarioService sgdUsuarioService;

    @Override
    public void actualizarSgdUsuarioLogueado(String sgdUsuarioUsername, Integer logueado) {
        logger.info("Actualizar usuario logueado: " + sgdUsuarioUsername + " logueado: " + logueado);
        String ms = "OK";
        try{
            if(sgdUsuarioUsername != null && !sgdUsuarioUsername.isEmpty() && logueado != null){
                SgdUsuario sgdUsuario = sgdUsuarioService.findBySgdUsuarioUsername(sgdUsuarioUsername);
                if(sgdUsuario != null){
                    sgdUsuario.setSgdUsuarioLogueado(logueado);
                    sgdUsuarioService.update(sgdUsuario);
                    logger.info("Usuario actualizado exitosamente!");
                }else{
                    ms = "Usuario no encontrado";
                    logger.warn(ms);
                }
            }else{
                ms = "Parametros incompletos";
                logger.warn(ms);
            }
        }catch(Exception exception){
            exception.printStackTrace();
        }
        
    }

    @Override
    public SgdUsuarioModel findById(Long entityId) throws Exception {
        logger.info("[SgdUsuarioServiceWebImpl](findById)");
        SgdUsuario sgdUsuario = new SgdUsuario();
        try {
            sgdUsuario = sgdUsuarioService.findById(entityId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new MyServiceException("00100", MyUtils.getStackTrace(e));
        }
        SgdUsuarioModel sgdUsuarioModel = new SgdUsuarioModel();
        BeanUtils.copyProperties(sgdUsuario, sgdUsuarioModel);
        sgdUsuarioModel.setSgdUsuarioPadre(
                sgdUsuario.getSgdUsuarioPadre() != null ? sgdUsuario.getSgdUsuarioPadre().getSgdUsuarioId() : null);
        sgdUsuarioModel.setCtgAgencia(
                sgdUsuario.getCtgAgencia() != null ? sgdUsuario.getCtgAgencia().getCtgAgenciaId() : null);
        return sgdUsuarioModel;
    }


    @Override
    public boolean update(Long id, SgdUsuarioModel sgdUsuarioModel) throws Exception {
        logger.info("[SgdUsuarioServiceWebImpl](update)");
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
            e.printStackTrace();
            throw new MyServiceException("00200", MyUtils.getStackTrace(e));
        }
        sgdUsuarioModel = new SgdUsuarioModel();
        BeanUtils.copyProperties(sgdUsuario, sgdUsuarioModel);
        sgdUsuarioModel.setSgdUsuarioPadre(
                sgdUsuario.getSgdUsuarioPadre() != null ? sgdUsuario.getSgdUsuarioPadre().getSgdUsuarioId()
                        : null);
        sgdUsuarioModel.setCtgAgencia(
                sgdUsuario.getCtgAgencia() != null ? sgdUsuario.getCtgAgencia().getCtgAgenciaId()
                        : null);
        return sgdUsuarioModel.getSgdUsuarioId()!=null;
    }  

    @Override
    public boolean deleteById(Long id) throws Exception {
        logger.info("[SgdUsuarioServiceWebImpl](deleteById)");
        boolean respuesta = false;
        try {
            respuesta = sgdUsuarioService.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new MyServiceException("00300", MyUtils.getStackTrace(e));
        }
        return respuesta;
    }   
}