/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
