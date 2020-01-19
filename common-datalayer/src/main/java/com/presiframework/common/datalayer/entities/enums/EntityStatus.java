/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
