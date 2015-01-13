package com.scholastic.slz.core.data.dao;

import java.util.List;

import com.scholastic.slz.core.data.model.ClassInfo;

/**
 * 
 * @author vijay.abbigeri
 *
 */
public interface ClassDao extends GenericDao<ClassInfo> {
	
	/**
	 * Returns the class for a given UUID 
	 * @param	uuid
	 * 			the UUID of the class 
	 * @return	the class
	 */
	ClassInfo findClassByUuid(String uuid);
	
	/**
	 * Returns the class for a given UUID 
	 * @param	identifier
	 * 			Query String 
	 * @return	the list of class
	 */
	List<ClassInfo> findClassesForTeacher(Integer identifier, String queryString);
	
	/**
	 * Returns the list class for a given UUID 
	 * @param	uuid
	 * 			the UUID ,GUID , QueryString  
	 * @return	the list of class
	 */
	List<ClassInfo> findAllClasses(String orgUuid , String orgGuid , String queryString);
	
}
