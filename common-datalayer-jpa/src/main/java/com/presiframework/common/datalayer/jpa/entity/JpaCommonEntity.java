/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.presiframework.common.datalayer.jpa.entity;

import com.presiframework.common.datalayer.entities.CommonEntity;
import com.presiframework.common.datalayer.entities.enums.EntityStatus;
import java.util.Date;
import javax.persistence.MappedSuperclass;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;

/**
 *
 * @author Miguel Presinal <presinal378@gmail.com.do>
 * @since 1.0
 */
@MappedSuperclass
@Access(AccessType.PROPERTY)
public class JpaCommonEntity extends CommonEntity<Long> {

    public JpaCommonEntity() {
    }

    public JpaCommonEntity(Long id) {
        super(id);
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    @Override
    public Long getId() {
        return super.getId();
    }

    @Column(name = "CREATED_BY", nullable = false)
    @Override
    public Long getCreatedBy() {
        return super.getCreatedBy();
    }

    @Column(name = "UPDATED_BY", nullable = false)
    @Override
    public Long getUpdatedBy() {
        return super.getUpdatedBy();
    }

    @Column(name = "LAST_UPDATE", nullable = true, insertable = true, updatable = true)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Override
    public Date getLastUpdate() {
        return super.getLastUpdate();
    }

    @Column(name = "CREATION_DATE", nullable = true, insertable = true, updatable = false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Override
    public Date getCreationDate() {
        return super.getCreationDate();
    }
    
    @Column(name = "STATUS", length = 5, nullable = false)
    public String getStrStatus() {
        EntityStatus status = super.getStatus();
        return status != null ? status.getValue() : null;
    }
    
    public void setStrStatus(String strStatus) {
        super.setStatus(EntityStatus.valueOfStatus(strStatus));
    }

    @Column(name="DELETED", nullable = false)
    @Override
    public boolean isDeleted() {
        return super.isDeleted();
    }
}
