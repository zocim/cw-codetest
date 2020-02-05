/*
 *
 * Copyright Farmers WIFE S.L. All Rights Reserved.
 * 
 */

package com.farmerswife.cirkus.codetest.server.guice;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.farmerswife.cirkus.codetest.entities.Blog;
import com.farmerswife.cirkus.codetest.entities.Entry;
import com.farmerswife.cirkus.codetest.entities.User;
import com.github.fakemongo.Fongo;
import com.google.inject.AbstractModule;
import com.mongodb.MongoClient;

public class DatabaseSetupModule extends AbstractModule {
	private static Logger log = LogManager.getLogger();

	@Override
	protected void configure() {
		
		// Mock MongoDB setup
		Fongo fongo = new Fongo("Mock MongoDB");
		MongoClient mongo = fongo.getMongo();
		
		Morphia morphia = new Morphia();
		morphia.mapPackage("com.farmerswife.cirkus.codetest.entities");
		Datastore ds = morphia.createDatastore(mongo, "Codetest");
		ds.ensureIndexes();
		ds.ensureCaps();
		
		bind(Datastore.class).toInstance(ds);
		bind(Morphia.class).toInstance(morphia);
		
		// Setting up database with example data.
		ds.save(new User("John Doe", "john", "1111"));
		ds.save(new User("Jane Peterson", "jane", "2222"));
		ds.save(new User("Frank Westwood", "frank", "3333"));
		
		ds.save(new Blog(1, "John's blog no.1"));
		ds.save(new Blog(2, "John's blog no.2"));
		ds.save(new Blog(3, "Jane's blog no.1"));
		ds.save(new Blog(4, "Jane's blog no.2"));
		
		ds.save(new Entry(1, "John's blog no.1 entry 1", "John: Entry 1"));
		ds.save(new Entry(2, "John's blog no.2 entry 1", "John: Entry 2"));
		ds.save(new Entry(3, "Jane's blog no.1 entry 1", "Jane: Entry 1"));
		ds.save(new Entry(4, "Jane's blog no.2 entry 1", "Jane:Entry 2"));
		log.info("Initialized database");
	}
}
