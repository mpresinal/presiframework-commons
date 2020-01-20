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

package com.presiframework.common.datalayer.jpa.repository.filter;

import com.presiframework.common.datalayer.jpa.repository.filter.util.JpaSearchCriteriaPredicateUtil;
import com.presiframework.common.datalayer.repository.SearchCriteria;
import java.util.Collection;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author Miguel Presinal <presinal378@gmail.com.do>
 * @since 1.0
 */
public abstract class JpaSearchCriteria<E> implements SearchCriteria<CriteriaQuery<E>> {
    
    protected abstract CriteriaBuilder getCriteriaBuilder();

    protected abstract Class<E> getEntityClass();
    
    protected abstract List<Predicate> buildWhereConditions(Root<E> root, CriteriaBuilder builder);

    @Override
    public CriteriaQuery<E> buildCriteria() {
        Class<E> entityClass = getEntityClass();
        CriteriaBuilder builder = getCriteriaBuilder();
        CriteriaQuery<E> criteriaQuery = builder.createQuery(entityClass);
        Root<E> root = criteriaQuery.from(entityClass);
        List<Predicate> predicates = buildWhereConditions(root, builder);
        criteriaQuery.select(root).where(predicates.toArray(new Predicate[0]));
        return criteriaQuery;
    }
    
    protected Predicate createLikePredicate(Root root, CriteriaBuilder cb, String fieldName, Object value) {        
        return JpaSearchCriteriaPredicateUtil.createLikePredicate(root, cb, fieldName, value);
    }
    
    protected Predicate createEqualPredicate(Root root, CriteriaBuilder cb, String fieldName, Object value) {        
        return JpaSearchCriteriaPredicateUtil.createEqualPredicate(root, cb, fieldName, value);
    }
    
    protected Predicate createInPredicate(Root root, CriteriaBuilder cb, String fieldName, Collection values) {        
        return JpaSearchCriteriaPredicateUtil.createInPredicate(root, cb, fieldName, values);
    }
}
