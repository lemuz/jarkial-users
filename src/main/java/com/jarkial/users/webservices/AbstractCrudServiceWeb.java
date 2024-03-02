package com.jarkial.users.webservices;

public interface AbstractCrudServiceWeb<C, I> {

    public abstract boolean update(I id, C model) throws Exception;

    public abstract C findById(I id) throws Exception;

    public abstract boolean deleteById(I id) throws Exception;

}