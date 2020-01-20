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

package com.presiframework.common.service.impl;

import com.presiframework.common.service.CrudService;
import com.presiframework.common.datalayer.entities.exceptions.ExistingDataException;
import com.presiframework.common.datalayer.entities.exceptions.IntegrityViolationException;
import com.presiframework.common.service.exception.InternalServiceException;
import com.presiframework.common.datalayer.entities.CommonEntity;
import com.presiframework.common.datalayer.entities.exceptions.NoDataFoundException;
import com.presiframework.common.datalayer.entities.exceptions.RequiredFieldException;
import com.presiframework.common.datalayer.entities.validator.EntityValidator;
import com.presiframework.common.datalayer.entities.validator.IntegrityValidator;
import com.presiframework.common.datalayer.repository.CRUDOperations;
import com.presiframework.common.datalayer.repository.FilterOperations;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import com.presiframework.common.datalayer.repository.SearchCriteria;
import java.util.function.Supplier;

/**
 *
 * @author Miguel Presinal <presinal378@gmail.com.do>
 * @param <E>
 * @param <F>
 * @since 1.0
 */
public abstract class AbstractCrudService<E extends CommonEntity<? extends Number>, F extends SearchCriteria> implements CrudService<E, F> {

    public abstract Logger getLogger();

    public abstract IntegrityValidator<E> getIntegrityValidator();

    public abstract EntityValidator<E> getEntityValidator();

    public abstract <S extends E, ID> CRUDOperations<S, ID> getRepository();
    
    public abstract <S extends E> FilterOperations<S> getDataFilter();

    @Override
    public void create(E entity) throws InternalServiceException, IntegrityViolationException, ExistingDataException, RequiredFieldException {
        final String METHOD = "create():: ";
        final Logger logger = getLogger();
        logger.info(METHOD + "Enter");
        createOrUpdate(entity, false);
        logger.info(METHOD + "Exist");
    }

    @Override
    public boolean update(E entity) throws InternalServiceException, IntegrityViolationException, ExistingDataException, RequiredFieldException {
        final String METHOD = "update():: ";
        final Logger logger = getLogger();
        logger.info(METHOD + "Enter");
        createOrUpdate(entity, true);
        logger.info(METHOD + "Exist");
        return true;
    }

    protected E createOrUpdate(E entity, boolean updateMode) throws InternalServiceException, IntegrityViolationException, ExistingDataException, RequiredFieldException {
        final String METHOD = "createOrUpdate():: ";
        final Logger logger = getLogger();
        logger.info(METHOD + "Enter");
        logger.info(METHOD + "entity = " + entity);
        logger.info(METHOD + "updateMode = " + updateMode);

        CRUDOperations<E, Long> repository = getRepository();

        try {

            EntityValidator<E> validator = getEntityValidator();

            if (validator != null) {
                logger.debug(METHOD + "Validating entity....");
                validator.validateEntity(entity, updateMode);
                logger.debug(METHOD + "Validating entity....DONE");
            }

            validateRepositoryNotNull(repository);

            IntegrityValidator<E> validadorIntegridad = getIntegrityValidator();
            if (validadorIntegridad != null) {
                logger.debug(METHOD + "Validating entity data integrity...");
                validadorIntegridad.validate(entity);
                logger.debug(METHOD + "Validating entity data integrity...DONE");
            }

        } catch (Exception ex) {

            if (ex instanceof IntegrityViolationException) {
                throw (IntegrityViolationException) ex;

            } else if (ex instanceof ExistingDataException) {
                throw (ExistingDataException) ex;
            }

            throw new InternalServiceException(ex);

        } finally {
            logger.info(METHOD + "Exist");
        }

        try {
            logger.debug(METHOD + "Persisting entitiy...");
            if (updateMode) {
                entity.setLastUpdate(new Date());
            }
            repository.save(entity);
            logger.debug(METHOD + "Persisting entitiy...DONE");

        } catch (Exception e) {
            logger.error(METHOD + " Exception: " + e.getMessage(), e);

            throw new InternalServiceException(e);

        } finally {
            logger.info(METHOD + "Exist");
        }

        return entity;
    }

    protected boolean deleteChildrenEntity(E entity) throws Exception {
        return true;
    }

    @Override
    public boolean delete(Long id) throws InternalServiceException {
        List<Long> ids = new ArrayList<>();
        ids.add(id);
        return delete(ids);
    }

    @Override
    public boolean delete(E entity) throws InternalServiceException {
        CRUDOperations<E, Long> repository = getRepository();

        boolean eliminado = false;
        try {

            validateRepositoryNotNull(repository);

            if (entity != null) {
                entity.setDeleted(true);
                entity.setLastUpdate(new Date());

                if (deleteChildrenEntity(entity)) {
                    repository.save(entity);
                    eliminado = true;
                }
            }

            return eliminado;

        } catch (Exception e) {
            throw new InternalServiceException(e);
        }
    }

    @Override
    public boolean delete(List<Long> ids) throws InternalServiceException {
        CRUDOperations<E, Long> repository = getRepository();
        boolean deleted = false;

        try {

            validateRepositoryNotNull(repository);

            Iterable<E> entities = repository.findAllById(ids);

            if (entities != null) {

                for (E entity : entities) {
                    entity.setDeleted(true);
                    entity.setLastUpdate(new Date());

                    if (deleteChildrenEntity(entity)) {
                        repository.save(entity);
                        deleted = true;
                    }
                }

            }

            return deleted;

        } catch (Exception e) {
            throw new InternalServiceException(e);
        }
    }

    @Override
    public boolean permanentDelete(Long id) throws InternalServiceException {
        CRUDOperations<E, Long> repository = getRepository();

        try {
            validateRepositoryNotNull(repository);
            Optional<E> resp = repository.findById(id);

            if (resp.isPresent()) {
                repository.delete(resp.get());
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            throw new InternalServiceException(e);
        }
    }

    @Override
    public boolean permanentDelete(E entity) throws InternalServiceException {
        CRUDOperations<E, Long> repository = getRepository();

        try {
            validateRepositoryNotNull(repository);
            repository.delete(entity);
            return true;

        } catch (Exception e) {
            throw new InternalServiceException(e);
        }
    }

    @Override
    public boolean permanentDelete(List<Long> ids) throws InternalServiceException {
        CRUDOperations<E, Long> repository = getRepository();

        try {
            validateRepositoryNotNull(repository);

            if (ids != null && !ids.isEmpty()) {
                Iterable<E> entities = repository.findAllById(ids);
                if (entities != null) {
                    repository.deleteAll(entities);
                    return true;
                }

            }

            return false;

        } catch (Exception e) {
            throw new InternalServiceException(e);
        }
    }

    @Override
    public List<E> findAll() throws InternalServiceException {
        return doSearchAction(() -> {
            return getRepository().findAll();
        });
    }

    @Override
    public List<E> filter(F filter) throws InternalServiceException {
        return doSearchAction(() -> {
            return getDataFilter().filter(filter);
        });
    }
    
    private List<E> doSearchAction(Supplier<Iterable<E>> supplier) throws InternalServiceException{
        CRUDOperations<E, Long> repository = getRepository();
        final List<E> list = new ArrayList<>();
        try {
            validateRepositoryNotNull(repository);            
            Iterable<E> iterable = supplier.get();
            if (iterable != null) {
                iterable.forEach(entity -> {
                    list.add(entity);
                });
            }

        } catch (Exception e) {
            throw new InternalServiceException(e);
        }

        return list;
    }
    

    @Override
    public E findById(Long id) throws InternalServiceException, NoDataFoundException {
        final String METHOD = "findById():: ";
        Logger logger = getLogger();
        logger.info(METHOD + "Enter");
        logger.info(METHOD + "id = " + id);
        
        CRUDOperations<E, Long> repository = getRepository();
        E entity = null;
        try {
            validateRepositoryNotNull(repository);

            logger.debug(METHOD + "Finding entity by id...");
            Optional<E> resp = repository.findById(id);

            if (resp.isPresent()) {
                entity = resp.get();
            }

            logger.debug(METHOD + "entity = " + entity);
            logger.debug(METHOD + "Finding entity by id...DONE");

            if (entity == null) {
                throw new NoDataFoundException();
            }

            logger.info(METHOD + "Returning entity = " + entity);
            return entity;

        } catch (Exception e) {

            if (e instanceof NoDataFoundException) {
                throw e;
            }

            throw new InternalServiceException(e);
        } finally {
            logger.info(METHOD + "Exit");
        }
    }

    protected void validateRepositoryNotNull(CRUDOperations repository) {

        if (repository == null) {
            throw new NullPointerException("repository can not be null");
        }
    }
}
