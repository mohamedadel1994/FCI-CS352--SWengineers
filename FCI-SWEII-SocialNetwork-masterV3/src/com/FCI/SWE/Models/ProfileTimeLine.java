package com.FCI.SWE.Models;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class ProfileTimeLine extends Timeline{

		
	public ProfileTimeLine() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String getPosts() {
		String text="";
		int j=0;
		ActiveUser User = new ActiveUser();
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query gaeQueryF = new Query("Post");
		PreparedQuery pq = datastore.prepare(gaeQueryF);
		for (Entity entity : pq.asIterable()){
			j++;
				if(entity.getProperty("User").toString().equals(User.email)){
					text+=entity.getProperty("User").toString()+" : "+entity.getProperty("feeling").toString()+"</br>"+entity.getProperty("Text").toString()+"</br>"+entity.getProperty("Likes").toString()+" <form action=\"/rest/like\" method=\"post\"><input type=\"submit\" value=\"Like\">"
							+ "<input type=\"hidden\" name=\"text\" value="+j+"></form>";
				}
		}
		return text;
		
	}
	
	public String getpagePosts(String ID) {
		int id=Integer.parseInt(ID);
		int i=0;
		String pageName="";
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query gaeQueryFs = new Query("Page");
		PreparedQuery pqa = datastore.prepare(gaeQueryFs);
		for (Entity entity : pqa.asIterable()){
			i++;
			if(i==id ){
				pageName=entity.getProperty("name").toString();
				break;
			}
		}
		
		String text="<form action=\"/rest/writepagepost\" method=\"post\">\n" +
					"<input type=\"text\" name=\"text\" /><br>\n" +
					"<input type=\"text\" name=\"pageID\" value="+id+"><br>\n" +
					"<input type=\"submit\" value=\"post\"></form>";
		int j=0;
		ActiveUser User = new ActiveUser();
		Query gaeQueryF = new Query("Post");
		PreparedQuery pq = datastore.prepare(gaeQueryF);
		for (Entity entity : pq.asIterable()){
			j++;
				if(entity.getProperty("User").toString().equals(pageName)){
					text+=entity.getProperty("User").toString()+" : "+entity.getProperty("Text").toString()+"</br>"+entity.getProperty("Likes").toString()+" <form action=\"/rest/like\" method=\"post\"><input type=\"submit\" value=\"Like\">"
							+ "<input type=\"hidden\" name=\"text\" value="+j+"></form>";
				}
		}
		return text;
		
	}
	
	
	public String getUserPosts(String user) {
		String text="";
		int j=0;
		String emails[];
		ActiveUser User = new ActiveUser();
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query gaeQueryF = new Query("Post");
		PreparedQuery pq = datastore.prepare(gaeQueryF);
		for (Entity entity : pq.asIterable()){
			j++;
			emails=entity.getProperty("People").toString().split("/");
			for(int i=0;i<emails.length;i++){
			if(entity.getProperty("User").toString().equals(user) && emails[i].equals(User.email)){
				text+=entity.getProperty("User").toString()+" : "+entity.getProperty("feeling").toString()+"</br>"+entity.getProperty("Text").toString()+"</br>"+entity.getProperty("Likes").toString()+" <form action=\"/rest/like\" method=\"post\"><input type=\"submit\" value=\"Like\">"
					+ "<input type=\"hidden\" name=\"text\" value="+j+"></form>";
				}
			}
		}
		return text;
		
	}	

}
