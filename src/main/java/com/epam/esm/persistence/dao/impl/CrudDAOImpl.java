package com.epam.esm.persistence.dao.impl;

import com.epam.esm.persistence.dao.CrudDAO;
import com.epam.esm.persistence.entity.Entity;
import com.epam.esm.utils.SortProp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class CrudDAOImpl<E extends Entity> implements CrudDAO<E,Long> {
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public E create(E e) {
        entityManager.merge(e);
        return e;
    }

    @Override
    @Deprecated
    public E update(E e) {

        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<E> findById(Class<E> eClass, Long aLong) {
        return Optional.ofNullable(entityManager.find(eClass, aLong));
    }

    @Override
    public Optional<E> deleteById(Class<E> eClass,Long aLong) {
        Optional<E> e = Optional.ofNullable(entityManager.find(eClass, aLong));
        e.ifPresent(value -> {entityManager.remove(value);entityManager.refresh(value);});
        return e;
    }


    @Override
    public Page<E> findAll(Class<E> eClass,Pageable pageable) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<E> criteriaQuery = criteriaBuilder.createQuery(eClass);
        Root<E> root = criteriaQuery.from(eClass);
        TypedQuery<E> query = entityManager.createQuery(criteriaQuery.select(root));
        List<SortProp> sortProp = new ArrayList<>();
        for (Sort.Order order : pageable.getSort()) {
            sortProp.add(new SortProp(
                    order.getProperty(), order.getDirection().name()));
        }
        if (!sortProp.isEmpty()) {
            List<Order> orderList = new ArrayList<>();

            for (SortProp prop : sortProp) {
                System.out.println(prop);
                if (prop.getDirection().equals("DESC")) {
                    orderList.add(criteriaBuilder.desc(root.get(prop.getProperty())));
                } else {
                    orderList.add(criteriaBuilder.asc(root.get(prop.getProperty())));
                }
            }
            query = entityManager.createQuery(criteriaQuery.select(root).orderBy(orderList));

        }
        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        List<E> list = query.getResultList();


        return new PageImpl<>(list, pageable, list.size());
    }

//    @Override
//    public boolean existsByName(Class<Entity> aClass,String entityName) {
//        return entityManager.unwrap(Session.class).createQuery(
//                        "SELECT 1 FROM "+aClass.getSimpleName()+" WHERE EXISTS (SELECT 1 FROM "+aClass.getSimpleName()+" e WHERE e.name=:name)")
//                .setParameter("name", entityName)
//                .setMaxResults(1)
//                .uniqueResult() != null;
//    }
//
//    @Override
//    public boolean existsById(Class<Entity> aClass,Long entityId) {
//        return entityManager.unwrap(Session.class).createQuery(
//                        "SELECT 1 FROM "+aClass.getSimpleName()+" WHERE EXISTS (SELECT 1 FROM "+aClass.getSimpleName()+" e WHERE e.id=:id)")
//                .setParameter("id", entityId)
//                .setMaxResults(1)
//                .uniqueResult() != null;
//    }
//
//    @Override
//    public Optional<Entity> findByName(Class<Entity> aClass,String entityName) {
//        return Optional.ofNullable(entityManager.createQuery("SELECT e FROM "+aClass.getSimpleName()+" e WHERE e.name=:name", aClass)
//                .setParameter("name", entityName)
//                .getSingleResult());
//    }
}
