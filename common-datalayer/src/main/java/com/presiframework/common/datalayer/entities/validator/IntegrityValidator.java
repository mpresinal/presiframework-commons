/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.presiframework.common.datalayer.entities.validator;

import com.presiframework.common.datalayer.entities.exceptions.ExistingDataException;
import com.presiframework.common.datalayer.entities.exceptions.IntegrityViolationException;

/**
 *
 * @author Miguel Presinal <presinal378@gmail.com>
 * @since 1.0
 */
public interface IntegrityValidator<E> {

    /**
     * Validate the specified entity does not break the data integrity.<br/>
     * The validations that must be performed are:<br/>
     * <ol>
     *  <li>Check that does not exist an other entity with the same data as the specified one.</li>
     *  <li>If the entity instance has any relationship with other entity instance, then that entity must exist</li>
     * </ol>
     * 
     * @param entity     
     * @throws com.presiframework.common.datalayer.entities.exceptions.ExistingDataException     
     * @throws com.presiframework.common.datalayer.entities.exceptions.IntegrityViolationException     
     */
    void validate(E entity) throws ExistingDataException, IntegrityViolationException;
}
