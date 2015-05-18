package com.FCI.SWE.Models;

import org.testng.annotations.Test;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
/**
 * 
 * @author MEDO
 *
 */

public class PublicTimeLine extends Timeline{

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
			String emails[]=entity.getProperty("People").toString().split("/");
			for(int i=0;i<emails.length;i++){
				if(emails[i].equals(User.email)){
					text+=entity.getProperty("User").toString()+" : "+entity.getProperty("feeling").toString()+"</br>"+entity.getProperty("Text").toString()+"</br>"+entity.getProperty("Likes").toString()+" <form action=\"/rest/like\" method=\"post\"><input type=\"submit\" value=\"Like\">"
							+ "<input type=\"hidden\" name=\"text\" value="+j+"></form>"+" <form action=\"/rest/share\" method=\"post\"><input type=\"submit\" value=\"Share\">"
									+ "<input type=\"hidden\" name=\"text\" value="+j+"></form>";
				}
			}
		}
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>"+text);
		return text;
	}

}
