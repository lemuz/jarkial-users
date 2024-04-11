package com.jarkial.users.services.ctg;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jarkial.users.model.entity.ctg.CtgRutaAdjuntos;
import com.jarkial.users.repositories.ctg.CtgRutaAdjuntosRepository;
import com.jarkial.users.services.AbstractBaseServiceImpl;

@Service
@Transactional
public class CtgRutaAdjuntosServiceImpl extends AbstractBaseServiceImpl implements CtgRutaAdjuntosService {

    @Autowired
    CtgRutaAdjuntosRepository repository;

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<CtgRutaAdjuntos> findAllAsList() throws Exception {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Page<CtgRutaAdjuntos> findAllAsPage(Pageable pageable) throws Exception {
        return repository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public CtgRutaAdjuntos findById(Long id) throws Exception {
        return repository.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = { Exception.class }, propagation = Propagation.SUPPORTS)
    public CtgRutaAdjuntos update(CtgRutaAdjuntos entity) throws Exception {
        return repository.save(entity);
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = { Exception.class }, propagation = Propagation.SUPPORTS)
    public boolean deleteById(Long id) throws Exception {
        try {
            repository.deleteById(id);
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<CtgRutaAdjuntos> findAllBySgdUsuario(Long idPadre) throws Exception {
        return repository.findAllBySgdUsuario_SgdUsuarioId(idPadre);
    }

}
