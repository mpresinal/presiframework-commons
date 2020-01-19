/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.presiframework.common.rest;

import com.presiframework.common.rest.response.ResponseDataID;
import com.presiframework.common.rest.response.RestServiceResponse;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Miguel Presinal <presinal378@gmail.com>
 * @param <D> dto
 * @param <C> una implementacion de CriteriosBusqueda
 * @since 1.0
 */
public interface CrudRestService<D, C> {

    
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    ResponseEntity<RestServiceResponse<D>> create(@RequestBody D dto);    

    @RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    ResponseEntity<RestServiceResponse<D>> update(@RequestBody D dto);    
 
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    ResponseEntity<RestServiceResponse<ResponseDataID>> delete(@PathVariable Long id);
    
    @RequestMapping(path = "/delete/batch", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    ResponseEntity<RestServiceResponse<ResponseDataID>> batchDelete(@RequestBody List<Long> id);
    
    @RequestMapping(path="/{id}",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    ResponseEntity<RestServiceResponse<D>> findById(@PathVariable Long id);
    
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    ResponseEntity<RestServiceResponse<List<D>>> findAll();    
    
    @RequestMapping(path="/filter", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    ResponseEntity<RestServiceResponse<List<D>>> doFilter(@ModelAttribute C criteria);
    
}
