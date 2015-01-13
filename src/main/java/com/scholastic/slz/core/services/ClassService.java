package com.scholastic.slz.core.services;

import java.util.List;

import javax.inject.Inject;

import com.scholastic.slz.core.data.dao.ClassDao;
import com.scholastic.slz.core.data.model.ClassInfo;

/**
 * 
 * @author vijay.abbigeri
 * 
 */
public class ClassService {

	/** The DAO for class */
	@Inject
	private ClassDao classDao;

	/**
	 * Returns the class for a given UUID
	 * 
	 * @param uuid
	 *            the UUID of the class
	 * @return the class
	 */
	public ClassInfo getClassByUuid(final String uuid) {
		return classDao.findClassByUuid(uuid);
	}
	
	/**
	 * Returns the list of class for a given Organisation
	 * 
	 * @param orgUuid
	 *            the UUID of the class
	 * @param queryString for search 
	 * @return the list of class
	 */
	public List<ClassInfo> getClassesWithQuery(final String orgUuid,final String orgGuid,final String queryString) {
		return classDao.findAllClasses(orgUuid, orgGuid, queryString);
	}
	
	/**
	 * Returns the list of class for a given identifier
	 * 
	 * @param identifier
	 *            the UUID of the class
	 * @param queryString for search           
	 * @return the class
	 */
	public List<ClassInfo> getClassesForTeacher(final Integer identifier, final String queryString) {
		return classDao.findClassesForTeacher(identifier, queryString);
	}
}
