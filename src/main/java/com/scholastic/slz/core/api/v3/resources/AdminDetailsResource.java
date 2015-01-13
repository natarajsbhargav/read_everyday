package com.scholastic.slz.core.api.v3.resources;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.scholastic.slz.core.api.LegacyApiConstants;
import com.scholastic.slz.core.api.exception.ApplicationException;
import com.scholastic.slz.core.api.v3.vo.AdminDetailsResponse;
import com.scholastic.slz.core.api.v3.vo.School;
import com.scholastic.slz.core.data.model.UserAccount;
import com.scholastic.slz.core.services.UserService;

/**
 * @author madan.haraginadoni
 *
 */
/**
 * This is for getting Admin details from Resteasy service
 * 
 */
@Path(LegacyApiConstants.ADMIN_DETAILS_PATH)
public class AdminDetailsResource {
	/**
	 * LOGGER field for debugging
	 */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(AdminDetailsResource.class);

	/**
	 * Injecting the Admin which consist of Implementing method service layer
	 * Object
	 */
	@Inject
	private transient UserService adminService;
	/**
	 * Date Formatting for response object timestamp
	 */
	private SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss.H");
	/**
	 * @param code
	 * @return
	 * @throws ApplicationException
	 * @throws SQLException
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getAdminDetails(@PathParam("identifier") final String code)
			{
		Response response = null;
		UserAccount userAccount = null;

		if (code != null && !code.isEmpty()) {
			
				LOGGER.debug("Admin identifier : {}", code);
				userAccount = adminService.getAdminDetails(code);
			
			if (userAccount != null) {

				final AdminDetailsResponse adminDetailsResponse = new AdminDetailsResponse();
				adminDetailsResponse.setFirstName(userAccount.getFirstName());
				adminDetailsResponse.setLastName(userAccount.getLastName());
				adminDetailsResponse.setTimestamp(dateFormat.format(new Date()));
				adminDetailsResponse.setUserIdentifier(userAccount.getUuid());
				final School school = new School();
				if(userAccount.getOrganization() != null){
				school.setIdentifier(userAccount.getOrganization().getOrgGuid());
				school.setName(userAccount.getOrganization().getName());
				}
				adminDetailsResponse.setSchool(school);
				final ResponseBuilder respbuilder = Response
						.ok(adminDetailsResponse);
				response = respbuilder.build();
				return response;
			} else {
				throw new ApplicationException(
						Response.Status.NOT_FOUND,
						com.scholastic.slz.core.api.exception.ESlzApiErrorReason.AUTH_USER_NOT_FOUND,
						"Could not find a school admin user with identifier "
								+ code);
			}
		} else {
			throw new ApplicationException(
					Response.Status.NOT_FOUND,
					com.scholastic.slz.core.api.exception.ESlzApiErrorReason.AUTH_USER_NOT_FOUND,
					"Could not find a school admin user with identifier ");
		}

	}
}
