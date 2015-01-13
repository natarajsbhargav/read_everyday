package com.scholastic.slz.core.api;

/**
 * @author snehalata.raulo
 * 
 */
public interface LegacyApiConstants {

	enum UserType {
		STUDENT, TEACHER
	}

	String SLZAPI_V2_BASE_PATH = "/slzapi/v2";
	String SLZAPI_V3_BASE_PATH = "/slzapi/v3";

	String ADMIN_DETAILS_PATH = "/school-admins/{identifier}";
	String CLASS_PATH = "/classes";
	String COLON = ":";
	String FULL_PATH = "/full";
	String GRADE_SYSTEM_PATH = "/grade-systems/{identifier}";
	int ID_SIZE = 1;
	String IDENTIFIER = "identifier";
	int IDENTIFIER_LENGTH = 2;
	String IDENTIFIER_PATH = "/{identifier}";
	String LEXILE_UPDATE_PATH = "/students/{identifier}/lexile";
	String NAME = "name";
	String OFFLINE = "/offline";
	String OFFLINE_NUM_DAYS = "offline_num_days";
	String ORDER_EXPIRY_DATE = "order_expiry_date";
	int ORG_CODE_LENGTH = 7;
	String ORG_CODE_PATH = "/codes/{code}";
	String ORG_GRADE_PATH = "/{org-identifier}/{scholastic-grade-code}";
	String ORG_GROUP_ADMIN_PATH = "/group-admins";
	String ORG_GROUP_PATH = "/school-groups";
	String ORG_IDENTIFIER = "org-identifier";
	String ORG_PATH = "/school";
	String ORGS_PATH = "/schools";
	String PRODUCT = "product";
	String PRODUCT_CODE = "productCode";
	String REGION_ROOT_PATH = "/regions";
	String REGISTRATION = "/registration";
	String REGISTRATION_UUID = "registration_uuid";
	String REMOVE_MACHINE = "/registration/remove-machine";
	String REMOVE_PRODUCT = "/registration/remove-product";
	String SCHOLASTIC_GRADE_CODE = "scholastic-grade-code";
	String SCHOOL_CALENDAR_PATH = "/school-calendars/{identifier}";
	String SEARCH_PATH = "/search";
	String STUDENTS_PATH = "/students";
	String STUDENT_USERNAME = "student-username";
	String TEACHER_DETAIL_PATH = "/teachers/{identifier}";
	String USERNAMES_PATH = "/usernames/{student-username}";
	String UUID = "uuid";
	String VERIFY_MACHINE = "/registration/verify-machine";
	String VERIFY_PRODUCT = "/registration/verify-product";
}