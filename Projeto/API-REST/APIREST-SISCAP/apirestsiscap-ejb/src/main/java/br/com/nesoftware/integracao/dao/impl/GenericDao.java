package br.com.nesoftware.integracao.dao.impl;

import java.util.List;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.nesoftware.integracao.dao.IDao;

@Dependent
public class GenericDao<T> implements IDao<T> { 
	
	@PersistenceContext(unitName="siscap")
	private EntityManager em;
	
	
	@Override
	public EntityManager getEntityManager(){
		return em;
	}
	
	@Override
    public TypedQuery<T> createNamedQuery(String namedQuery, Class<T> resultClass) {
        return em.createNamedQuery(namedQuery, resultClass);
    }
	
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void inserir(T pojo) {
		em.persist(pojo);
	}

	
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public T obterPorId(final Class<T> entityClass, Long id){
		return em.find(entityClass, id);
	}

	@Override
	public List<T> obterTodos(Class<T> clazz) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
	    CriteriaQuery<T> criteriaQuery = builder.createQuery(clazz);
	    Root<T> root = criteriaQuery.from(clazz);
	    criteriaQuery.select(root);
	    TypedQuery<T> query = em.createQuery(criteriaQuery);
	    return query.getResultList();
	}
	
	
	

	
}