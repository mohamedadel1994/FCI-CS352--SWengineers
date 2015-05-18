package com.FCI.SWE.Models;

import java.util.ArrayList;
import java.util.List;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class conversationSubject implements Subject{
	private conversationObserver obj;
	private ArrayList<Observer> observers=new 	ArrayList <Observer>();
	
	public void createConv(String sender , String msg , String convName,String receivers){
		Boolean check=true;
		String users[]=receivers.split("/");
		for(int j=0 ; j<users.length;j++){
			UserEntity user2=UserEntity.getUserByMail(users[j]);
			if (user2 != null|| !users[j].equals(receivers)  ) {
				obj.setEmail(users[j]);
				attach(obj);
			}
		}
	
		ActiveUser User=new ActiveUser();
		String members=User.getEmail()+"/"+receivers;
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query gaeQuery = new Query("conversation");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		//for (Entity entity : pq.asIterable()) {
		//	if (entity.getProperty("convName").toString().equals(convName)){
		//		check=false;
		//	}
		//}
		
		if(check==true){
			List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());
			Entity employee = new Entity("conversation", list.size() + 1);
			employee.setProperty("convName", convName);
			employee.setProperty("members", members);
			datastore.put(employee);
		}
		Notify(convName, sender,msg);
		
	}
	
	
	@Override
	public void attach(Observer obs) {
		// TODO Auto-generated method stub
		 observers.add(obs);
		
	}

	@Override
	public void Notify(String convName, String sender,String msg) {
		// TODO Auto-generated method stub
		 for (int i=0; i < observers.size(); i++) {
		      observers.get(i).update(convName,sender,msg);
		    }
		
	}

}
