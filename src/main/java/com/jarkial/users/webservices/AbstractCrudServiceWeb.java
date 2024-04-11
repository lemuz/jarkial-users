package com.jarkial.users.webservices;

public interface AbstractCrudServiceWeb<C, I> {

    public abstract boolean update(I entityId, C model) throws Exception;

    public abstract C findById(I entityId) throws Exception;

    public abstract boolean deleteById(I entityId) throws Exception;

}