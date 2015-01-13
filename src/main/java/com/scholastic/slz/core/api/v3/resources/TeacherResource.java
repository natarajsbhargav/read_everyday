package com.scholastic.slz.core.api.v3.resources;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.scholastic.slz.core.api.LegacyApiConstants;
import com.scholastic.slz.core.api.exception.ApplicationException;
import com.scholastic.slz.core.api.exception.ESlzApiErrorReason;
import com.scholastic.slz.core.api.v3.vo.ClassResponse;
import com.scholastic.slz.core.api.v3.vo.School;
import com.scholastic.slz.core.api.v3.vo.TeacherDetailsResponseVO;
import com.scholastic.slz.core.data.model.ClassInfo;
import com.scholastic.slz.core.data.model.UserAccount;
import com.scholastic.slz.core.services.UserService;

/**
 * @author madan.haraginadoni
 *
 */
/**
 * Teacher resource to get the Teacher details by Identifier
 * 
 */
@Path(LegacyApiConstants.TEACHER_DETAIL_PATH)
public class TeacherResource {

	/**
	 * Logger to log the debug details at runtime
	 */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(TeacherResource.class);

	/**
	 * Date Formatting for response object timestamp
	 */
	private SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss.H");
	/**
	 * Injecting the teacher service object
	 */
	@Inject
	private transient UserService teacherService;

	/**
	 * @param code
	 * @return
	 * @throws ApplicationException
	 * @throws SQLException
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getTeacherDetails(@PathParam("identifier") String code)
			throws ApplicationException{
		Response response = null;
		UserAccount teacherInfo = null;

		if (code != null && !code.isEmpty()) {
			
				LOGGER.debug("Teacher identifier  : {}" , code);
				teacherInfo = teacherService.getTeacherDetails(code);
			
			if (teacherInfo != null) {
				final List<ClassResponse> classList = new ArrayList<ClassResponse>();
				final TeacherDetailsResponseVO teacherDetailsResponse = new TeacherDetailsResponseVO();
				teacherDetailsResponse.setIdentifier(teacherInfo.getUuid());
				teacherDetailsResponse.setFirstName(teacherInfo.getFirstName());
				teacherDetailsResponse.setLastName(teacherInfo.getLastName());
				teacherDetailsResponse.setTimestamp(dateFormat
						.format(new Date()));
				final School schoolDetails = new School();
				if(teacherInfo.getOrganization() != null){
				schoolDetails.setIdentifier(teacherInfo.getOrganization()
						.getOrgUuid());
				schoolDetails.setName(teacherInfo.getOrganization().getName());
				}
				final Set<ClassInfo> classInfoList = teacherInfo.getOrganization().getClasses();
				for (final ClassInfo classVO : classInfoList) {
					ClassResponse classDetails = new ClassResponse();
					classDetails.setIdentifier(classVO.getUuid());
					classDetails.setName(classVO.getName());
					classList.add(classDetails);
				}
				teacherDetailsResponse.setClasses(classList);
				teacherDetailsResponse.setSchool(schoolDetails);
				final ResponseBuilder respbuilder = Response
						.ok(teacherDetailsResponse);
				response = respbuilder.build();
				return response;
			} else {
				throw new ApplicationException(Status.NOT_FOUND,
						ESlzApiErrorReason.AUTH_USER_NOT_FOUND,
						"Could not find a teacher with identifier " + code);
			}
		} else {
			LOGGER.debug("Teacher identifier not found  : ");
			throw new ApplicationException(Status.NOT_FOUND,
					ESlzApiErrorReason.AUTH_USER_NOT_FOUND,
					"Could not find a teacher with identifier " + code);
		}

	}

}
