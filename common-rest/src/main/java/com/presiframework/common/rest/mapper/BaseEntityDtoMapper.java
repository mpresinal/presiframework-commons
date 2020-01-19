/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.presiframework.common.rest.mapper;

import com.presiframework.common.datalayer.entities.CommonEntity;
import com.presiframework.common.rest.dto.CommonDto;

/**
 *
 * @author Miguel Presinal <presinal378@gmail.com>
 * @param <D>
 * @param <E>
 * @since 1.0
 */
public abstract class BaseEntityDtoMapper<D extends CommonDto, E extends CommonEntity<Long>> implements EntityDtoMapper<D, E>{

    public abstract D newDTO();    
    public abstract E newEntity();

    
    protected void commonRefreshDto(CommonDto dto, CommonEntity<Long> entity) {
        dto.setId(entity.getId());        
        dto.setStatus(entity.getStatus());
        dto.setCreatedBy(entity.getCreatedBy());
        dto.setUpdatedBy(entity.getUpdatedBy());
        dto.setCreationDate(entity.getCreationDate());
        dto.setLastUpdate(entity.getLastUpdate());        
    }
    
    protected void commonRefreshEntity(CommonEntity<Long> entity, CommonDto dto) {
        entity.setId(dto.getId());        
        entity.setStatus(dto.getStatus());
        entity.setCreatedBy(dto.getCreatedBy());
        entity.setUpdatedBy(dto.getUpdatedBy());
        entity.setCreationDate(dto.getCreationDate());
        entity.setLastUpdate(dto.getLastUpdate());      
    }
    
    @Override
    public void refreshDto(D dto, E entity) {
        commonRefreshDto(dto, entity);
    }

    @Override
    public void refreshEntity(E entity, D dto) {
        commonRefreshEntity(entity, dto);
    }        
    
    @Override
    public E toEntity(D dto) {
        if(dto == null){
            return null;
        }
        
        E entity = newEntity();        
        this.refreshEntity(entity, dto);
        return entity;
    }

    @Override
    public D toDto(E entity) {
        if(entity == null){
            return null;
        }
        
        D dto = newDTO();        
        refreshDto(dto, entity);
        return dto;
    }
}
