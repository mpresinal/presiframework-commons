/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.presiframework.common.rest.response;

/**
 *
 * @author Miguel Presinal <presinal378@gmail.com>
 * @since 1.0
 */
public class ValidationErrorMessage extends ErrorMessage {

    /**
     *
     */
    private static final long serialVersionUID = 1556397176209556486L;

    private String fieldName;

    public ValidationErrorMessage(String message) {
        this(null, message);
    }

    public ValidationErrorMessage(int errorCode, String message) {
        this(errorCode, null, message);
    }

    public ValidationErrorMessage(String fieldName, String message) {
        super(message);
        this.fieldName = fieldName;
    }

    public ValidationErrorMessage(int errorCode, String fieldName, String message) {
        super(errorCode, message);
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }
}
