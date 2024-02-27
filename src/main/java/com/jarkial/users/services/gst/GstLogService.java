package com.jarkial.users.services.gst;

import com.jarkial.users.model.entity.gst.GstLog;
import com.jarkial.users.services.AbstractCrudService;

public interface GstLogService extends AbstractCrudService<GstLog, Long>{

    GstLog crearGstLog(String string, String ipAddress) throws Exception;
    
}
