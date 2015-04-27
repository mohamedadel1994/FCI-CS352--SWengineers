package com.FCI.SWE.Models;

import java.util.List;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class PagePost extends Post{

	@Override
	public void writePost(String text,String ID,String feeling) {
		// TODO Auto-generated method stub
		Hashtag h=new Hashtag();
		h.add(text);
		String emails="";
	    int id=Integer.parseInt(ID);
		ActiveUser User = new ActiveUser();
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query gaeQueryF = new Query("likedPages");
		PreparedQuery pq = datastore.prepare(gaeQueryF);
		for (Entity entity : pq.asIterable()){
			if(entity.getProperty("pageID").toString().equals(ID) ){
				emails+=entity.getProperty("User").toString()+"/";
			}
		}
		
		int i=0;
		String pageName="";
		DatastoreService datastore2 = DatastoreServiceFactory.getDatastoreService();
		Query gaeQueryFs = new Query("Page");
		PreparedQuery pqa = datastore2.prepare(gaeQueryFs);
		for (Entity entity : pqa.asIterable()){
			i++;
			if(i==id ){
				pageName=entity.getProperty("name").toString();
				break;
			}
		}
		

		Query gaeQuery = new Query("Post");
		PreparedQuery pqp = datastore.prepare(gaeQuery);
		List<Entity> list = pqp.asList(FetchOptions.Builder.withDefaults());

		Entity posts = new Entity("Post", list.size() + 1);

		posts.setProperty("User", pageName);
		posts.setProperty("feeling", feeling);
		posts.setProperty("Text", text);
		posts.setProperty("People", emails);
		posts.setProperty("Likes", 0);
		 
		datastore.put(posts);
		
	
		
	}

}
