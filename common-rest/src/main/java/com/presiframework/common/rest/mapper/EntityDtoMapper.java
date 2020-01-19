/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
