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

/**
 * Esta clase sirve como configuracion para la pruebas unitarias realizadas por un CrudServiceTest.
 * 
 * @author Miguel Presinal <presinal378@gmail.com>
 * @since 1.0
 */
public class CrudServiceTestConfig<E extends CommonEntity, C extends SearchCriteria > {

    E dataInstanceToSave;
    E dataInstanceToUpdate;
    E existingDataInstaceToSave;    
    int findAllExpectedValue;
    int findAllFlowSearchCriteriaStateINExpectedValue;
    C searchCriteriaStateIN;
    
    boolean enableTestExistingDataInstance = true;
    boolean enableTestsFlowRequiredFieldCreatedBy = true;
    boolean enableTestsFlowIntegrityViolation = true;
    boolean enableTestsFlowExistingData = true;
    boolean enableTestsCreateFlowExistingData = true;
    boolean enableTestFlowSearchCriteriaStateIN = true;

    public E getDataInstanceToSave() {
        return dataInstanceToSave;
    }

    public void setDataInstanceToSave(E dataInstanceToSave) {
        this.dataInstanceToSave = dataInstanceToSave;
    }

    public E getDataInstanceToUpdate() {
        return dataInstanceToUpdate;
    }

    public void setDataInstanceToUpdate(E dataInstanceToUpdate) {
        this.dataInstanceToUpdate = dataInstanceToUpdate;
    }
    
    public E getExistingDataInstaceToSave() {
        return existingDataInstaceToSave;
    }

    public void setExistingDataInstaceToSave(E existingDataInstaceToSave) {
        this.existingDataInstaceToSave = existingDataInstaceToSave;
    }

    public int getFindAllExpectedValue() {
        return findAllExpectedValue;
    }

    public void setFindAllExpectedValue(int findAllExpectedValue) {
        this.findAllExpectedValue = findAllExpectedValue;
    }

    public int getFindAllFlowSearchCriteriaStateINExpectedValue() {
        return findAllFlowSearchCriteriaStateINExpectedValue;
    }

    public void setFindAllFlowSearchCriteriaStateINExpectedValue(int findAllFlowSearchCriteriaStateINExpectedValue) {
        this.findAllFlowSearchCriteriaStateINExpectedValue = findAllFlowSearchCriteriaStateINExpectedValue;
    }

    public C getSearchCriteriaStateIN() {
        return searchCriteriaStateIN;
    }

    public void setSearchCriteriaStateIN(C searchCriteriaStateIN) {
        this.searchCriteriaStateIN = searchCriteriaStateIN;
    }

    public boolean isEnableTestExistingDataInstance() {
        return enableTestExistingDataInstance;
    }

    public void setEnableTestExistingDataInstance(boolean enableTestExistingDataInstance) {
        this.enableTestExistingDataInstance = enableTestExistingDataInstance;
    }

    public boolean isEnableTestsFlowRequiredFieldCreatedBy() {
        return enableTestsFlowRequiredFieldCreatedBy;
    }

    public void setEnableTestsFlowRequiredFieldCreatedBy(boolean enableTestsFlowRequiredFieldCreatedBy) {
        this.enableTestsFlowRequiredFieldCreatedBy = enableTestsFlowRequiredFieldCreatedBy;
    }

    public boolean isEnableTestsFlowIntegrityViolation() {
        return enableTestsFlowIntegrityViolation;
    }

    public void setEnableTestsFlowIntegrityViolation(boolean enableTestsFlowIntegrityViolation) {
        this.enableTestsFlowIntegrityViolation = enableTestsFlowIntegrityViolation;
    }

    public boolean isEnableTestsFlowExistingData() {
        return enableTestsFlowExistingData;
    }

    public void setEnableTestsFlowExistingData(boolean enableTestsFlowExistingData) {
        this.enableTestsFlowExistingData = enableTestsFlowExistingData;
    }

    public boolean isEnableTestsCreateFlowExistingData() {
        return enableTestsCreateFlowExistingData;
    }

    public void setEnableTestsCreateFlowExistingData(boolean enableTestsCreateFlowExistingData) {
        this.enableTestsCreateFlowExistingData = enableTestsCreateFlowExistingData;
    }
    
    public boolean isEnableTestFlowSearchCriteriaStateIN() {
        return enableTestFlowSearchCriteriaStateIN;
    }

    public void setEnableTestFlowSearchCriteriaStateIN(boolean enableTestFlowSearchCriteriaStateIN) {
        this.enableTestFlowSearchCriteriaStateIN = enableTestFlowSearchCriteriaStateIN;
    }  
    
}
