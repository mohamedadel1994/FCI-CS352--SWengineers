package com.FCI.SWE.Models;
import java.util.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.FCI.SWE.Models.ActiveUser;
import com.FCI.SWE.Models.Post;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

/**
 * 
 * @author MEDO
 *
 */
public class Hashtag {
	Post p;
	public void add (String text){
		int id=0;
		
		ActiveUser User = new ActiveUser();
		String hashtags[]=text.split("#");
		for(int i=1;i<hashtags.length;i++){
			String hashtag[]=hashtags[i].split(" ");				
			int count=1;
			DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
			Query gaeQuery = new Query("Hashtags");
			PreparedQuery pqs = datastore.prepare(gaeQuery);
			for (Entity entity : pqs.asIterable()){
				id++;
				if(entity.getProperty("key").toString().equals (hashtag[0]) ){
				    count=Integer.parseInt(entity.getProperty("count").toString())+1;
					Entity hash = new Entity("Hashtags",id );
					hash.setProperty("key", entity.getProperty("key").toString());
					hash.setProperty("post",entity.getProperty("post").toString());
					hash.setProperty("count", count);
					hash.setProperty("user",entity.getProperty("user").toString());
					datastore.put(hash);
				}
			}
				List<Entity> list = pqs.asList(FetchOptions.Builder.withDefaults());
				Entity hash = new Entity("Hashtags", list.size() + 1);
				hash.setProperty("key", hashtag[0]);
				hash.setProperty("post",text );
				hash.setProperty("user",User.email);
				hash.setProperty("count", count);
				datastore.put(hash);
			
		}
	}
	/**
	 * 
	 * @return
	 */
	public String sort(){
		String hashtag="Trends Hashtag"+"</br>";
		Set<Integer> bo7sen = new HashSet<Integer>();
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();	
		Query gaeQuery = new Query("Hashtags");
		PreparedQuery pqs = datastore.prepare(gaeQuery);
		for (Entity entity : pqs.asIterable()){
			bo7sen.add(Integer.parseInt(entity.getProperty("count").toString()));	
		}
		ArrayList<Integer>mo7sen=new ArrayList<Integer>(bo7sen);
		Collections.sort(mo7sen);

		for(int i=mo7sen.size()-1;i>=0;i--){
			for (Entity entity : pqs.asIterable()){
				if (mo7sen.get(i)==Integer.parseInt(entity.getProperty("count").toString())){
					if (!hashtag.contains(entity.getProperty("key").toString())){
						hashtag+=entity.getProperty("key").toString()+"</br>";
					}
				}
			}
		}
		return hashtag;
	}
	/**
	 * 
	 * @param hash
	 * @return
	 */
	public String search(String hash){
		String posts="";
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();	
		Query gaeQuery = new Query("Hashtags");
		PreparedQuery pqs = datastore.prepare(gaeQuery);
		for (Entity entity : pqs.asIterable()){
			if (hash.equals(entity.getProperty("key").toString())){
				posts+=entity.getProperty("user").toString()+"</br>"+entity.getProperty("post").toString()+"</br>"+"</br>";
			}
		}
		return posts;
		
	}
}