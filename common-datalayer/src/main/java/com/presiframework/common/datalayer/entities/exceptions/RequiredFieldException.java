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

import com.presiframework.common.datalayer.SerialVersion;

/**
 *
 * @author Miguel Presinal <presinal378@gmail.com>
 * @since 1.0
 */
public class RequiredFieldException extends AbstractFieldException {

    /**
     *
     */
    private static final long serialVersionUID = SerialVersion.CURRENT_VERSION;

    public static final int CODE = 1004;
    public static final String DESCRIPTION = "There are required fields that were not provided.";

    public RequiredFieldException() {
        this(null);
    }
    
    public RequiredFieldException(String field) {
        this(CODE, field, null);
    }

    public RequiredFieldException(String field, Throwable cause) {
        this(CODE, field, cause);        
    }
    
    public RequiredFieldException(int codigo, String field, Throwable cause) {
        super(field, null, CODE, DESCRIPTION + ( field!= null? " Field: " + field : ""), cause);
    }
}
