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
