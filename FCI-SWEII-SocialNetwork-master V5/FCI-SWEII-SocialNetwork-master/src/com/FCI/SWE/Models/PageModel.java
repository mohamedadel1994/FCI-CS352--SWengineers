package com.FCI.SWE.Models;

import java.util.List;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class PageModel {
	public String createPage(String name , String type , String category){
		String test="Good";
		ActiveUser User = new ActiveUser();
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query gaeQueryF = new Query("Page");
		PreparedQuery pqp = datastore.prepare(gaeQueryF);
		List<Entity> list = pqp.asList(FetchOptions.Builder.withDefaults());

		Entity page = new Entity("Page", list.size() + 1);

		page.setProperty("owner", User.email);
		page.setProperty("name", name);
		page.setProperty("type", type);
		page.setProperty("category", category);
		page.setProperty("numberOfLikes", 0);
		page.setProperty("numberOfActiveUsers", 0);
		 
		datastore.put(page);
		return test;
	}
	
	
}
