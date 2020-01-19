/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.presiframework.commons.service.jpa.spring;

import com.presiframework.common.datalayer.jpa.entity.JpaCommonEntity;
import com.presiframework.common.datalayer.jpa.spring.repository.CRUDSpringRepository;
import com.presiframework.common.datalayer.jpa.spring.repository.filter.ExampleSearchCriteria;
import com.presiframework.common.datalayer.repository.FilterOperations;
import com.presiframework.common.service.exception.InternalServiceException;
import com.presiframework.common.service.impl.AbstractCrudService;
import java.util.List;

/**
 *
 * @author Miguel Presinal <presinal378@gmail.com.do>
 * @param <E>
 * @param <F>
 * @since 1.0
 */
public abstract class AbstractJpaSpringCrudService<E extends JpaCommonEntity, F extends ExampleSearchCriteria<E>> extends AbstractCrudService<E, F> implements JpaSpringCrudService<E, F> {


    @Override
    public abstract <S extends E, ID> CRUDSpringRepository<S, ID> getRepository();

    @Override
    public <S extends E> FilterOperations<S> getDataFilter() {
        throw new UnsupportedOperationException("This method is not supported by this type of CrudService"); 
    }
    
    @Override
    public List<E> filter(F filter) throws InternalServiceException {        
        return getRepository().findAll(filter.buildCriteria());
    }
    
}
