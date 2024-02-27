package com.jarkial.users.webservices.ctg;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jarkial.users.configuration.utils.MyUtils;
import com.jarkial.users.model.dto.ctg.CtgCatalogoModel;
import com.jarkial.users.model.entity.ctg.CtgCatalogo;
import com.jarkial.users.model.exceptions.MyServiceException;
import com.jarkial.users.services.AbstractBaseServiceImpl;
import com.jarkial.users.services.ctg.CtgCatalogoService;

@Service
public class CtgCatalogoServiceWebImpl extends AbstractBaseServiceImpl implements CtgCatalogoServiceWeb {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CtgCatalogoService ctgCatalogoService;

    @Override
    public List<CtgCatalogoModel> findAllByCtgCatalogoPadre(Long ctgCatalogoPadreId) throws MyServiceException {
        logger.info("[CtgCatalogoServiceWebImpl](findAllByCtgCatalogoPadre)");
        List<CtgCatalogo> findAll = new ArrayList<>();
        try {
            findAll = ctgCatalogoService.findAllByCtgCatalogoPadreId(ctgCatalogoPadreId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new MyServiceException("00100", MyUtils.getStackTrace(e));
        }
        List<CtgCatalogoModel> lista = new ArrayList<>();
        findAll.forEach(s -> {
            CtgCatalogoModel model = new CtgCatalogoModel();
            BeanUtils.copyProperties(s, model);
            model.setCtgCatalogoPadre(
                    s.getCtgCatalogoPadre() != null ? s.getCtgCatalogoPadre().getCtgCatalogoId() : null);
                    lista.add(model);
        });
        return lista;
    }

    @Override
    public CtgCatalogoModel findById(Long entityId) throws MyServiceException {
        logger.info("[CtgCatalogoServiceWebImpl](findById)");
        CtgCatalogo catalogo = new CtgCatalogo();
        try {
            catalogo = ctgCatalogoService.findById(entityId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new MyServiceException("00100", MyUtils.getStackTrace(e));
        }
        CtgCatalogoModel catalogoModel = new CtgCatalogoModel();
        BeanUtils.copyProperties(catalogo, catalogoModel);
        catalogoModel.setCtgCatalogoPadre(
                catalogo.getCtgCatalogoPadre() != null ? catalogo.getCtgCatalogoPadre().getCtgCatalogoId() : null);
        return catalogoModel;
    }

    @Override
    public boolean update(Long idCatalogo, CtgCatalogoModel catalogoModel) throws Exception {
        logger.info("[CtgCatalogoServiceWebImpl](update)");
        CtgCatalogo catalogoEntity = new CtgCatalogo();
        BeanUtils.copyProperties(catalogoModel, catalogoEntity);
        catalogoEntity.setCtgCatalogoPadre(
                catalogoModel.getCtgCatalogoPadre() != null ? new CtgCatalogo(catalogoModel.getCtgCatalogoPadre())
                        : null);
        catalogoEntity.setCtgCatalogoId(idCatalogo);
        try {
            catalogoEntity = ctgCatalogoService.update(catalogoEntity);
        } catch (Exception e) {
            e.printStackTrace();
            throw new MyServiceException("00200", MyUtils.getStackTrace(e));
        }
        catalogoModel = new CtgCatalogoModel();
        BeanUtils.copyProperties(catalogoEntity, catalogoModel);
        catalogoModel.setCtgCatalogoPadre(
                catalogoEntity.getCtgCatalogoPadre() != null ? catalogoEntity.getCtgCatalogoPadre().getCtgCatalogoId()
                        : null);
        return catalogoModel.getCtgCatalogoId()!=null;
    }

    @Override
    public boolean deleteById(Long id) throws Exception {
        logger.info("[CtgCatalogoServiceWebImpl](deleteById)");
        boolean respuesta = false;
        try {
            respuesta = ctgCatalogoService.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new MyServiceException("00300", MyUtils.getStackTrace(e));
        }
        return respuesta;
    }

}