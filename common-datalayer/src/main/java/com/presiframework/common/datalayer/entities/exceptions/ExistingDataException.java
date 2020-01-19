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
public class ExistingDataException extends BaseException {

    private static final long serialVersionUID = SerialVersion.CURRENT_VERSION;

    public static final int CODE = 1003;
    public static final String DESCRIPTION = "There is an existing data.";

    public ExistingDataException() {
        super(CODE, DESCRIPTION);
    }

    public ExistingDataException(String description) {
        super(CODE, description);
    }

    public ExistingDataException(Throwable cause) {
        super(CODE, DESCRIPTION, cause);
    }
}
