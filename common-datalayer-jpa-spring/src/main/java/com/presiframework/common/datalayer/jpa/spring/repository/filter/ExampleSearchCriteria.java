/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.presiframework.common.datalayer.jpa.spring.repository.filter;

import com.presiframework.common.datalayer.jpa.entity.JpaCommonEntity;
import com.presiframework.common.datalayer.repository.SearchCriteria;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

/**
 *
 * @author Miguel Presinal <presinal378@gmail.com>
 * @since 1.0
 */
public interface ExampleSearchCriteria<E extends JpaCommonEntity> extends SearchCriteria<Example<E>> {
    
    /**
     * An implementation of this method must return the same instance of the matcher
     * @return the matcher that will be used to filter the entities
     */
    ExampleMatcher getMatcher();
    
    E getEntity();

    @Override
    public default Example<E> buildCriteria() {
        return Example.of(getEntity(), getMatcher());
    }
}