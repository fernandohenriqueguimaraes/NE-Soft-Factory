package br.com.nesoftware.integracao.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;


/**
 * Interface para Dao Generico
 * 
 */
public interface IDao<T> {
	
	public EntityManager getEntityManager();

	public void inserir(T pojo);
	
	public T obterPorId(final Class<T> entityClass, Long id);
    public TypedQuery<T> createNamedQuery(String namedQuery, Class<T> class1);
    public List<T> obterTodos(Class<T> clazz);
    
    
}