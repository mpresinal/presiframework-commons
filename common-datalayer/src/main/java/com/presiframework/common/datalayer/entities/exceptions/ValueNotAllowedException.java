/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.presiframework.common.datalayer.entities.exceptions;

import com.presiframework.common.datalayer.SerialVersion;
import java.util.Arrays;

/**
 *
 * @author Miguel Presinal <presinal378@gmail.com>
 * @since 1.0
 */
public class ValueNotAllowedException extends BaseException {

    private static final long serialVersionUID = SerialVersion.CURRENT_VERSION;
    
    public static final int CODE = 1010;
    public static final String DESCRIPTION = "Value not allowed. value: %s";

    public ValueNotAllowedException(String value) {
        super(CODE, String.format(DESCRIPTION, value));
    }

    public ValueNotAllowedException(String valor, Object... values) {
        super(CODE, String.format(DESCRIPTION, valor)+". Allowed values: "+Arrays.toString(values));
    }
    
    public ValueNotAllowedException(String value, Throwable cause) {
        super(CODE, String.format(DESCRIPTION, value), cause);        
    }

}
