package com.scholastic.slz.core.data.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.UserTransaction;

import com.scholastic.slz.core.data.dao.GenericDao;
import com.scholastic.slz.core.qualifiers.Primary;

/**
 * 
 * @author vijay.abbigeri
 * 
 * @param <T>
 *            Indicates the type of entity object to be used for this
 *            instantiation
 */
public abstract class GenericDaoImpl<T> implements GenericDao<T> {

	/** The entity manager */
	@Inject
	@Primary
	protected EntityManager entityManager;

	/** The entity type */
	private Class<T> type;
	/** The entity transaction injection */
	@Inject
	UserTransaction transaction;

	/**
	 * Initializes the entity type
	 */
	public GenericDaoImpl() {
		final Type classType = getClass().getGenericSuperclass();
		if (classType instanceof Class) {
			type = (Class<T>) classType;
		} else if (classType instanceof ParameterizedType) {
			type = (Class<T>) ((ParameterizedType) classType).getRawType();
		}

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T create(final T t) {
		this.entityManager.persist(t);
		return t;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(final Object id) {
		this.entityManager.remove(this.entityManager.getReference(type, id));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T update(final T t) {
		return this.entityManager.merge(t);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<T> updateAll(final List<T> plt) {
		final List<T> lt = new ArrayList<T>();
		for (final T t : plt) {
			lt.add(update(t));
		}
		return lt;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T find(final Object id) {
		return (T) this.entityManager.find(type, id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<T> findWithNamedQuery(final String namedQuery,
			final Map<String, Object> parameters) {
		final Query query = getNamedQuery(namedQuery, parameters);
		return (List<T>) query.getResultList();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<?> findWithNamedNativeQuery(final String namedQuery,
			final Map<String, Object> parameters) {
		final Query query = getNamedQuery(namedQuery, parameters);
		return query.getResultList();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T findSingleResultWithNamedQuery(final String namedQuery,
			final Map<String, Object> parameters) {
		T result = null;
		final Query query = getNamedQuery(namedQuery, parameters);
		final List<T> results = (List<T>) query.getResultList();
		if (!results.isEmpty()) {
			result = results.get(0);
		}
		return result;
	}

	/**
	 * Creates the named query
	 * 
	 * @param namedQuery
	 *            the named query to be executed
	 * @param parameters
	 *            the query parameters
	 * @return the query
	 */
	private Query getNamedQuery(final String namedQuery,
			final Map<String, Object> parameters) {
		final Query query = entityManager.createNamedQuery(namedQuery);
		if (parameters != null) {
			for (final Entry<String, Object> entry : parameters.entrySet()) {
				query.setParameter(entry.getKey(), entry.getValue());
			}
		}
		return query;
	}
}