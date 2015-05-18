package com.FCI.SWE.Models;

import java.util.List;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class conversationObserver implements Observer{

	private String email;
	
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmail() {
		return email;
	}
	
	@Override
	public void update(String convName, String sender,String msg) {
		ActiveUser User=new ActiveUser();
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query gaeQuery = new Query("Message");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());

		Entity employee = new Entity("Message", list.size() + 1);
		String arr[]=this.email.split("/");
		String seen="no";
		for (int i=1;i<arr.length;i++){
			seen+="/no";
		}
		employee.setProperty("sender", User.email);
		employee.setProperty("receiver", this.email);
		employee.setProperty("msg", msg);
		employee.setProperty("seen", "no");
		employee.setProperty("conversationName", convName);
		datastore.put(employee);
	}

}
