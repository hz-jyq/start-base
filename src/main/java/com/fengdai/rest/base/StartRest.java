package com.fengdai.rest.base;

import java.io.IOException;
import java.net.URI;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

public abstract class StartRest {
	
	protected ResourceConfig buildResourceConfig() {
		 	ResourceConfig rc =new ResourceConfig();
		 	 rc.packages("com.fengdai.rest.config");
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
