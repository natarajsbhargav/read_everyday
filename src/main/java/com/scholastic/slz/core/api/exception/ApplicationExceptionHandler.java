package com.scholastic.slz.core.api.exception;

/**
 * @author Madan D H
 *
 */
import java.sql.Timestamp;
import java.util.List;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * ApplicationExceptionHandler will handle the SLZAPI exception
 * 
 */
@Provider
public class ApplicationExceptionHandler implements
		ExceptionMapper<ApplicationException> {
	/**
	 * LOGGER field for debugging
	 */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(ApplicationExceptionHandler.class);
	
	/**
	 * headers information of the request
	 */
	@Context
	private transient HttpHeaders headers;

	
	/* (non-Javadoc)
	 * @see javax.ws.rs.ext.ExceptionMapper#toResponse(java.lang.Throwable)
	 */
	@Override
	public Response toResponse(final ApplicationException ex) {

		final List<MediaType> accepts = headers.getAcceptableMediaTypes();
		final ErrorResponse response = new ErrorResponse();
		response.setDiagnosticInfo(ex.getMessage());
		response.setStatus(String.valueOf(ex.getStatus().getStatusCode()));
		response.setCode(ex.getReason().getCode());
		response.setTimestamp(new Timestamp(new java.util.Date().getTime())
				.toString());
		if (ex.getReason().getCode().equals("40401")) {
			response.setErrorMessage(ErrorResponse.ERROR40401);
		}else if (ex.getReason().getCode().equals("400")) {
			response.setErrorMessage(ErrorResponse.ERROR400);
		}else if (ex.getReason().getCode().equals("405")) {
			response.setErrorMessage(ErrorResponse.ERROR405);
		}else if (ex.getReason().getCode().equals("500")) {
			response.setErrorMessage(ErrorResponse.ERROR500);
		}else{
			response.setErrorMessage(ErrorResponse.ERROR400);
		}
		ResponseBuilder rbuilder = Response.status(ex.getStatus());
		MediaType mediaType = null;
		if (!accepts.isEmpty()) {
			// just pick the first one
			mediaType = accepts.get(0);
			final String mediaTypeString = mediaType.toString();
			LOGGER.debug("Setting response type to :{}",mediaType);

			if (mediaType.isWildcardType()) {
				rbuilder = rbuilder.type(MediaType.APPLICATION_JSON);
			}
			if (MediaType.APPLICATION_XML.equalsIgnoreCase(mediaTypeString)) {
				rbuilder = rbuilder.type(MediaType.APPLICATION_XML);
			}
			if (MediaType.APPLICATION_JSON.equalsIgnoreCase(mediaTypeString)) {
				rbuilder = rbuilder.type(MediaType.APPLICATION_JSON);
			}

		} else {

			rbuilder = rbuilder.type(MediaType.APPLICATION_JSON);
		}

		return rbuilder.entity(response).build();
	}

}
