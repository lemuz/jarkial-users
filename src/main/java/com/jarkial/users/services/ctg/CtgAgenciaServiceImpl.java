package com.jarkial.users.services.ctg;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jarkial.users.model.entity.ctg.CtgAgencia;
import com.jarkial.users.model.entity.ctg.CtgCatalogo;
import com.jarkial.users.repositories.ctg.CtgAgenciaRepository;
import com.jarkial.users.services.AbstractBaseServiceImpl;

@Service
@Transactional
public class CtgAgenciaServiceImpl extends AbstractBaseServiceImpl implements CtgAgenciaService {

    @Autowired
    CtgAgenciaRepository repository;

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<CtgAgencia> findAllAsList() throws Exception {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Page<CtgAgencia> findAllAsPage(Pageable pageable) throws Exception {
        return repository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public CtgAgencia findById(Long id) throws Exception {
        return repository.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = { Exception.class }, propagation = Propagation.SUPPORTS)
    public CtgAgencia update(CtgAgencia entity) throws Exception {
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
    public Page<CtgAgencia> findAllByCtgSubTipoAgenciaAsPage(Long idPadre, Pageable pageable) throws Exception {
        return repository.findAllByCtgSubTipoAgencia_CtgSubTipoAgenciaId(idPadre, pageable);
    }

}
