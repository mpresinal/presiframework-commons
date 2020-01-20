/*
 * Copyright 2020 Miguel.
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
