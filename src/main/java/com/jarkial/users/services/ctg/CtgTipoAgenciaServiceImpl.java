package com.jarkial.users.services.ctg;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jarkial.users.model.entity.ctg.CtgTipoAgencia;
import com.jarkial.users.repositories.ctg.CtgTipoAgenciaRepository;
import com.jarkial.users.services.AbstractBaseServiceImpl;

@Service
@Transactional
public class CtgTipoAgenciaServiceImpl extends AbstractBaseServiceImpl implements CtgTipoAgenciaService{

    @Autowired
    CtgTipoAgenciaRepository repository;

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<CtgTipoAgencia> findAllAsList() throws Exception {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Page<CtgTipoAgencia> findAllAsPage(Pageable pageable) throws Exception {
        return repository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public CtgTipoAgencia findById(Long id) throws Exception {
        return repository.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = { Exception.class }, propagation = Propagation.SUPPORTS)
    public CtgTipoAgencia update(CtgTipoAgencia entity) throws Exception {
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
    
}
