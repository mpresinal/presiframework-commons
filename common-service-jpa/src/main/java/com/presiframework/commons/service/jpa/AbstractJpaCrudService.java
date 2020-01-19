/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.presiframework.commons.service.jpa;

import com.presiframework.common.datalayer.jpa.entity.JpaCommonEntity;
import com.presiframework.common.datalayer.jpa.repository.AbstractJpaRepository;
import com.presiframework.common.datalayer.jpa.repository.filter.JpaSearchCriteria;
import com.presiframework.common.service.impl.AbstractCrudService;

/**
 *
 * @author Miguel Presinal <presinal378@gmail.com.do>
 * @param <E>
 * @param <F>
 * @since 1.0
 */
public abstract class AbstractJpaCrudService<E extends JpaCommonEntity, F extends JpaSearchCriteria<E>> extends AbstractCrudService<E, F> implements JpaCrudService<E, F> {

    @Override
    public abstract <S extends E, ID> AbstractJpaRepository<S, ID> getRepository();

}
