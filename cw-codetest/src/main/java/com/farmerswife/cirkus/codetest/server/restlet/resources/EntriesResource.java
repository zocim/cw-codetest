package com.farmerswife.cirkus.codetest.server.restlet.resources;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

public class EntriesResource extends ServerResource{
	
	@Get
	public String getMessages() {
		return "Hello Entry!";
	}
	
}
