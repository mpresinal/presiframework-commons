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

package com.presiframework.common.test;

import com.presiframework.common.datalayer.entities.CommonEntity;
import com.presiframework.common.datalayer.repository.SearchCriteria;
import com.presiframework.common.service.CrudService;
import org.slf4j.Logger;

/**
 *
 * @author Miguel Presinal <presinal378@gmail.com>
 * @param <E>
 * @param <CTR>
 * @since 1.0
 */
public class DefaultCrudServiceTest<E extends CommonEntity<Long>, CTR extends SearchCriteria > extends CrudServiceTest<E, CTR> {

    public DefaultCrudServiceTest() {
    }

    public DefaultCrudServiceTest(CrudServiceTestConfig<E, CTR> testConfig) {
        super(testConfig);
    }
    
    @Override
    protected Logger getLogger() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected CrudService<E, CTR> getService() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setUp() throws Exception { }

}
