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

package com.presiframework.common.service.exception;

import com.presiframework.common.datalayer.entities.exceptions.BaseException;

/**
 *
 * @author Miguel Presinal <presinal378@gmail.com>
 * @since 1.0
 */
public class InternalServiceException extends BaseException {

    /**
     *
     */
    private static final long serialVersionUID = 3534629452883270465L;

    public static final int CODE = 1000;
    public static final String DESCRIPTION = "An unexpected error has occured.";

    public InternalServiceException() {
        super(CODE, DESCRIPTION);
    }

    public InternalServiceException(Throwable cause) {
        super(CODE, DESCRIPTION, cause);
    }

    public InternalServiceException(String message) {
        this(DESCRIPTION+" "+message, null);
    }

    public InternalServiceException(String message, Throwable cause) {
        super(CODE, DESCRIPTION+" "+message, cause);
    }
    
}
