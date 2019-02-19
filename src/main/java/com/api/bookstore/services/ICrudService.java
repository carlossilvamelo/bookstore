package com.api.bookstore.services;

import java.io.Serializable;
import org.springframework.data.domain.Page;

public interface ICrudService<T, S extends Serializable> {

	public Page<T> getAll(String pageNumber, String pageSize);

	public T getById(S id);

	public T create(T entity);

	public T remove(T entity);

	public T update(T entity);
}
