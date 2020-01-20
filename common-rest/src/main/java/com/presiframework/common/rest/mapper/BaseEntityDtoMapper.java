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
