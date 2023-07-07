package com.epam.esm.facade;

import com.epam.esm.veiw.dto.BaseDTO;

import java.util.List;

public interface BaseFacade<E extends BaseDTO> {
    long create(E entity);

    E findById(long id);

    List<E> findAll();

    void update(E entity);

    void delete(long id);
}
