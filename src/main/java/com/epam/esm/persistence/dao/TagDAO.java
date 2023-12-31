package com.epam.esm.persistence.dao;

import com.epam.esm.persistence.entity.TagEntity;

import java.util.Optional;

public interface TagDAO extends BaseDAO<TagEntity, Long> {
    boolean existsByName(String tagEntityName);

    Optional<TagEntity> findByName(String tagEntityName);
}
