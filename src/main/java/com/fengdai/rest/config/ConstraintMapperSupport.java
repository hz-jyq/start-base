package com.fengdai.rest.config;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;

public class ConstraintMapperSupport implements ExceptionMapper<ConstraintViolationException>{

	@Override
	public Response toResponse(ConstraintViolationException exception) {
		Set<ConstraintViolation<?>> set= exception.getConstraintViolations();
		StringBuffer str=new StringBuffer("参数");
		for(ConstraintViolation<?> v:set ){
			str.append(v.getPropertyPath().toString());
			str.append(v.getMessage());	
		}
	    return Response.status(Status.OK).entity(str.toString()).type(MediaType.APPLICATION_JSON).build();
	}

}
