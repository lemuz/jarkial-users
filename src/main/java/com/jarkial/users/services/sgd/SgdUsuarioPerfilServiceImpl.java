package com.jarkial.users.services.sgd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jarkial.users.model.entity.sgd.SgdUsuario;
import com.jarkial.users.model.entity.sgd.SgdUsuarioPerfil;
import com.jarkial.users.repositories.sgd.SgdUsuarioPerfilRepository;
import com.jarkial.users.services.AbstractBaseServiceImpl;

import java.util.List;

@Service
@Transactional
public class SgdUsuarioPerfilServiceImpl extends AbstractBaseServiceImpl implements SgdUsuarioPerfilService{

    @Autowired
    SgdUsuarioPerfilRepository repository;

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<SgdUsuarioPerfil> findAllAsList() throws Exception {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Page<SgdUsuarioPerfil> findAllAsPage(Pageable pageable) throws Exception {
        return repository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public SgdUsuarioPerfil findById(Long id) throws Exception {
        return repository.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = {Exception.class}, propagation = Propagation.SUPPORTS)
    public SgdUsuarioPerfil update(SgdUsuarioPerfil entity) throws Exception {
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
    public List<SgdUsuarioPerfil> findAllBySgdUsuarioAsList(Long idPadre) throws Exception {
        return repository.findAllBySgdUsuario_SgdUsuarioId(idPadre);
    }

    @Override
    public Page<SgdUsuarioPerfil> findAllBySgdPerfilAsPage(Long idPadre, Pageable pageable) throws Exception {
        return repository.findAllBySgdPerfil_CtgCatalogoId(idPadre, pageable);
    }

}