/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.presiframework.common.datalayer.entities.exceptions;

import com.presiframework.common.datalayer.SerialVersion;

/**
 *
 * @author Miguel Presinal <presinal378@gmail.com>
 * @since 1.0
 */
public class IntegrityViolationException extends AbstractFieldException {

    /**
     *
     */
    private static final long serialVersionUID = SerialVersion.CURRENT_VERSION;

    public static final int CODE = 1006;
    public static final String DESCRIPTION = "There is not any record asocieted with the provided data. field: %s, value: %s";

    public IntegrityViolationException(String fieldName, Object value) {        
        super(fieldName, value, CODE, String.format(DESCRIPTION, fieldName, value));
    }

    public IntegrityViolationException(String fieldName, Object value, String message) {
        super(fieldName, value, CODE, message);
    }

    public IntegrityViolationException(String field, Object value, Throwable cause) {
        super(field, value, CODE, String.format(DESCRIPTION, field, value), cause);        
    }
    
    public IntegrityViolationException(Throwable cause) {
        super(null, null, CODE, String.format(DESCRIPTION, "N/A", "N/A"), cause);        
    }
}
