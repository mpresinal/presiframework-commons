/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.presiframework.common.test;

import com.presiframework.common.datalayer.entities.exceptions.RequiredFieldException;
import com.presiframework.common.datalayer.entities.exceptions.ExistingDataException;
import com.presiframework.common.datalayer.entities.exceptions.NoDataFoundException;
import com.presiframework.common.datalayer.entities.exceptions.IntegrityViolationException;

import com.presiframework.common.service.exception.InternalServiceException;
import com.presiframework.common.service.CrudService;
import com.presiframework.common.datalayer.entities.CommonEntity;
import com.presiframework.common.datalayer.repository.SearchCriteria;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;

/**
 *
 * @author Miguel Presinal <presinal378@gmail.com>
 * @since 1.0
 */
public abstract class CrudServiceTest<E extends CommonEntity, C extends SearchCriteria > {
    
    protected static final Long CREATED_BY_ID = 2L;
    
    private String cleanScript;
    private String insertScript;
    
    private CrudServiceTestConfig<E, C> testConfig;

    public CrudServiceTest() {
    }
    
    public CrudServiceTest(CrudServiceTestConfig<E, C> testConfig) {
        this.testConfig = testConfig;
    }

    public CrudServiceTestConfig getTestConfig() {
        return testConfig;
    }
    
    public void setTestConfig(CrudServiceTestConfig<E, C> testConfig) {
        this.testConfig = testConfig;
    }    
    
    protected abstract Logger getLogger();
    
    protected abstract CrudService<E, C> getService();   
    
    @Before
    public abstract void setUp() throws Exception;

    
    @Test
    public void testCreate_normalFlow() throws InternalServiceException, IntegrityViolationException, ExistingDataException, RequiredFieldException, NoDataFoundException {
        final String METHOD = "testCreate_normalFlow() :: ";
        Logger logger = getLogger();
        logger.info(METHOD+"Enter");
        
        try {
            E entity = testConfig.getDataInstanceToSave();

            logger.info(METHOD + "Creating entity....");
            getService().create(entity);
            Long idGenerated = (Long)entity.getId();
            logger.info(METHOD + "** idGenerated = " + idGenerated);
            logger.info(METHOD + "Creating entity....done!");

            E entityTmp = getService().findById(idGenerated);

            logger.info(METHOD + " entityTmp = " + entityTmp);
            assertNotNull("The test has failed. Wrong response. entityTmp es null", entityTmp);
            assertEquals("The test has failed. Wrong response.", entity, entityTmp);            
        
        } catch( Exception e) {
            logger.error(METHOD+"Exception : "+e.getMessage());
            e.printStackTrace();
            throw e;
            
        } finally {
            logger.info(METHOD + "Exit");
        }
    }
    
    
    @Test(expected = IntegrityViolationException.class)
    public void testCreate_integrityViolationFlow() throws InternalServiceException, IntegrityViolationException, ExistingDataException, RequiredFieldException {
        final String METHOD = "testCreate_integrityViolationFlow() :: ";
        Logger logger = getLogger();
        logger.info(METHOD+"Enter");
        
        if (testConfig != null && !testConfig.isEnableTestsFlowIntegrityViolation()) {
            logger.info(METHOD+"This test is not enable. Skipping Test");
            throw new IntegrityViolationException("", "");
        }        
        
        E entity = testConfig.getDataInstanceToSave();
        ((CommonEntity) entity).setCreatedBy(CREATED_BY_ID+10000);
        
        logger.info(METHOD+"Creating entity....");
        getService().create(entity);
        Long idGenerated = (Long) entity.getId();
        logger.info(METHOD+"** idGenerated = "+idGenerated);
        logger.info(METHOD+"Creating entity....done!");        

        fail("The test has failed. Wrong flow");
        logger.info(METHOD+"Exit");
    }
    
    
    @Test(expected = RequiredFieldException.class)
    public void testCreate_requiredFieldCreatedByFlow() throws InternalServiceException, IntegrityViolationException, ExistingDataException, RequiredFieldException {
        final String METHOD = "testCreate_requiredFieldCreatedByFlow() :: ";
        Logger logger = getLogger();
        logger.info(METHOD+"Enter");
        
        if (testConfig != null && !testConfig.isEnableTestsFlowRequiredFieldCreatedBy()) {
            logger.info(METHOD+"This test is not enable. Skipping Test");
            throw new RequiredFieldException();
        }
        
        E entity = testConfig.getDataInstanceToSave();
        ((CommonEntity) entity).setCreatedBy(null);
                
        logger.info(METHOD+"Creating entity....");
        getService().create(entity);
        Long idGenerated = (Long) entity.getId();
        logger.info(METHOD+"** idGenerated = "+idGenerated);
        logger.info(METHOD+"Creating entity....done!");        

        fail("The test has failed. Wrong flow");
        logger.info(METHOD+"Exit");
    }
    
    @Test(expected = ExistingDataException.class)
    public void testCreate_existingDataFlow() throws InternalServiceException, IntegrityViolationException, ExistingDataException, RequiredFieldException {
        final String METHOD = "testCreate_existingDataFlow() :: ";
        Logger logger = getLogger();
        logger.info(METHOD+"Enter");
        
        if (testConfig != null && (!testConfig.isEnableTestsCreateFlowExistingData() || !testConfig.isEnableTestExistingDataInstance())) { // setEnableTestsCrearFlujoExisteDatos
            logger.info(METHOD+"This test is not enable. Skipping Test");
            throw new ExistingDataException();
        }
        
        E entity = testConfig.getExistingDataInstaceToSave();        
        
        logger.info(METHOD+"Creating entity....");
        getService().create(entity);
        Long idGenerated = (Long) entity.getId();
        logger.info(METHOD+"** idGenerated = "+idGenerated);
        logger.info(METHOD+"Creating entity....done!");        

        fail("The test has failed. Wrong flow");
        logger.info(METHOD+"Exit");
    }
    
    @Test
    public void testUpdate_normalFlow() throws InternalServiceException, IntegrityViolationException, ExistingDataException, RequiredFieldException, NoDataFoundException {
        final String METHOD = "testUpdate_normalFlow() :: ";
        Logger logger = getLogger();
        logger.info(METHOD+"Enter");
        
        try {
            E entity = testConfig.getDataInstanceToUpdate();

            logger.info(METHOD + "Updating entity....");
            getService().update(entity);
            Long idGenerated = (Long) entity.getId();            
            logger.info(METHOD + "Updating entity....done!");

            E entityTmp = getService().findById(idGenerated);

            logger.info(METHOD + " entityTmp = " + entityTmp);
            assertNotNull("The test has failed. Wrong response. entityTmp es null", entityTmp);
            assertEquals("The test has failed. Wrong response.", entity, entityTmp);            
        
        } catch( Exception e) {
            logger.error(METHOD+"Exception : "+e.getMessage());
            e.printStackTrace();
            throw e;
            
        } finally {
            logger.info(METHOD + "Exit");
        }
    }
    
    
    @Test(expected = IntegrityViolationException.class)
    public void testUpdate_integrityViolationFlow() throws InternalServiceException, IntegrityViolationException, ExistingDataException, RequiredFieldException {
        final String METHOD = "testUpdate_integrityViolationFlow() :: ";
        Logger logger = getLogger();
        logger.info(METHOD+"Enter");
        
        if (testConfig != null && !testConfig.isEnableTestsFlowIntegrityViolation()) {
            logger.info(METHOD+"This test is not enable. Skipping Test");
            throw new IntegrityViolationException("", "");
        }
        
        E entity = testConfig.getDataInstanceToUpdate();
        ((CommonEntity) entity).setCreatedBy(CREATED_BY_ID+10000);
        
        logger.info(METHOD+"Updating entity....");
        getService().update(entity);                
        logger.info(METHOD+"Updating entity....done!");        

        fail("The test has failed. Wrong flow");
        logger.info(METHOD+"Exit");
    }
    
    
    @Test(expected = RequiredFieldException.class)
    public void testUpdate_requiredFieldCreatedByFlow() throws InternalServiceException, IntegrityViolationException, ExistingDataException, RequiredFieldException {
        final String METHOD = "testUpdate_requiredFieldCreatedByFlow() :: ";
        Logger logger = getLogger();
        logger.info(METHOD+"Enter");
        
        if (testConfig != null && !testConfig.isEnableTestsFlowRequiredFieldCreatedBy()) {
            logger.info(METHOD+"This test is not enable. Skipping Test");
            throw new RequiredFieldException();
        }
        
        E entity = testConfig.getDataInstanceToUpdate();
        ((CommonEntity) entity).setCreatedBy(null);
                
        logger.info(METHOD+"Updating entity....");
        getService().update(entity);        
        logger.info(METHOD+"Updating entity....done!");        

        fail("The test has failed. Wrong flow");
        logger.info(METHOD+"Exit");
    }
    
    @Test(expected = ExistingDataException.class)
    public void testUpdate_existingDataFlow() throws InternalServiceException, IntegrityViolationException, ExistingDataException, RequiredFieldException {
        final String METHOD = "testUpdate_existingDataFlow() :: ";
        Logger logger = getLogger();
        logger.info(METHOD+"Enter");
        
        if (testConfig != null && !testConfig.isEnableTestExistingDataInstance()) {
            logger.info(METHOD+"This test is not enable. Skipping Test");
            throw new ExistingDataException();            
        }
        
        E entity = testConfig.getExistingDataInstaceToSave();     
        
        logger.info(METHOD+"Updating entity....");
        getService().update(entity);        
        logger.info(METHOD+"Updating entity....done!");        

        fail("The test has failed. Wrong flow");
        logger.info(METHOD+"Exit");
    }
    
    @Test
    public void testFindAll_normalFlow() throws InternalServiceException {
        final String METHOD = "testFindAll_normalFlow() :: ";
        Logger logger = getLogger();
        logger.info(METHOD+"Enter");
        
        int expected = testConfig.getFindAllExpectedValue();
        
        List<E> list = getService().findAll();
        logger.info(METHOD+" list = "+list);
        assertNotNull("The test has failed. Wrong response. list is null", list);        
        assertFalse("The test has failed. Wrong response. list is empty", list.isEmpty());        
        assertEquals("The test has failed. Wrong response.", expected, list.size());
        logger.info(METHOD+"Exit");
    }
    
    @Test
    public void testFindAll_searchCriterriaStateINFlow() throws InternalServiceException{
        final String METHOD = "testFindAll_searchCriterriaStateINFlow() :: ";
        Logger logger = getLogger();
        logger.info(METHOD+"Enter");
        
        if (testConfig != null && !testConfig.isEnableTestFlowSearchCriteriaStateIN()) {
            logger.info(METHOD+"This test is not enable. Skipping Test");
            return;
        }
        
        int expected = testConfig.getFindAllFlowSearchCriteriaStateINExpectedValue();
        
        C criteria =  testConfig.getSearchCriteriaStateIN();
        
        List<E> list = getService().filter(criteria);
        logger.info(METHOD+" list = "+list);
        assertNotNull("The test has failed. Wrong response. list es null", list);        
        assertFalse("The test has failed. Wrong response. list is empty", list.isEmpty());        
        assertEquals("The test has failed. Wrong response.", expected, list.size());
        logger.info(METHOD+"Exit");
    }
    
    public void setCleanScript(String cleanScript) {
        this.cleanScript = cleanScript;
    }

    public void setInsertScript(String insertScript) {
        this.insertScript = insertScript;
    }   

    protected String getCleanScript() {
        return cleanScript;
    }

    protected String getInsertScript() {
        return insertScript;
    }    
    
}
