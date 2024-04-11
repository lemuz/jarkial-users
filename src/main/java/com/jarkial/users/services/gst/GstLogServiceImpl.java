package com.jarkial.users.services.gst;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jarkial.users.configuration.utils.MyUtils;
import com.jarkial.users.model.entity.gst.GstLog;
import com.jarkial.users.repositories.gst.GstLogRepository;
import com.jarkial.users.services.AbstractBaseServiceImpl;

import java.util.List;

@Service
@Transactional
public class GstLogServiceImpl extends AbstractBaseServiceImpl implements GstLogService{

    @Autowired
    GstLogRepository repository;

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<GstLog> findAllAsList() throws Exception {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Page<GstLog> findAllAsPage(Pageable pageable) throws Exception {
        return repository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public GstLog findById(Long id) throws Exception {
        return repository.findById(id).orElse(null);

    }

    @Override
    @Transactional(readOnly = false, rollbackFor = {Exception.class}, propagation = Propagation.SUPPORTS)
    public GstLog update(GstLog entity) throws Exception {
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
    @Transactional(readOnly = false, rollbackFor = {Exception.class}, propagation = Propagation.SUPPORTS)
    public GstLog crearGstLog(String operacion, String ipAddress) throws Exception{
        GstLog gstLog = new GstLog();
        gstLog.setGstConfLogIp(ipAddress);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        gstLog.setGstConfLogUsuario(auth.getName());
        gstLog.setGstConfLogTipoTransacion(operacion);
        gstLog.setGstConfLogClase(MyUtils.getClassName());
        GstLog gstLogR = update(gstLog);
        return gstLogR;
    }
    
}
