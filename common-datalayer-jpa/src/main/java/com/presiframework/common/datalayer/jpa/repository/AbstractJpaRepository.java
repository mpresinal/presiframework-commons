/*
 * Copyright 2020 Miguel Presinal.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.presiframework.common.datalayer.jpa.repository;

import com.presiframework.common.datalayer.jpa.entity.JpaCommonEntity;
import com.presiframework.common.datalayer.jpa.repository.filter.JpaSearchCriteria;
import com.presiframework.common.datalayer.repository.CRUDRepository;
import com.presiframework.common.datalayer.repository.SearchCriteria;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author Miguel
 * @param <E>
 * @param <ID>
 */
public abstract class AbstractJpaRepository<E extends JpaCommonEntity, ID> implements CRUDRepository<E, ID> {

    private String findAllQuery;
    private String countQuery;

    protected abstract EntityManager getEntityManager();

    protected abstract Class<E> getEntityClass();

    private void persistOrMerge(E entity, EntityManager manager) {
        if (entity.getId() != null) {
            manager.merge(entity);
        } else {
            manager.persist(entity);
        }
    }

    @Override
    public <T extends E> T save(T entity) {
        EntityManager manager = getEntityManager();
        EntityTransaction tx = manager.getTransaction();
        tx.begin();
        persistOrMerge(entity, manager);
        tx.commit();
        return entity;
    }

    @Override
    public <T extends E> List<T> saveAll(Iterable<T> entities) {
        List<T> list = null;
        if (entities != null) {
            EntityManager manager = getEntityManager();
            EntityTransaction tx = manager.getTransaction();
            tx.begin();
            list = new ArrayList<>();
            
            for (T entity : entities) {
                persistOrMerge(entity, manager);
                list.add(entity);
            }

            tx.commit();
        }

        return list;
    }

    @Override
    public Optional<E> findById(ID id) {
        return Optional.ofNullable(getEntityManager().find(getEntityClass(), id));
    }

    @Override
    public boolean existsById(ID id) {
        return findById(id).isPresent();
    }

    @Override
    public List<E> findAll() {
        if (findAllQuery == null) {
            findAllQuery = "SELECT e FROM " + getEntityClass().getSimpleName() + " e";
        }

        return getEntityManager().createQuery(findAllQuery).getResultList();
    }

    @Override
    public <F extends SearchCriteria<?>> List<E> filter(F filter) {
        if (!(filter instanceof JpaSearchCriteria)) {
            throw new IllegalArgumentException("filter must be a JpaSearchCriteria implementation");
        }

        JpaSearchCriteria jpaFilter = (JpaSearchCriteria) filter;
        Query query = getEntityManager().createQuery(jpaFilter.buildCriteria());
        return query.getResultList();
    }

    @Override
    public List<E> findAllById(Iterable<ID> ids) {
        List<ID> idsList = new ArrayList<>();
        ids.forEach(id -> {
            idsList.add(id);
        });
        final Class<E> entityClass = getEntityClass();

        return this.filter(new JpaSearchCriteria<E>() {
            @Override
            protected CriteriaBuilder getCriteriaBuilder() {
                return getEntityManager().getCriteriaBuilder();
            }

            @Override
            protected Class<E> getEntityClass() {
                return entityClass;
            }

            @Override
            protected List<Predicate> buildWhereConditions(Root<E> root, CriteriaBuilder builder) {
                return Arrays.asList(createInPredicate(root, builder, "id", idsList));
            }

        });
    }

    @Override
    public long count() {
        if (countQuery == null) {
            countQuery = "SELECT COUNT(e) FROM " + getEntityClass().getSimpleName() + " e";
        }
        Long result = (Long) getEntityManager().createQuery(countQuery).getSingleResult();
        return result != null ? result : 0;
    }

    @Override
    public void deleteById(ID id) {
        Optional<E> result = this.findById(id);
        if (result.isPresent()) {
            delete(result.get());
        }
    }

    @Override
    public void delete(E entity) {
        EntityManager entityManager = getEntityManager();
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        if (entityManager.contains(entity)) {
            entityManager.remove(entity);
        } else {
            E e = entityManager.getReference(getEntityClass(), entity.getId());
            entityManager.remove(entity);
        }
        tx.commit();
    }

    @Override
    public void deleteAll(Iterable<? extends E> entities) {
        EntityManager manager = getEntityManager();
        EntityTransaction tx = manager.getTransaction();
        final String COMMA = ",";
        StringBuilder query = new StringBuilder("DELETE FROM " + getEntityClass().getSimpleName() + " e WHERE e.id IN(");

        entities.forEach(e -> {
            query.append(e.getId()).append(COMMA);
            manager.detach(entities);
        });

        query.replace(query.length() - 1, query.length(), ")");
        tx.begin();
        manager.createQuery(query.toString()).executeUpdate();
        tx.commit();
    }

    @Override
    public void deleteAll() {
        String query = "DELETE FROM " + getEntityClass().getSimpleName() + " e";
        EntityManager manager = getEntityManager();
        EntityTransaction tx = manager.getTransaction();
        tx.begin();
        manager.createQuery(query).executeUpdate();
        tx.commit();
    }
}
