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

import com.farmerswife.cirkus.codetest.entities.Blog;
import com.farmerswife.cirkus.codetest.server.dao.UserDAO;
import com.farmerswife.cirkus.codetest.server.utils.JsonParser;

public class BlogsResource extends ServerResource{
	@com.google.inject.Inject UserDAO myDAO;
	
	// POST methods
		@Post("json")
		public Representation addBlog(Representation entity) {
			
			// Parse request
			try {
				Map<String, Object> requestBodyAsMap = JsonParser.jsonToMap(entity.getText());
				 //Representation rep = new JsonRepresentation(entity.toJSON());
		         getResponse().setEntity(entity);
			} catch (Exception exc) {
			}

			// Create response as a JSON object
			Map<String, Object> responseAsMap = new HashMap<String, Object>();
			// ...
			return new JsonRepresentation(responseAsMap);
			
		}
	
	/*
	 * @Get public String getMessages() { return "Hello Blog!"; }
	 */
	
//	@Get
//	public Representation myGetMethod() {
//		
//		// Create response as a JSON array
//		List<Map<String, Object>> responseArray = new ArrayList<>();
//		// ...
//		responseArray.add(getResponseAttributes());
//		return new JsonRepresentation(new org.json.JSONArray(responseArray));
//	}
		@Get
		public Blog getBlog() {
			Blog blog = new Blog();
			blog.setId(1);
			blog.setTitle("Blog 1");
			return blog;
		} 

}
