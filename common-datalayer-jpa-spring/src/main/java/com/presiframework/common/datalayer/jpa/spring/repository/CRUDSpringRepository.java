/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.presiframework.common.datalayer.jpa.spring.repository;

import com.presiframework.common.datalayer.jpa.entity.JpaCommonEntity;
import com.presiframework.common.datalayer.repository.CRUDOperations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 *
 * @author Miguel Presinal <presinal378@gmail.com>
 * @param <E>
 * @param <ID>
 * @since 1.0
 */
@NoRepositoryBean
public interface CRUDSpringRepository<E extends JpaCommonEntity, ID> extends JpaRepository<E, ID>, CRUDOperations<E, ID> {

}
