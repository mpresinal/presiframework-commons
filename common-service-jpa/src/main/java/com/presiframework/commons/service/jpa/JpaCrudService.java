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
