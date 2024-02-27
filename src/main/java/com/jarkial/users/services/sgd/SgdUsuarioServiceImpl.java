package com.jarkial.users.services.sgd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jarkial.users.model.entity.sgd.SgdUsuario;
import com.jarkial.users.repositories.sgd.SgdUsuarioRepository;
import com.jarkial.users.services.AbstractBaseServiceImpl;

import java.util.List;

@Service
@Transactional
public class SgdUsuarioServiceImpl extends AbstractBaseServiceImpl implements SgdUsuarioService{

    @Autowired
    SgdUsuarioRepository sgdUsuarioRepository;

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<SgdUsuario> findAll() throws Exception {
        return sgdUsuarioRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public SgdUsuario findById(Long id) throws Exception {
        return sgdUsuarioRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = {Exception.class}, propagation = Propagation.SUPPORTS)
    public SgdUsuario update(SgdUsuario sgdUsuario) throws Exception {
        return sgdUsuarioRepository.save(sgdUsuario);
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = {Exception.class}, propagation = Propagation.SUPPORTS)
    public boolean deleteById(Long id) throws Exception {
        try{
            sgdUsuarioRepository.deleteById(id);
        }catch(Exception exception){
            exception.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public SgdUsuario findBySgdUsuarioUsername(String username) throws Exception {
        return sgdUsuarioRepository.findBySgdUsuarioUsername(username).orElse(null);
    } 
}