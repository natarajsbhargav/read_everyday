package com.scholastic.slz.core.api.exception;
/**
 * @author Madan D H
 *
 */
import javax.ws.rs.core.Response;



public class ApplicationException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	Response.Status status;
	
	private  ESlzApiErrorReason reason;

	public Response.Status getStatus() {
		return status;
	}

	public void setStatus(Response.Status status) {
		this.status = status;
	}

	public ApplicationException() {
		super();

	}
	public ESlzApiErrorReason getReason() {
		return reason;
	}

	
	
	public ApplicationException(Response.Status status,ESlzApiErrorReason pReason,String msg) {
		super(msg);
		this.status = status;
		this.reason = pReason;
	}

	public ApplicationException(Response.Status status,String msg) {
		super(msg);
		this.status = status;
		
	}

	

}
