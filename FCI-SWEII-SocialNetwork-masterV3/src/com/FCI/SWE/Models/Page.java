package com.FCI.SWE.Models;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class Page {
	
	public String searchPage(String name){
		int j=0;
		String text="";
		ActiveUser User = new ActiveUser();
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query gaeQueryF = new Query("Page");
		PreparedQuery pqp = datastore.prepare(gaeQueryF);
		for (Entity entity : pqp.asIterable()){
			j++;
			if(entity.getProperty("name").toString().equals(name)){
				text+=entity.getProperty("name").toString()+" <form action=\"/rest/likePage\" method=\"post\"><input type=\"submit\" value=\"Like\">"
						+ "<input type=\"text\" name=\"text\" value="+j+"></form>";
			}
		}
		return text;
	}
	
	public String getpages(){
		String text="";
		int j=0;
		ActiveUser User = new ActiveUser();
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query gaeQueryF = new Query("Page");
		PreparedQuery pqp = datastore.prepare(gaeQueryF);
		for (Entity entity : pqp.asIterable()){
			j++;
			if(entity.getProperty("owner").toString().equals(User.email)){
				text+=entity.getProperty("name").toString()+" <form action=\"/rest/pagePost\" method=\"post\"><input type=\"submit\" value=\"goto\">"
						+ "<input type=\"text\" name=\"text\" value="+j+"></form>";
			}
		}
		return text;
	}

}
