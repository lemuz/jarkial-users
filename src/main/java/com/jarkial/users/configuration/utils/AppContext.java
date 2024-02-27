package com.jarkial.users.configuration.utils;

import com.jarkial.users.configuration.security.SgdUsuarioDetailsServiceImpl;

public class AppContext {

    public static SgdUsuarioDetailsServiceImpl getSgdUsuarioDetailsServiceImpl(){
        return ContextProvider.getApplicationContext().getBean(SgdUsuarioDetailsServiceImpl.class);
    }
    
}
