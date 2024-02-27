package com.jarkial.users.services.gst;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jarkial.users.model.entity.gst.GstDocumentoCargado;
import com.jarkial.users.repositories.gst.GstDocumentoCargadoRepository;
import com.jarkial.users.services.AbstractBaseServiceImpl;

import java.util.List;

@Service
@Transactional
public class GstDocumentoCargadoServiceImpl extends AbstractBaseServiceImpl implements GstDocumentoCargadoService{

    @Autowired
    GstDocumentoCargadoRepository gstDocumentoCargadoRepository;

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<GstDocumentoCargado> findAll() throws Exception {
        return gstDocumentoCargadoRepository.findAll();

    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public GstDocumentoCargado findById(Long id) throws Exception {
        return gstDocumentoCargadoRepository.findById(id).orElse(null);

    }

    @Override
    @Transactional(readOnly = false, rollbackFor = {Exception.class}, propagation = Propagation.SUPPORTS)
    public GstDocumentoCargado update(GstDocumentoCargado entity) throws Exception {
        return gstDocumentoCargadoRepository.save(entity);

    }

    @Override
    @Transactional(readOnly = false, rollbackFor = {Exception.class}, propagation = Propagation.SUPPORTS)
    public boolean deleteById(Long id) throws Exception {
        try{
            gstDocumentoCargadoRepository.deleteById(id);
        }catch(Exception exception){
            exception.printStackTrace();
            return false;
        }
        return true;
    }   
}