package com.scholastic.slz.core.data.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.TypedQuery;

import com.scholastic.slz.core.data.dao.ClassDao;
import com.scholastic.slz.core.data.model.ClassInfo;

/**
 * 
 * @author vijay.abbigeri
 *
 */
public class ClassDaoImpl extends GenericDaoImpl<ClassInfo> implements ClassDao {
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public ClassInfo findClassByUuid(final String uuid) {
		final Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("uuid", uuid);
		return findSingleResultWithNamedQuery(ClassInfo.QUERY_FIND_BY_UUID, parameters);
	}

	@Override
	public List<ClassInfo> findClassesForTeacher(Integer identifier,
			String queryString) {
		final TypedQuery<ClassInfo> classInfo = entityManager
				.createNamedQuery(ClassInfo.QUERY_FIND_CLASSES_TEACHER, ClassInfo.class)
				.setParameter("identifier", identifier)
				.setParameter("queryString", queryString);
		return classInfo.getResultList();
		
	}

	@Override
	public List<ClassInfo> findAllClasses(String orgUuid, String orgGuid,
			String queryString) {
		final TypedQuery<ClassInfo> classInfo = entityManager
				.createNamedQuery(ClassInfo.QUERY_FIND_CLASSES_STUDENT, ClassInfo.class)
				.setParameter("orgUuid", orgUuid)
				.setParameter("orgGuid", orgGuid)
				.setParameter("queryString", queryString);
		return classInfo.getResultList();
		
	}

}
