/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.presiframework.common.datalayer.repository;

import java.util.List;

/**
 *
 * @author Miguel Presinal <presinal378@gmail.com>
 * @param <E>
 * @since 1.0
 */
public interface FilterOperations<E> {

    /**
     *
     * @param <F>
     * @param filter
     * @return all the entity that match the filter.
     */
    <F extends SearchCriteria<?>> List<E> filter(F filter);
}
