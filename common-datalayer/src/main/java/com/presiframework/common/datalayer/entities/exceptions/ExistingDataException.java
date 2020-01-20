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
