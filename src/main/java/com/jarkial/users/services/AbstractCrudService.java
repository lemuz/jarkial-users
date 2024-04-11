package com.jarkial.users.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AbstractCrudService<C, I> {

    public abstract List<C> findAllAsList() throws Exception;
    
    public abstract Page<C> findAllAsPage(Pageable pageable) throws Exception;

    public abstract C findById(I id) throws Exception;

    public abstract C update(C entity) throws Exception;

    public abstract boolean deleteById(I id) throws Exception;

}