package com.jarkial.users.configuration.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jarkial.users.model.dto.ctg.CtgAgenciaModel;
import com.jarkial.users.model.dto.ctg.CtgCatalogoModel;
import com.jarkial.users.model.dto.ctg.CtgRutaAdjuntosModel;
import com.jarkial.users.model.dto.ctg.CtgSubTipoAgenciaModel;
import com.jarkial.users.model.dto.ctg.CtgTipoAgenciaModel;
import com.jarkial.users.model.dto.gst.GstDocumentoCargadoModel;
import com.jarkial.users.model.dto.sgd.SgdRolPerfilModel;
import com.jarkial.users.model.dto.sgd.SgdUsuarioModel;
import com.jarkial.users.model.dto.sgd.SgdUsuarioPerfilModel;
import com.jarkial.users.model.entity.ctg.CtgAgencia;
import com.jarkial.users.model.entity.ctg.CtgCatalogo;
import com.jarkial.users.model.entity.ctg.CtgRutaAdjuntos;
import com.jarkial.users.model.entity.ctg.CtgSubTipoAgencia;
import com.jarkial.users.model.entity.ctg.CtgTipoAgencia;
import com.jarkial.users.model.entity.gst.GstDocumentoCargado;
import com.jarkial.users.model.entity.sgd.SgdRolPerfil;
import com.jarkial.users.model.entity.sgd.SgdUsuario;
import com.jarkial.users.model.entity.sgd.SgdUsuarioPerfil;
import com.jarkial.users.model.exceptions.MyServiceException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.MDC;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MyUtils extends MyUtilsConstant {

    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    public static String cleanForLog(String message) {
        String clean = message.replace('\n', '_').replace('\r', '_');
        clean = StringEscapeUtils.escapeHtml(message);
        if (!StringUtils.equals(message, clean))
            clean += " (Encoded)";
        return clean;
    }

    public static final String getTransactionId() {
        return StringUtils.defaultString(MDC.get("transacionId"), UUID.randomUUID().toString());
    }

    public static String getBase64Encode(String cadena) {
        return new String(Base64.encodeBase64(Base64.encodeBase64(cadena.getBytes())));
    }

    public static String getBase64Decode(String cadena) {
        return new String(Base64.decodeBase64(Base64.decodeBase64(cadena.getBytes())));
    }

    public static String getClassName() {
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        String className = stackTrace[2].getClassName();
        return className;
    }

    public static String getStackTrace(Throwable exception) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        exception.printStackTrace(pw);
        return sw.toString();
    }

    public static long iniciaMetodo() {
        long start = System.currentTimeMillis();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        String className = stackTrace[1].getClassName();
        String methodName = stackTrace[1].getMethodName();
        logger.info("Inicia: " + className + "." + methodName + "()");
        return start;
    }

    public static void finMetodo(long start) {
        long end = System.currentTimeMillis();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        String className = stackTrace[1].getClassName();
        String methodName = stackTrace[1].getMethodName();
        logger.warn("Fin: " + className + "." + methodName + "(), Time elapsed: " + (end - start));
    }

    public static void errorMetodo(long start, String code, Exception e) throws MyServiceException {
        long end = System.currentTimeMillis();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        String className = stackTrace[1].getClassName();
        String methodName = stackTrace[1].getMethodName();
        logger.error("Error: " + className + "." + methodName + "(), Time elapsed: " + (end - start), e);
        e.printStackTrace();
        throw new MyServiceException(getStackTrace(e), e, code);
    }

    public static Page<CtgAgenciaModel> fullCtgAgenciaModelPage(Page<CtgAgencia> pageEntity) throws MyServiceException {
        List<CtgAgenciaModel> listModel = new ArrayList<>();
        Page<CtgAgenciaModel> pageModel = Page.empty(pageEntity.getPageable());
        if (pageEntity.getContent().size() > 0) {
            pageEntity.forEach((entity) -> {
                CtgAgenciaModel ctgAgenciaModel = fullCtgAgenciaModel(entity);
                listModel.add(ctgAgenciaModel);
            });
            pageModel = new PageImpl<>(listModel, PageRequest.of(pageEntity.getNumber(), pageEntity.getSize()),
                    pageEntity.getTotalElements());
        }
        return pageModel;
    }

    public static CtgAgenciaModel fullCtgAgenciaModel(CtgAgencia entity) {
        CtgAgenciaModel model = new CtgAgenciaModel();
        BeanUtils.copyProperties(entity, model);
        model.setCtgSubTipoAgencia(
                entity.getCtgSubTipoAgencia() != null ? entity.getCtgSubTipoAgencia().getCtgSubTipoAgenciaId() : null);
        return model;
    }

    public static CtgAgencia fullCtgAgenciaEntity(CtgAgenciaModel model) {
        CtgAgencia entity = new CtgAgencia();
        BeanUtils.copyProperties(model, entity);
        entity.setCtgSubTipoAgencia(
                model.getCtgSubTipoAgencia() != null ? new CtgSubTipoAgencia(model.getCtgSubTipoAgencia())
                        : null);
        return entity;
    }

    public static Page<CtgCatalogoModel> fullCtgCatalogoModelPage(Page<CtgCatalogo> pageEntity) {
        List<CtgCatalogoModel> listModel = new ArrayList<>();
        Page<CtgCatalogoModel> pageModel = Page.empty(pageEntity.getPageable());
        if (pageEntity.getContent().size() > 0) {
            pageEntity.forEach((entity) -> {
                CtgCatalogoModel model = fullCtgCatalogoModel(entity);
                listModel.add(model);
            });
            pageModel = new PageImpl<>(listModel, PageRequest.of(pageEntity.getNumber(), pageEntity.getSize()),
                    pageEntity.getTotalElements());
        }
        return pageModel;
    }

    public static CtgCatalogoModel fullCtgCatalogoModel(CtgCatalogo entity) {
        CtgCatalogoModel model = new CtgCatalogoModel();
        BeanUtils.copyProperties(entity, model);
        model.setCtgCatalogoPadre(
                entity.getCtgCatalogoPadre() != null ? entity.getCtgCatalogoPadre().getCtgCatalogoId() : null);
        return model;
    }

    public static CtgCatalogo fullCtgCatalogoEntity(CtgCatalogoModel model) {
        CtgCatalogo catalogoEntity = new CtgCatalogo();
        BeanUtils.copyProperties(model, catalogoEntity);
        catalogoEntity.setCtgCatalogoPadre(
                model.getCtgCatalogoPadre() != null ? new CtgCatalogo(model.getCtgCatalogoPadre())
                        : null);
        return catalogoEntity;
    }

    public static Page<CtgSubTipoAgenciaModel> fullCtgSubTipoAgenciaModelPage(Page<CtgSubTipoAgencia> pageEntity) {
        List<CtgSubTipoAgenciaModel> listModel = new ArrayList<>();
        Page<CtgSubTipoAgenciaModel> pageModel = Page.empty(pageEntity.getPageable());
        if (pageEntity.getContent().size() > 0) {
            pageEntity.forEach((entity) -> {
                CtgSubTipoAgenciaModel model = fullCtgSubTipoAgenciaModel(entity);
                listModel.add(model);
            });
            pageModel = new PageImpl<>(listModel, PageRequest.of(pageEntity.getNumber(), pageEntity.getSize()),
                    pageEntity.getTotalElements());
        }
        return pageModel;
    }

    public static CtgSubTipoAgenciaModel fullCtgSubTipoAgenciaModel(CtgSubTipoAgencia entity) {
        CtgSubTipoAgenciaModel model = new CtgSubTipoAgenciaModel();
        BeanUtils.copyProperties(entity, model);
        model.setCtgTipoAgencia(
                entity.getCtgTipoAgencia() != null ? entity.getCtgTipoAgencia().getCtgTipoAgenciaId()
                        : null);
        return model;
    }

    public static CtgSubTipoAgencia fullCtgSubTipoAgenciaEntity(CtgSubTipoAgenciaModel model) {
        CtgSubTipoAgencia entity = new CtgSubTipoAgencia();
        BeanUtils.copyProperties(model, entity);
        entity.setCtgTipoAgencia(
                model.getCtgTipoAgencia() != null ? new CtgTipoAgencia(model.getCtgTipoAgencia())
                        : null);
        return entity;
    }

    public static Page<CtgRutaAdjuntosModel> fullCtgRutaAdjuntosModelPage(Page<CtgRutaAdjuntos> pageEntity) {
        List<CtgRutaAdjuntosModel> listModel = new ArrayList<>();
        Page<CtgRutaAdjuntosModel> pageModel = Page.empty(pageEntity.getPageable());
        if (pageEntity.getContent().size() > 0) {
            pageEntity.forEach((entity) -> {
                CtgRutaAdjuntosModel model = fullCtgRutaAdjuntosModel(entity);
                listModel.add(model);
            });
            pageModel = new PageImpl<>(listModel, PageRequest.of(pageEntity.getNumber(), pageEntity.getSize()),
                    pageEntity.getTotalElements());
        }
        return pageModel;
    }

    public static CtgRutaAdjuntosModel fullCtgRutaAdjuntosModel(CtgRutaAdjuntos entity) {
        CtgRutaAdjuntosModel model = new CtgRutaAdjuntosModel();
        BeanUtils.copyProperties(entity, model);
        model.setSgdUsuario(
                entity.getSgdUsuario() != null ? entity.getSgdUsuario().getSgdUsuarioId() : null);
        return model;
    }

    public static CtgRutaAdjuntos fullCtgRutaAdjuntosEntity(CtgRutaAdjuntosModel model) {
        CtgRutaAdjuntos entity = new CtgRutaAdjuntos();
        BeanUtils.copyProperties(model, entity);
        entity.setSgdUsuario(
                model.getSgdUsuario() != null ? new SgdUsuario(model.getSgdUsuario())
                        : null);
        return entity;
    }

    public static GstDocumentoCargado fullGstDocumentoCargadoEntity(GstDocumentoCargadoModel model) {
        GstDocumentoCargado entity = new GstDocumentoCargado();
        BeanUtils.copyProperties(model, entity);
        entity.setGstDocumentoCargadoTipoDocumento(
                model.getGstDocumentoCargadoId() != null ? new CtgCatalogo(model.getGstDocumentoCargadoId()) : null);
        entity.setSgdUsuario(
                model.getSgdUsuario() != null ? new SgdUsuario(model.getSgdUsuario()) : null);
        return entity;
    }

    public static GstDocumentoCargadoModel fullGstDocumentoCargadoModel(GstDocumentoCargado entity) {
        GstDocumentoCargadoModel model = new GstDocumentoCargadoModel();
        BeanUtils.copyProperties(entity, model);
        model.setGstDocumentoCargadoTipoDocumento(
                entity.getGstDocumentoCargadoTipoDocumento() != null
                        ? entity.getGstDocumentoCargadoTipoDocumento().getCtgCatalogoId()
                        : null);
        model.setSgdUsuario(
                entity.getSgdUsuario() != null ? entity.getSgdUsuario().getSgdUsuarioId() : null);
        return model;
    }

    public static Page<GstDocumentoCargadoModel> fullGstDocumentoCargadoModelPage(
            Page<GstDocumentoCargado> pageEntity) {
        List<GstDocumentoCargadoModel> listModel = new ArrayList<>();
        Page<GstDocumentoCargadoModel> pageModel = Page.empty(pageEntity.getPageable());
        if (pageEntity.getContent().size() > 0) {
            pageEntity.forEach((entity) -> {
                GstDocumentoCargadoModel model = fullGstDocumentoCargadoModel(entity);
                listModel.add(model);
            });
            pageModel = new PageImpl<>(listModel, PageRequest.of(pageEntity.getNumber(), pageEntity.getSize()),
                    pageEntity.getTotalElements());
        }
        return pageModel;
    }

    public static SgdRolPerfil fullSgdRolPerfilEntity(SgdRolPerfilModel model) {
        SgdRolPerfil entity = new SgdRolPerfil();
        BeanUtils.copyProperties(model, entity);
        entity.setSgdPerfil(
                model.getSgdPerfil() != null ? new CtgCatalogo(model.getSgdPerfil()) : null);
        entity.setSgdRol(
                model.getSgdRol() != null ? new CtgCatalogo(model.getSgdRol()) : null);
        return entity;
    }

    public static SgdRolPerfilModel fullSgdRolPerfilModel(SgdRolPerfil entity) {
        SgdRolPerfilModel model = new SgdRolPerfilModel();
        BeanUtils.copyProperties(entity, model);
        model.setSgdPerfil(
                entity.getSgdPerfil() != null ? entity.getSgdPerfil().getCtgCatalogoId() : null);
        model.setSgdRol(
                entity.getSgdRol() != null ? entity.getSgdRol().getCtgCatalogoId() : null);
        return model;
    }

    public static Page<SgdRolPerfilModel> fullSgdRolPerfilModelPage(Page<SgdRolPerfil> pageEntity) {
        List<SgdRolPerfilModel> listModel = new ArrayList<>();
        Page<SgdRolPerfilModel> pageModel = Page.empty(pageEntity.getPageable());
        if (pageEntity.getContent().size() > 0) {
            pageEntity.forEach((entity) -> {
                SgdRolPerfilModel model = fullSgdRolPerfilModel(entity);
                listModel.add(model);
            });
            pageModel = new PageImpl<>(listModel, PageRequest.of(pageEntity.getNumber(), pageEntity.getSize()),
                    pageEntity.getTotalElements());
        }
        return pageModel;
    }

    public static SgdUsuarioPerfil fullSgdUsuarioPerfilEntity(SgdUsuarioPerfilModel model) {
        SgdUsuarioPerfil entity = new SgdUsuarioPerfil();
        BeanUtils.copyProperties(model, entity);
        entity.setSgdPerfil(
                model.getSgdPerfil() != null ? new CtgCatalogo(model.getSgdPerfil()) : null);
        entity.setSgdUsuario(
                model.getSgdUsuario() != null ? new SgdUsuario(model.getSgdUsuario()) : null);
        return entity;
    }

    public static SgdUsuarioPerfilModel fullSgdUsuarioPerfilModel(SgdUsuarioPerfil entity) {
        SgdUsuarioPerfilModel model = new SgdUsuarioPerfilModel();
        BeanUtils.copyProperties(entity, model);
        model.setSgdPerfil(
                entity.getSgdPerfil() != null ? entity.getSgdPerfil().getCtgCatalogoId() : null);
        model.setSgdUsuario(
                entity.getSgdUsuario() != null ? entity.getSgdUsuario().getSgdUsuarioId() : null);
        return model;
    }

    public static Page<SgdUsuarioPerfilModel> fullSgdUsuarioPerfilModelPage(Page<SgdUsuarioPerfil> pageEntity) {
        List<SgdUsuarioPerfilModel> listModel = new ArrayList<>();
        Page<SgdUsuarioPerfilModel> pageModel = Page.empty(pageEntity.getPageable());
        if (pageEntity.getContent().size() > 0) {
            pageEntity.forEach((entity) -> {
                SgdUsuarioPerfilModel model = fullSgdUsuarioPerfilModel(entity);
                listModel.add(model);
            });
            pageModel = new PageImpl<>(listModel, PageRequest.of(pageEntity.getNumber(), pageEntity.getSize()),
                    pageEntity.getTotalElements());
        }
        return pageModel;
    }

    public static SgdUsuario fullSgdUsuarioEntity(SgdUsuarioModel model) {
        SgdUsuario entity = new SgdUsuario();
        BeanUtils.copyProperties(model, entity);
        entity.setSgdUsuarioPadre(
                model.getSgdUsuarioPadre() != null ? new SgdUsuario(model.getSgdUsuarioPadre())
                        : null);
        entity.setCtgAgencia(
                model.getCtgAgencia() != null ? new CtgAgencia(model.getCtgAgencia())
                        : null);
        return entity;
    }

    public static SgdUsuarioModel fullSgdUsuarioModel(SgdUsuario entity) {
        SgdUsuarioModel model = new SgdUsuarioModel();
        BeanUtils.copyProperties(entity, model);
        model.setSgdUsuarioPadre(
                entity.getSgdUsuarioPadre() != null ? entity.getSgdUsuarioPadre().getSgdUsuarioId()
                        : null);
        model.setCtgAgencia(
                entity.getCtgAgencia() != null ? entity.getCtgAgencia().getCtgAgenciaId()
                        : null);
        return model;
    }

    public static Page<SgdUsuarioModel> fullSgdUsuarioModelPage(Page<SgdUsuario> pageEntity) {
        List<SgdUsuarioModel> listModel = new ArrayList<>();
        Page<SgdUsuarioModel> pageModel = Page.empty(pageEntity.getPageable());
        if (pageEntity.getContent().size() > 0) {
            pageEntity.forEach((entity) -> {
                SgdUsuarioModel model = fullSgdUsuarioModel(entity);
                listModel.add(model);
            });
            pageModel = new PageImpl<>(listModel, PageRequest.of(pageEntity.getNumber(), pageEntity.getSize()),
                    pageEntity.getTotalElements());
        }
        return pageModel;
    }
}
