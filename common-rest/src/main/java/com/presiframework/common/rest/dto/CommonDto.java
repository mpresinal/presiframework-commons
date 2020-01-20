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

package com.presiframework.common.rest.dto;

import com.presiframework.common.datalayer.entities.enums.EntityStatus;
import java.util.Date;

/**
 *
 * @author Miguel Presinal<presinal378@gmail.com>
 * @since 1.0
 */
public class CommonDto {

    protected String _objectName;
    protected String link;
    protected Long id;
    private Date creationDate;
    private Date lastUpdate;
    private Long createdBy;
    private Long updatedBy;
    private boolean deleted;
    private EntityStatus status;

    public CommonDto(String objectName) {
        this._objectName = objectName;
    }

    public CommonDto(Long id) {
        this.id = id;
    }

    public String get_objectName() {
        return _objectName;
    }

    public void set_objectName(String objectName) {
        this._objectName = objectName;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String toString(String childClassName, String childClassProperties) {
        return childClassName + "{" + "link=" + link + ", id=" + id
                + (childClassProperties != null ? ", " + childClassProperties : "")
                + ", creationDate=" + creationDate + ", lastUpdate=" + lastUpdate
                + ", createdBy=" + createdBy + ",updatedBy=" + updatedBy + ", status=" + status + '}';
    }

}
