package com.fengdai.rest.config;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.fengdai.rest.helper.ResourceHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
public class ExceptionMapperSupport implements ExceptionMapper<Exception> {
	
	private Logger log = LoggerFactory.getLogger(ExceptionMapperSupport.class);
	
	@Override
	public Response toResponse(Exception exception) {
		log.error("--resource层捕获异常--",exception);
		return ResourceHelper.toError(exception);
	}
}
