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

package com.presiframework.common.datalayer.entities.enums;

import com.presiframework.common.datalayer.SerialVersion;
import java.io.Serializable;

/**
 *
 * @author Miguel Presinal<presinal378@gmail.com>
 * @since 1.0
 */
public enum EntityStatus implements Serializable{

    ACTIVE(EntityStatus.AC),
    INACTIVE(EntityStatus.IN);

    private static final long serialVersionUID = SerialVersion.CURRENT_VERSION;
    private final String status;

    private static final String AC = "AC";
    private static final String IN = "IN";

    private EntityStatus(String status) {
        this.status = status;
    }

    public static EntityStatus valueOfStatus(String status) {
        switch (status) {
            case AC:
                return ACTIVE;
            case IN:
                return INACTIVE;
            default:
                return null;
        }
    }

    public String getValue() {
        return status;
    }
}
