package com.farmerswife.cirkus.codetest.server.restlet.resources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import com.farmerswife.cirkus.codetest.server.dao.UserDAO;
import com.farmerswife.cirkus.codetest.server.utils.JsonParser;

public class UsersResource extends ServerResource{
@com.google.inject.Inject UserDAO myDAO;
	
	/*
	 * @Get public String getMessages() { return "Hello Blog!"; }
	 */
	
	@Post("json")
	public Representation myPostMethod(Representation entity) {
	
	// Parse request
		try {
			Map<String, Object> requestBodyAsMap = JsonParser.jsonToMap(entity.getText());
		} catch (Exception exc) {
		}

		// Create response as a JSON object
		Map<String, Object> responseAsMap = new HashMap<String, Object>();
		// ...
		responseAsMap.get(getChallengeResponse());
		return new JsonRepresentation(responseAsMap);
	
	}

	@Get
	public Representation myGetMethod() {
		
		// Create response as a JSON array
		List<Map<String, Object>> responseArray = new ArrayList<>();
		// ...
		responseArray.add(getResponseAttributes());
		return new JsonRepresentation(new org.json.JSONArray(responseArray));
	}
}
