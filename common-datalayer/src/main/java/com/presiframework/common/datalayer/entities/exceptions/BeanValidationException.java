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

package com.presiframework.common.datalayer.entities.exceptions;

import com.presiframework.common.datalayer.SerialVersion;
import java.util.Collections;
import java.util.Set;
//import javax.validation.ConstraintViolation;

/**
 *
 * @author Miguel Presinal <presinal378@gmail.com>
 * @since 1.0
 */
public class BeanValidationException extends RequiredFieldException {

    private static final long serialVersionUID = SerialVersion.CURRENT_VERSION;

    private final Set<?> violations;

    public BeanValidationException(Set<?> violations) {
        super("");
        this.violations = violations;
    }

    public Set<?> getViolations() {
        return Collections.unmodifiableSet(violations);
    }
}
