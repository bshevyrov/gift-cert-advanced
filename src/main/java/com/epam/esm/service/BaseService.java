package com.epam.esm.service;

import com.epam.esm.persistence.entity.AbstractEntity;

import java.util.List;

public interface BaseService<E extends AbstractEntity> {
    E create(E entity);

    E findById(long id);

    List<E> findAll();

    void update(E entity);

    void delete(long id);

}
