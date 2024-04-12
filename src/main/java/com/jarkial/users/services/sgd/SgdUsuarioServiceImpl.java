package com.jarkial.users.services.sgd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jarkial.users.model.entity.gst.GstLog;
import com.jarkial.users.model.entity.sgd.SgdUsuario;
import com.jarkial.users.repositories.sgd.SgdUsuarioRepository;
import com.jarkial.users.services.AbstractBaseServiceImpl;

import java.util.List;

@Service
@Transactional
public class SgdUsuarioServiceImpl extends AbstractBaseServiceImpl implements SgdUsuarioService{

    @Autowired
    SgdUsuarioRepository repository;

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<SgdUsuario> findAllAsList() throws Exception {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Page<SgdUsuario> findAllAsPage(Pageable pageable) throws Exception {
        return repository.findAll(pageable);
    } 

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public SgdUsuario findById(Long id) throws Exception {
        return repository.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = {Exception.class}, propagation = Propagation.SUPPORTS)
    public SgdUsuario update(SgdUsuario sgdUsuario) throws Exception {
        return repository.save(sgdUsuario);
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
    public SgdUsuario findBySgdUsuarioUsername(String username) throws Exception {
        return repository.findBySgdUsuarioUsername(username).orElse(null);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<SgdUsuario> findBySgdUsuarioPadreAsList(Long idPadre) throws Exception {
        return repository.findBySgdUsuarioPadre_SgdUsuarioId(idPadre);
    }

}