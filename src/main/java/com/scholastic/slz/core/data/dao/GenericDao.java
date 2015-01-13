package com.scholastic.slz.core.data.dao;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author vijay.abbigeri
 * 
 * @param <T>
 *            Indicates the type of entity object to be used for this
 *            instantiation
 */
public interface GenericDao<T> {

	/**
	 * enum for the deleted field
	 * 
	 */
	enum DELETED {
		Y, N
	}

	/**
	 * Persists the given entity
	 * 
	 * @param t
	 *            the entity object that needs to be persisted
	 * @return created entity
	 */
	T create(T t);

	/**
	 * Deletes the entity
	 * 
	 * @param id
	 *            the primary key of the entity to be deleted
	 */
	void delete(Object id);

	/**
	 * Updates the given entity
	 * 
	 * @param t
	 *            the entity object that needs to be merged
	 * @return updated entity
	 */
	T update(T t);

	/**
	 * Update the list of entity
	 * 
	 * @param t
	 *            the list of entity object that needs to be merged
	 * @return updated list of entity
	 */
	List<T> updateAll(List<T> t);

	/**
	 * Finds an entity
	 * 
	 * @param id
	 *            the primary key of the entity to be found
	 * @return the entity
	 */
	T find(Object id);

	/**
	 * Returns the result list for a given named query
	 * 
	 * @param namedQuery
	 *            the named query to be executed
	 * @param parameters
	 *            the query parameters
	 * @return list of results
	 */
	List<T> findWithNamedQuery(String namedQuery, Map<String, Object> parameters);

	/**
	 * Returns the result list for a given named native query
	 * 
	 * @param namedQuery
	 *            the named native query to be executed
	 * @param parameters
	 *            the query parameters
	 * @return list of results
	 */
	List<?> findWithNamedNativeQuery(String namedQuery,
			Map<String, Object> parameters);

	/**
	 * Returns a single result for a given named query
	 * 
	 * @param namedQuery
	 *            the named query to be executed
	 * @param parameters
	 *            the query parameters
	 * @return the query result
	 */
	T findSingleResultWithNamedQuery(String namedQuery,
			Map<String, Object> parameters);
}
