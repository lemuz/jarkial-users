package com.jarkial.users.services.gst;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jarkial.users.model.entity.ctg.CtgAgencia;
import com.jarkial.users.model.entity.gst.GstDocumentoCargado;
import com.jarkial.users.repositories.gst.GstDocumentoCargadoRepository;
import com.jarkial.users.services.AbstractBaseServiceImpl;

import java.util.List;

@Service
@Transactional
public class GstDocumentoCargadoServiceImpl extends AbstractBaseServiceImpl implements GstDocumentoCargadoService{

    @Autowired
    GstDocumentoCargadoRepository repository;

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<GstDocumentoCargado> findAllAsList() throws Exception {
        return repository.findAll();

    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Page<GstDocumentoCargado> findAllAsPage(Pageable pageable) throws Exception {
        return repository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public GstDocumentoCargado findById(Long id) throws Exception {
        return repository.findById(id).orElse(null);

    }

    @Override
    @Transactional(readOnly = false, rollbackFor = {Exception.class}, propagation = Propagation.SUPPORTS)
    public GstDocumentoCargado update(GstDocumentoCargado entity) throws Exception {
        return repository.save(entity);

    }

    @Override
    @Transactional(readOnly = false, rollbackFor = {Exception.class}, propagation = Propagation.SUPPORTS)
    public boolean deleteById(Long id) throws Exception {
        try{
            repository.deleteById(id);
        }catch(Exception exception){
            exception.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Page<GstDocumentoCargado> findAllBySgdUsuarioAsPage(Long idPadre, Pageable pageable) throws Exception {
        return repository.findAllBySgdUsuario_SgdUsuarioId(idPadre, pageable);
    }
}