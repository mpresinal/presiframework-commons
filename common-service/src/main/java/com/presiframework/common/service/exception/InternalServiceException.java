/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.presiframework.common.service.exception;

import com.presiframework.common.datalayer.entities.exceptions.BaseException;

/**
 *
 * @author Miguel Presinal <presinal378@gmail.com>
 * @since 1.0
 */
public class InternalServiceException extends BaseException {

    /**
     *
     */
    private static final long serialVersionUID = 3534629452883270465L;

    public static final int CODE = 1000;
    public static final String DESCRIPTION = "An unexpected error has occured.";

    public InternalServiceException() {
        super(CODE, DESCRIPTION);
    }

    public InternalServiceException(Throwable cause) {
        super(CODE, DESCRIPTION, cause);
    }

    public InternalServiceException(String message) {
        this(DESCRIPTION+" "+message, null);
    }

    public InternalServiceException(String message, Throwable cause) {
        super(CODE, DESCRIPTION+" "+message, cause);
    }
    
}
