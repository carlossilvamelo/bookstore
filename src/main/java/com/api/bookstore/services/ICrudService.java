package com.api.bookstore.services;

import java.io.Serializable;
import java.util.List;

public interface ICrudService<T, S extends Serializable> {

    public List<T> getAll();

    public T getById(S id);

    public T create(T entity);

    public void remove(T entity);

    public T update(T entity);
}
