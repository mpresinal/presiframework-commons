/*
 * Copyright 2020 Miguel Presinal.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
