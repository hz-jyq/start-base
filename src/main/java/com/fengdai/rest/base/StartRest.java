package com.fengdai.rest.base;

import java.io.IOException;
import java.net.URI;

import javax.validation.ConstraintValidator;
import javax.validation.Validation;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import com.fengdai.rest.Annotation.NotNull;
import com.fengdai.rest.Annotation.NotNull.ValidStringChecker;

public abstract class StartRest {
	
	protected ResourceConfig buildResourceConfig() {
		 	ResourceConfig rc =new ResourceConfig();
		 	rc.packages("com.fengdai.rest.config");
		 	rc.register(com.fengdai.rest.config.ExceptionMapperSupport.class);
			rc.register(com.fengdai.rest.config.ConstraintMapperSupport.class);
		 	settingResourceConfig(rc);
		 	return rc;
	}
	
	public abstract ResourceConfig settingResourceConfig(ResourceConfig resourceConfig);
	
	
	public HttpServer startServer(String baseUri) {
	        System.out.println("Starting grizzly...");  
	    	ResourceConfig rc = buildResourceConfig();
	        HttpServer server  = GrizzlyHttpServerFactory.createHttpServer(URI.create(baseUri), rc);
	        System.out.println(String.format("Jersey app started with WADL available at" + "%s application.wadl\nTry out %shelloworld\nHit enter to stop it...",baseUri,baseUri));
	        System.out.println("...started");  
	        try {
				System.in.read();
			} catch (IOException e) {
				e.printStackTrace();
			}
	        server.shutdown();  
	        System.exit(0);  
	        return server;
	}
}
