package com.scholastic.slz.core.services;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.scholastic.slz.core.data.dao.ClassDao;
import com.scholastic.slz.core.data.model.ClassInfo;

/**
 * 
 * @author vijay.abbigeri
 * 
 */
@RunWith(MockitoJUnitRunner.class)
public class ClassServiceTest {

	/** the class DAO */
	@Mock
	private ClassDao classDao;

	/** the class service */
	@InjectMocks
	private ClassService classService = new ClassService();

	/**
	 * Tests that the service returns the class for a given UUID
	 */
	@Test
	public void testGetClassByUuid() {
		String uuid = "0b9e058c-39c8-49d5-8c1c-dfd585fdaca9";
		ClassInfo classInfo = createClass(1, "Class 1");
		Mockito.when(classDao.findClassByUuid(uuid)).thenReturn(classInfo);

		ClassInfo result = classService.getClassByUuid(uuid);
		assertNotNull(result);
	}

	/**
	 * Tests that the service returns the list of class for a given orgId and
	 * querytext match
	 */
	@Test
	public void testGetClassForTeacher() {
		final List<ClassInfo> classInfoList = new ArrayList<ClassInfo>();
		final Integer orgID = Integer.valueOf("10");
		final String queryText = "test";
		Mockito.when(classDao.findClassesForTeacher(orgID, queryText))
				.thenReturn(classInfoList);
		;
		List<ClassInfo> result = classService.getClassesForTeacher(orgID,
				queryText);
		assertNotNull(result);
	}

	/**
	 * Tests that the service returns the class for a given organization GUID,
	 * UUID and queryString
	 */
	@Test
	public void testGetClassesWithQuery() {
		final List<ClassInfo> classInfoList = new ArrayList<ClassInfo>();
		classInfoList.add(createClass(1, "Class 1"));
		final String orgUuid = "04e28b8f-46f8-49b1-a36d-1360c8bff7c101901";
		final String orgGuid = "04e28b8f-46f8-49b1-a36d-1360c8bff7c101902";
		final String queryString = "test";
		Mockito.when(classDao.findAllClasses(orgUuid, orgGuid, queryString))
				.thenReturn(classInfoList);
		List<ClassInfo> result = classService.getClassesWithQuery(orgUuid,
				orgGuid, queryString);
		assertNotNull(result);
	}

	/**
	 * Creates a class for a given identifier and name
	 * 
	 * @param identifier
	 *            the class identifier
	 * @param name
	 *            the class name
	 * @return the class
	 */
	private ClassInfo createClass(final Integer identifier, final String name) {
		final ClassInfo classinfo = new ClassInfo();
		classinfo.setId(identifier);
		classinfo.setName(name);
		return classinfo;
	}
}