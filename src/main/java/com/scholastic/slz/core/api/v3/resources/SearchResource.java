package com.scholastic.slz.core.api.v3.resources;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.scholastic.slz.core.api.LegacyApiConstants;
import com.scholastic.slz.core.api.LegacyApiUtils;
import com.scholastic.slz.core.api.exception.ApplicationException;
import com.scholastic.slz.core.api.exception.ESlzApiErrorReason;
import com.scholastic.slz.core.api.v3.helpers.SearchHelper;
import com.scholastic.slz.core.api.v3.vo.SearchResultVO;
import com.scholastic.slz.core.data.model.ClassInfo;
import com.scholastic.slz.core.data.model.Organization;
import com.scholastic.slz.core.data.model.OrganizationGroup;
import com.scholastic.slz.core.data.model.UserAccount;
import com.scholastic.slz.core.services.ClassService;
import com.scholastic.slz.core.services.OrganizationGroupService;
import com.scholastic.slz.core.services.OrganizationService;
import com.scholastic.slz.core.services.UserService;

/**
 * 
 * @author vijay.abbigeri
 * 
 */
@Path(LegacyApiConstants.SEARCH_PATH)
public class SearchResource {

	/** The logger for search resource */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(SearchResource.class);

	/** The organization service */
	@Inject
	private transient OrganizationService orgService;

	/** The organization group service */
	@Inject
	private transient OrganizationGroupService orgGroupService;
	
	/** The user service */
	@Inject
	private transient UserService userService;
	
	/** The Class service */
	@Inject
	private transient ClassService classService;

	/**
	 * GETs the search results for a given search query
	 * 
	 * @param queryString
	 *            the search query
	 * @param userId
	 *            the user identifier
	 * @param orgId
	 *            the organization identifier
	 * @param types
	 *            comma-separated list of types to search for
	 * @return response (success/failure)
	 * @throws ApplicationException
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getSearchResults(@QueryParam("q") final String queryString,
			@QueryParam("user-identifier") final String userId,
			@QueryParam("school-identifier") final String orgId,
			@QueryParam("types") final String types)
			throws ApplicationException {
		String userType ="";
        Integer identifier = null;
        List<UserAccount> userAccountList = null;
        List<ClassInfo> classInfoList = null;
        List<ClassInfo> classTeacherList = null;
        final SearchResultVO searchResultVO = new SearchResultVO();
        String orgUUID = "";
        String orgGUID = "";
		UserAccount user = null;
		final String authZoneCode = "SRII";
		Integer schoolIdentifier = null;
		if (queryString == null || queryString.length() < 3) {
			throw new ApplicationException(
					Status.NOT_FOUND,
					ESlzApiErrorReason.AUTH_USER_NOT_FOUND,
					"Could not search with query string "
							+ queryString
							+ ". Query String should be greater than or equals to 3 characters");
		} else {
			final String pattern = "%"+queryString.toLowerCase(Locale.getDefault())+"%";
			if (userId != null && !userId.isEmpty()){
				user = userService.getUserByIdentifier(userId);
			}
			if (user != null) {
                userType = user.getType();
                schoolIdentifier =  user.getOrganization().getId();
                if((!userType.equalsIgnoreCase("student")) && schoolIdentifier != null && (userType.equalsIgnoreCase("gadmin") || userType.equalsIgnoreCase("administrator") || userType.equalsIgnoreCase("teacher"))){
                	userAccountList = userService.getStudentByClasses("student", authZoneCode, pattern, schoolIdentifier);
                 	identifier = user.getId();
                 	 SearchHelper.addAllStudentUsers(userAccountList, searchResultVO);
                 	if(userType.equalsIgnoreCase("teacher")){
                	classTeacherList = classService.getClassesForTeacher(identifier, pattern);
                	SearchHelper.addAllClassDetails(classTeacherList, searchResultVO);
                 	}else{
                 		if(userAccountList != null && userAccountList.size() > 0){
                    		orgGUID = userAccountList.get(0).getOrganization().getOrgGuid();
                         	orgUUID = userAccountList.get(0).getOrganization().getOrgUuid();
                    	    classInfoList = classService.getClassesWithQuery(orgUUID, orgGUID, pattern);
                    	    SearchHelper.addAllClassDetails(classInfoList, searchResultVO);
                    	}
                 	}
                	if(userType.equalsIgnoreCase("gadmin")){
            			// add organization and organization group results
            			addOrgAndOrgGroupResults(orgId, queryString, searchResultVO);
                	}
                }
			}
			
			else{ throw  new ApplicationException(
					Status.NOT_FOUND,
					ESlzApiErrorReason.AUTH_USER_NOT_FOUND,
					"Could not find the user with id "+ userId);
			}
			
			return Response.ok(searchResultVO).build();
		}
	}

	/**
	 * Adds Organization and Organization Group Search Results
	 * 
	 * @param orgId
	 *            the organization identifier
	 * @param queryString
	 *            the query string
	 * @param searchResultVO
	 *            the search result VO to which the organization and
	 *            organization group results will be added
	 */
	private void addOrgAndOrgGroupResults(final String orgId,
			final String queryString, final SearchResultVO searchResultVO) {
		LOGGER.debug("Adding organization and organization group results");
		if (orgId == null) {
			LOGGER.debug(
					"Org Id is not specified; Getting org and org groups with name containing: {}",
					queryString);
			final List<Organization> orgs = orgService
					.getOrganizationsByName(queryString);
			SearchHelper.addOrganizationDetails(orgs, searchResultVO);
			final List<OrganizationGroup> orgGroups = orgGroupService
					.getOrganizationGroupsByName(queryString);
			SearchHelper.addOrganizationGroupDetails(orgGroups, searchResultVO);
		} else {
			LOGGER.debug("Getting Organization with Id: {}", orgId);
			final String[] identifiers = orgId.split(LegacyApiConstants.COLON);
			if (identifiers.length == 2 && !LegacyApiUtils.hasNull(identifiers)) {
				final String orgGuid = identifiers[0];
				final String orgUuid = identifiers[1];
				LOGGER.debug("Organization GUID: {}, UUID: {}", orgGuid,
						orgUuid);
				Organization org = orgService.getOrganizationByNameAndId(
						queryString, orgGuid, orgUuid);
				SearchHelper.addOrganizationDetails(org, searchResultVO);
				org = orgService.getOrganizationByIdAndGroupName(queryString,
						orgGuid, orgUuid);
				SearchHelper.addOrganizationGroupDetails(org, searchResultVO);
			}
		}
	}
}
