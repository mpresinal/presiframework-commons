/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.presiframework.common.datalayer.entities.exceptions;

/**
 *
 * @author Miguel Presinal <presinal378@gmail.com>
 * @since 1.0
 */
public class AbstractFieldException extends BaseException {

    protected String fieldName;
    protected Object value;

    public AbstractFieldException() {
    }    

    public AbstractFieldException(String fieldName, Object value, int code, String message) {
        super(code, message);
        this.fieldName = fieldName;
        this.value = value;
    }

    public AbstractFieldException(String nombreDato, Object valor, int code, String message, Throwable cause) {
        super(code, message, cause);
        this.fieldName = nombreDato;
        this.value = valor;
    }
    
    public String getFieldName() {
        return fieldName;
    }

    protected void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }   

    public Object getValue() {
        return value;
    }

    protected void setValue(Object value) {
        this.value = value;
    }
}
