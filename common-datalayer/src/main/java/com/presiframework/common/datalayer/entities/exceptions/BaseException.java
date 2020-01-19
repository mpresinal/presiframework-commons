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
public class BaseException extends Exception {

    private static final long serialVersionUID = SerialVersion.CURRENT_VERSION;

    private int code;

    public BaseException() {
    }

    public BaseException(int code, String message) {
        this(code, message, null);
    }

    public BaseException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return code + " - " + getMessage();
    }
}
