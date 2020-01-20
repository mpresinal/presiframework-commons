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
