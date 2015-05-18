package com.FCI.SWE.Models;

import java.util.List;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class publicPost extends Post {
	public String writePost(String text,String emails,String feeling) {
		Hashtag h=new Hashtag();
		h.add(text);
	    emails="";
		ActiveUser User = new ActiveUser();
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query gaeQueryF = new Query("users");
		PreparedQuery pq = datastore.prepare(gaeQueryF);
		for (Entity entity : pq.asIterable()){
				emails+=entity.getProperty("email").toString()+"/";
		}
		Query gaeQuery = new Query("Post");
		PreparedQuery pqp = datastore.prepare(gaeQuery);
		List<Entity> list = pqp.asList(FetchOptions.Builder.withDefaults());

		Entity posts = new Entity("Post", list.size() + 1);

		posts.setProperty("User", User.email);
		posts.setProperty("feeling", feeling);
		posts.setProperty("Text", text);
		posts.setProperty("People", emails);
		posts.setProperty("Likes", 0);
		 
		datastore.put(posts);
		return "done";
	}

}
