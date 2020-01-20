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

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Miguel Presinal <presinal378@gmail.com>
 * @since 1.0
 */
public interface EntityDtoMapper<D, E> {

    E toEntity(D dto);
    
    D toDto(E entity);
    
    void refreshDto(D dto, E entity);
    
    void refreshEntity(E entity, D dto);
        
    default List<E> toEntityList(List<D> dtoList){
        List<E> list = new ArrayList<>();
        
        if(dtoList != null) {
            for(D dto : dtoList) {
                list.add(toEntity(dto));
            }
        }
        
        return list;
    }
    
    default List<D> toDtoList(Iterable<E> entityList){
        List<D> list = new ArrayList<>();
        
        if(entityList != null) {
            entityList.forEach(entity -> {
                list.add(toDto(entity));
            });            
        }
        
        return list;
    }
    
    default List<D> toDtoList(List<E> entityList) {
        return EntityDtoMapper.this.toDtoList((Iterable<E>)entityList);
    }    
}
