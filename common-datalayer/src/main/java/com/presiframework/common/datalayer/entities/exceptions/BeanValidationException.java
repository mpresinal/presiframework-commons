/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
