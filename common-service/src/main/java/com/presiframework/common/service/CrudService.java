/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.presiframework.common.service;

import com.presiframework.common.datalayer.entities.exceptions.ExistingDataException;
import com.presiframework.common.datalayer.entities.exceptions.IntegrityViolationException;
import com.presiframework.common.service.exception.InternalServiceException;
import com.presiframework.common.datalayer.entities.exceptions.NoDataFoundException;
import com.presiframework.common.datalayer.entities.exceptions.RequiredFieldException;
import java.util.List;
import com.presiframework.common.datalayer.repository.SearchCriteria;

/**
 *
 * @author Miguel Presinal <presinal378@gmail.com>
 * @param <E>
 * @param <F>
 * @since 1.0
 */
public interface CrudService<E, F extends SearchCriteria> {

    void create(E entity) throws InternalServiceException, IntegrityViolationException, ExistingDataException, RequiredFieldException;
    
    boolean update(E entity) throws InternalServiceException, IntegrityViolationException, ExistingDataException, RequiredFieldException;
    
    /**
     * This method mark as deleted the entity by id specified in the parameter. 
     * The entity is virtually deleted.
     * To delete the entity permanent see the methods permanentDelete.
     * @param id
     * @return
     * @throws InternalServiceException 
     */
    boolean delete(Long id) throws InternalServiceException;
    
    boolean delete(E entity) throws InternalServiceException;    
    
    boolean delete(List<Long> ids) throws InternalServiceException;
    
    /**
     * Elimina el entity de la base de datos. permanent 
     * 
     * @param id
     * @return
     * @throws InternalServiceException 
     */
    boolean permanentDelete(Long id) throws InternalServiceException;
    
    boolean permanentDelete(E entity) throws InternalServiceException;    
    
    boolean permanentDelete(List<Long> ids) throws InternalServiceException;
    
    List<E> findAll() throws InternalServiceException;
    
    List<E> filter(F filter) throws InternalServiceException;    
    
    E findById(Long id) throws InternalServiceException, NoDataFoundException;
}
