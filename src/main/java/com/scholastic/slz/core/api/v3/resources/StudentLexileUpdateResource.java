package com.scholastic.slz.core.api.v3.resources;

import java.sql.SQLException;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.scholastic.slz.core.api.LegacyApiConstants;
import com.scholastic.slz.core.api.exception.ApplicationException;
import com.scholastic.slz.core.api.exception.ESlzApiErrorReason;
import com.scholastic.slz.core.services.UserService;

/**
 * @author madan.haraginadoni
 *
 */
/**
 * Lexile update resource by using form parameters and Identifier
 * 
 */

@Path(LegacyApiConstants.LEXILE_UPDATE_PATH)
public class StudentLexileUpdateResource {
	/**
	 * Logger to log the debug details at runtime
	 */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(StudentLexileUpdateResource.class);

	/**
	 * This the root element for the rest service
	 */
	public static final String ROOT = "/v3";
	/**
	 * Injecting the service layer object
	 */
	@Inject
	private transient UserService lexileUpdate;

	/**
	 * @param code
	 * @param lexileText
	 * @throws ApplicationException
	 * @throws SQLException
	 */
	@PUT
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	public Response studentLexileUpdate(
			@PathParam("identifier") final String code,
			@FormParam("text") final String text) throws ApplicationException {
		Integer lexileScore = null;
		Response response = null;
		LOGGER.debug("Student identifier  : {}", text);
		if (!code.isEmpty() && !text.isEmpty()) {
			LOGGER.debug("Student identifier  : {}", code);
			try {
				lexileScore = Integer.parseInt(text);
			} catch (Exception exp) {
				throw new ApplicationException(Status.BAD_REQUEST,
						"Invalid lexile score format");
			}
			String lexileSource = "intl";
			// TODO Auto-generated catch block
			// Need to write the code base to retrieve the lexileSource when
			// authentication module is implemented
			if (lexileScore != null)
				lexileUpdate.putStudentLexile(code, lexileSource, lexileScore);
			final ResponseBuilder respbuilder = Response.ok();
			response = respbuilder.build();
			return response;
		} else {

			throw new ApplicationException(Status.NOT_FOUND,
					ESlzApiErrorReason.AUTH_USER_NOT_FOUND,
					"Could not find a teacher with identifier " + code);
		}

	}
}
