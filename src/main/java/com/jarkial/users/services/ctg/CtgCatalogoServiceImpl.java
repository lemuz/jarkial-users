package com.jarkial.users.services.ctg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jarkial.users.model.entity.ctg.CtgCatalogo;
import com.jarkial.users.repositories.ctg.CtgCatalogoRepository;
import com.jarkial.users.services.AbstractBaseServiceImpl;

import java.util.List;

@Service
@Transactional
public class CtgCatalogoServiceImpl extends AbstractBaseServiceImpl implements CtgCatalogoService{

    @Autowired
    CtgCatalogoRepository repository;

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<CtgCatalogo> findAllAsList() throws Exception {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Page<CtgCatalogo> findAllAsPage(Pageable pageable) throws Exception {
        return repository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public CtgCatalogo findById(Long id) throws Exception {
        return repository.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = {Exception.class}, propagation = Propagation.SUPPORTS)
    public CtgCatalogo update(CtgCatalogo entity) throws Exception {
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
    public CtgCatalogo findByCtgCatalogoNombreAndCtgCatalogoPadreId(String nombre, Long ctgCatalogoPadreId) throws Exception{
        return repository.findByCtgCatalogoNombreAndCtgCatalogoPadre_CtgCatalogoId(nombre, ctgCatalogoPadreId).orElse(null);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<CtgCatalogo> findAllByCtgCatalogoPadreIdAsList(Long ctgCatalogoPadreId) throws Exception {
        return repository.findAllByCtgCatalogoPadre_CtgCatalogoId(ctgCatalogoPadreId);

    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Page<CtgCatalogo> findAllByCtgCatalogoPadreIdAsPage(Long ctgCatalogoPadreId, Pageable pageable) throws Exception {
        return repository.findAllByCtgCatalogoPadre_CtgCatalogoId(ctgCatalogoPadreId, pageable);
    }
}