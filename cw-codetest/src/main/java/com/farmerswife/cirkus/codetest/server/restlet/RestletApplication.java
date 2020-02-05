/*
 *
 * Copyright Farmers WIFE S.L. All Rights Reserved.
 * 
 */

package com.farmerswife.cirkus.codetest.server.restlet;

import org.restlet.Application;
import org.restlet.Context;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.Restlet;
import org.restlet.resource.Finder;
import org.restlet.resource.ServerResource;
import org.restlet.routing.Router;

import com.farmerswife.cirkus.codetest.server.restlet.resources.BlogsResource;
import com.farmerswife.cirkus.codetest.server.restlet.resources.EntriesResource;
import com.farmerswife.cirkus.codetest.server.restlet.resources.MyExampleResource;
import com.farmerswife.cirkus.codetest.server.restlet.resources.UsersResource;
import com.google.inject.Inject;
import com.google.inject.Injector;

public class RestletApplication extends Application {
	@Inject Injector injector;
	
	@Override
	public Restlet createInboundRoot() {
		Router router = new GuiceRouter(injector, getContext());
		
		// Setup routes
		router.attach("/example", MyExampleResource.class);
		router.attach("/users", UsersResource.class);
		router.attach("/blogs", BlogsResource.class);
		router.attach("/blogs/{blogId}", BlogsResource.class);
		router.attach("/blogs/{blogId}/entries", EntriesResource.class);
		router.attach("/blogs/{blogId}/entries/{entryId}", EntriesResource.class);
		// --------------------------------------------
		// ------ Additional routes goes here ---------
		// --------------------------------------------
		
		return router;
	}
	
	// _____________________________________________________________________________

	/**
	 * Custom Restlet Router to enable support for Guice DI application. 
	 */
	class GuiceRouter extends Router {
		private final Injector injector;
		
		public GuiceRouter(Injector injector, Context context) {
	        super(context);
	        this.injector = injector;
	    }	
		
		@Override
		public Finder createFinder(Class<? extends ServerResource> resourceClass) {
			return new GuiceFinder(injector, getContext(), resourceClass);
		}
	}
	
	/**
	 * Custom Restlet Finder to enable support for Guice DI application. 
	 */
	class GuiceFinder extends Finder {
		private final Injector injector;

		GuiceFinder(Injector injector, Context context, Class<? extends ServerResource> targetClass) {
	        super(context, targetClass);
	        this.injector = injector;
	    }
		
		@Override
		public ServerResource create(Class<? extends ServerResource> target, Request request, Response response) {
			return injector.getInstance(target);
		}
	}
}
