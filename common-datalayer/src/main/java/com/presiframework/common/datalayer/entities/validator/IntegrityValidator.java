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
