package com.jarkial.users.configuration.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;

public class MyUtilsConstant {

    public static final Logger logger = LoggerFactory.getLogger(MyUtilsConstant.class);

    public static final SimpleDateFormat dateFormatAsYYYYMMDDHHMMSS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static final SimpleDateFormat dateFormatAsYYYYMMDD = new SimpleDateFormat("yyyyMMdd");

    public static final String ROLE_ADMIN = "ROLE_ADMINISTRADOR_CATALOGOS";

    public static final Long COD_PADRE_ROLES = 3L;

    public static final String CODE_ERROR_READ = "00100";

    public static final String CODE_ERROR_WRITE = "00200";

    public static final String CODE_ERROR_DELETE = "00300";

    public static final String CODE_ERROR_UNKNOW = "10000";
    
}
