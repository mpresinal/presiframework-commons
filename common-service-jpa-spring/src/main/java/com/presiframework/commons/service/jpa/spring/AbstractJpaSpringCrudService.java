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
