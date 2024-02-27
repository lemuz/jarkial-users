package com.jarkial.users.configuration.filter;

import org.hibernate.EmptyInterceptor;

public class HibernateStatisticsInterceptor  extends EmptyInterceptor{

    private ThreadLocal<Long> queryCount = new ThreadLocal<>();

    public void startCounter(){ queryCount.set(0L);}

    public Long getQueryCount(){ return queryCount.get();}

    public void clearCounter(){ queryCount.remove();}

    @Override
    public  String onPrepareStatement(String sql){
        Long count = queryCount.get();
        if(count != null)
            queryCount.set(count+1);
        return super.onPrepareStatement(sql);
    }
}
