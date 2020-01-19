/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.presiframework.common.datalayer.entities.validator;

import com.presiframework.common.datalayer.entities.exceptions.RequiredFieldException;

/**
 *
 * @author Miguel Presinal <presinal378@gmail.com>
 * @since 1.0
 */
public interface EntityValidator<E> {

    /**
     * Validate the provided entity performing validation on every required fields.     
     * 
     * @param entity the entity that must be validated
     * @param updateMode true or false to indicate that the entity will be updated. 
     *                   When true is specified, the entity must have and id set.
     * @throws RequiredFieldException 
     */
    void validateEntity(E entity, boolean updateMode) throws RequiredFieldException;
}
