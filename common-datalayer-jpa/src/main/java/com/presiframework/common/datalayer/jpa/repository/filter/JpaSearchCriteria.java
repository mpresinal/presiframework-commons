/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
