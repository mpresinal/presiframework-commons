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
public class InactiveStateException extends BaseException {


    private static final long serialVersionUID = SerialVersion.CURRENT_VERSION;;

    public static final int CODE = 1001;
    public static final String DESCRIPTION = "El estado no es activo";

    public InactiveStateException() {
        super(CODE, DESCRIPTION);
    }

    public InactiveStateException(Throwable cause) {
        super(CODE, DESCRIPTION, cause);
    }
}
