/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.presiframework.common.datalayer.jpa.repository.filter.util;

import java.util.Collection;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author Miguel Presinal <presinal378@gmail.com.do>
 * @since 1.0
 */
public class JpaSearchCriteriaPredicateUtil {

    public static final String LIKE_WILDCARD = "%";
    
    public static Predicate createLikePredicate(Root root, CriteriaBuilder cb, String fieldName, Object value) {        
        return cb.like(root.get(fieldName), LIKE_WILDCARD+value+LIKE_WILDCARD);        
    }
    
    public static Predicate createEqualPredicate(Root root, CriteriaBuilder cb, String fieldName, Object value) {        
        return cb.like(root.get(fieldName), value.toString());
    }
    
    public static Predicate createInPredicate(Root root, CriteriaBuilder cb, String fieldName, Collection values) {        
        return root.get(fieldName).in(values);        
    }
}
