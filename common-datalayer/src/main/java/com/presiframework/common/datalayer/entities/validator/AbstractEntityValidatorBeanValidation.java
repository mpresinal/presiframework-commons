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

package com.presiframework.common.datalayer.entities.validator;

import com.presiframework.common.datalayer.entities.exceptions.BeanValidationException;
import com.presiframework.common.datalayer.entities.exceptions.RequiredFieldException;
import com.presiframework.common.datalayer.entities.CommonEntity;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import com.presiframework.common.datalayer.entities.validator.util.EntityValidatorUtil;

/**
 *
 * @author Miguel Presinal <presinal378@gmail.com>
 * @since 1.0
 */
public abstract class AbstractEntityValidatorBeanValidation<E extends CommonEntity> implements EntityValidator<E> {

    private Validator validator;

    public AbstractEntityValidatorBeanValidation() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    /**
     *
     * @return un lista de los entity rerenciados
     */
    public abstract List<?> getEntityReferences(E entity);

    @Override
    public void validateEntity(E entity, boolean updateMode) throws RequiredFieldException {
        EntityValidatorUtil.validateNotNull(entity, "entity");
        validate(entity, updateMode);
        validateEntityReferences(entity, updateMode);
    }

    private void validate(E entity, boolean updateMode) throws RequiredFieldException {
        if (updateMode) {
            EntityValidatorUtil.validateNotNull(entity.getId(), "id");
        }
        
        Set<ConstraintViolation<E>> violations = validator.validate(entity);

        if (violations != null && !violations.isEmpty()) {
            Set<ConstraintViolation<?>> tmp = new HashSet<>();
            tmp.addAll(violations);
            violations.clear();

            throw new BeanValidationException(tmp);
        }
    }

    protected void validateEntityReferences(E entity, boolean updateMode) throws RequiredFieldException {
        List<?> references = getEntityReferences(entity);

        if (references != null && !references.isEmpty()) {
            for (Object ref : references) {
                this.validate(entity, updateMode);
            }
        }
    }
}
