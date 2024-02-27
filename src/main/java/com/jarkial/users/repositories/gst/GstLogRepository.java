package com.jarkial.users.repositories.gst;

import org.springframework.stereotype.Repository;

import com.jarkial.users.model.entity.gst.GstLog;
import com.jarkial.users.repositories.AbstractBaseRepository;

@Repository
public interface GstLogRepository extends AbstractBaseRepository<GstLog, Long>{
    
}