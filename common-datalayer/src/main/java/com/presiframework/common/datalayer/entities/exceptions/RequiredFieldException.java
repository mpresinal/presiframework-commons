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
public class RequiredFieldException extends AbstractFieldException {

    /**
     *
     */
    private static final long serialVersionUID = SerialVersion.CURRENT_VERSION;

    public static final int CODE = 1004;
    public static final String DESCRIPTION = "There are fields required that were not provided.";

    public RequiredFieldException() {
        this(null);
    }
    
    public RequiredFieldException(String field) {
        this(CODE, field, null);
    }

    public RequiredFieldException(String field, Throwable cause) {
        this(CODE, field, cause);        
    }
    
    public RequiredFieldException(int codigo, String field, Throwable cause) {
        super(field, null, CODE, DESCRIPTION + ( field!= null? " Field: " + field : ""), cause);
    }
}
