/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.presiframework.commons.service.jpa;

import com.presiframework.common.datalayer.jpa.entity.JpaCommonEntity;
import com.presiframework.common.service.CrudService;
import com.presiframework.common.datalayer.jpa.repository.filter.JpaSearchCriteria;
/**
 *
 * @author Miguel Presinal <mpresinal@bancocaribe.com.do>
 * @param <E>
 * @param <F>
 * @since 1.0
 */
public interface JpaCrudService<E extends JpaCommonEntity, F extends JpaSearchCriteria<E>> extends CrudService<E, F> {

}
