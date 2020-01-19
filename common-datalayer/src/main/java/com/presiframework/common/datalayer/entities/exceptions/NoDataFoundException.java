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
public class NoDataFoundException extends BaseException {

    private static final long serialVersionUID = SerialVersion.CURRENT_VERSION;

    public static final int CODE = 1002;
    public static final String DESCRIPTION = "No data found.";

    public NoDataFoundException() {
        super(CODE, DESCRIPTION);
    }

    public NoDataFoundException(Throwable cause) {
        super(CODE, DESCRIPTION, cause);
    }

}
