package com.fengdai.rest.config;

import javax.validation.ValidationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class ConstraintMapperSupport implements ExceptionMapper<ValidationException>{

	@Override
	public Response toResponse(ValidationException exception) {
	
		return null;
	}

}
