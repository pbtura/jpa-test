package com.tura.jpa.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceUnit;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.sql.DataSource;

import com.tura.jpa.domain.Division;
import com.tura.jpa.domain.Division_;
import com.tura.jpa.domain.SubCollection;
import com.tura.jpa.domain.SubCollection_;
import com.tura.jpa.dto.SearchDTO;


/**
 * Session Bean implementation class JpaService
 */
@Stateless
@LocalBean
public class JpaService {

	@PersistenceUnit(unitName = "JpaPersistenceUnit")
	protected EntityManager entityManager;
	
	@Resource
	public DataSource datasource;
	
    /**
     * Default constructor. 
     */
    public JpaService() {
        // TODO Auto-generated constructor stub
    }

    public CriteriaQuery<SearchDTO> buildQuery()
    {
    	CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<SearchDTO> selectQuery = cb.createQuery(SearchDTO.class);
		Root<Division> root = selectQuery.from(Division.class);
		Join<Division, SubCollection> subcollectionJoin = root.join(Division_.subCollection, JoinType.LEFT);
		// select records by record id
		Predicate predicate = null;
		List<Predicate> predicates = new ArrayList<Predicate>();

		if (predicates.size() > 1)
		{
			predicate = cb.and(predicates.toArray(new Predicate[predicates.size()]));
		}
		else if (predicates.size() > 0)
		{
			predicate = predicates.get(0);
		}
		if (predicate != null)
		{
			selectQuery.where(predicate);
		}
		return selectQuery.select(cb.construct(SearchDTO.class, root.get(Division_.id), root.get(Division_.value),
				 subcollectionJoin.get(SubCollection_.id))).distinct(true);
    }

}
