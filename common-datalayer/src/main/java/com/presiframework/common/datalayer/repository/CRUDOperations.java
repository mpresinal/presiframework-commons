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
package com.presiframework.common.datalayer.repository;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author Miguel Presinal <presinal378@gmail.com>
 * @since 1.0
 */
public interface CRUDOperations<E, ID> {

    /**
     * Saves a given entity.Use the returned instance for further operations as
     * the save operation might have changed the entity instance completely.
     *
     * @param <T>
     * @param entity must not be {@literal null}.
     * @return the saved entity will never be {@literal null}.
     */
    <T extends E> T save(T entity);

    /**
     * Saves all given entities.
     *
     * @param <T>
     * @param entities must not be {@literal null}.
     * @return the saved entities will never be {@literal null}.
     * @throws NullPointerException in case the given entity is {@literal null}.
     */
    <T extends E> List<T> saveAll(Iterable<T> entities);

    /**
     * Retrieves an entity by its id.
     *
     * @param id must not be {@literal null}.
     * @return the entity with the given id or {@literal Optional#empty()} if
     * none found
     * @throws NullPointerException if {@code id} is {@literal null}.
     */
    Optional<E> findById(ID id);

    /**
     * Returns whether an entity with the given id exists.
     *
     * @param id must not be {@literal null}.
     * @return {@literal true} if an entity with the given id exists,
     * {@literal false} otherwise.
     * @throws NullPointerException if {@code id} is {@literal null}.
     */
    boolean existsById(ID id);

    /**
     * Returns all instances of the type.
     *
     * @return all entities
     */
    List<E> findAll();

    /**
     * Returns all instances of the type with the given IDs.
     *
     * @param ids
     * @return
     */
    List<E> findAllById(Iterable<ID> ids);

    /**
     * Returns the number of entities available.
     *
     * @return the number of entities
     */
    long count();

    /**
     * Deletes the entity with the given id.
     *
     * @param id must not be {@literal null}.
     * @throws NullPointerException in case the given {@code id} is
     * {@literal null}
     */
    void deleteById(ID id);

    /**
     * Deletes a given entity.
     *
     * @param entity
     * @throws NullPointerException in case the given entity is {@literal null}.
     */
    void delete(E entity);

    /**
     * Deletes the given entities.
     *
     * @param entities
     * @throws NullPointerException in case the given Iterable is
     * {@literal null}.
     */
    void deleteAll(Iterable<? extends E> entities);

    /**
     * Deletes all entities managed by the repository.
     */
    void deleteAll();
}
