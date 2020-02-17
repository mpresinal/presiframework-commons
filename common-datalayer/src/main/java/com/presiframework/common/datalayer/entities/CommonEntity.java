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

package com.presiframework.common.datalayer.entities;

import com.presiframework.common.datalayer.SerialVersion;
import com.presiframework.common.datalayer.entities.enums.EntityStatus;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Miguel Presinal<presinal378@gmail.com>
 * @param <ID>
 * @since 1.0
 */
public class CommonEntity<ID> implements Serializable {

    private static final long serialVersionUID = SerialVersion.CURRENT_VERSION;
    
    private ID id;
    private Date creationDate;
    private Date lastUpdate;
    private Long createdBy;
    private Long updatedBy;
    private boolean deleted;
    private EntityStatus status;

    public CommonEntity() {
    }

    public CommonEntity(ID id) {
        this();
        this.id = id;
    }

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Long getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
    }

    
    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public EntityStatus getStatus() {
        if (status == null) {
            status = EntityStatus.ACTIVE;
        }

        return status;
    }

    public void setStatus(EntityStatus status) {
        this.status = status;
    }

    
    public void onPrePersist() {
        if (creationDate == null) {
            creationDate = new Date();
        }
        
        if (lastUpdate == null) {
            lastUpdate = new Date();
        }
    }
    
    public void onPreUpdate() {
        lastUpdate = new Date();
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CommonEntity<?> other = (CommonEntity<?>) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CommonField{" + "id=" + id + ", creationDate=" + creationDate + ", lastUpdate=" + lastUpdate + ", createdBy=" + createdBy+ ",updatedBy=" + updatedBy + ", deleted=" + deleted + ", status=" + status + '}';
    }

    public String toString(String classname, String props) {
        return classname + '{'
                + "id=" + id
                + props
                + ", creationDate=" + creationDate
                + ", lastUpdate=" + lastUpdate
                + ", createdBy=" + createdBy
                + ",updatedBy=" + updatedBy
                + ", deleted=" + deleted
                + ", status=" + status
                + '}';
    }
}
