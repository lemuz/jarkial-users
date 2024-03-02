package com.jarkial.users.services.ctg;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jarkial.users.model.entity.ctg.CtgAgencia;
import com.jarkial.users.repositories.ctg.CtgAgenciaRepository;
import com.jarkial.users.services.AbstractBaseServiceImpl;

@Service
@Transactional
public class CtgAgenciaServiceImpl extends AbstractBaseServiceImpl implements CtgAgenciaService {

    @Autowired
    CtgAgenciaRepository ctgAgenciaRepository;

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<CtgAgencia> findAll() throws Exception {
        return ctgAgenciaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public CtgAgencia findById(Long id) throws Exception {
        return ctgAgenciaRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = { Exception.class }, propagation = Propagation.SUPPORTS)
    public CtgAgencia update(CtgAgencia entity) throws Exception {
        return ctgAgenciaRepository.save(entity);
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = { Exception.class }, propagation = Propagation.SUPPORTS)
    public boolean deleteById(Long id) throws Exception {
        try {
            ctgAgenciaRepository.deleteById(id);
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
        return true;
    }

}
