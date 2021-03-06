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
