package com.FCI.SWE.Models;

import java.util.Date;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

/**
 * <h1>User Entity class</h1>
 * <p>
 * This class will act as a model for user, it will holds user data
 * </p>
 *
 * @author Mohamed Samir
 * @version 1.0
 * @since 2014-02-12
 */

public class UserEntity {
	public String name;
	public String email;
	public String password;

	/**
	 * Constructor accepts user data
	 * 
	 * @param name
	 *            user name
	 * @param email
	 *            user email
	 * @param password
	 *            user provided password
	 */
	public UserEntity() {
		// TODO Auto-generated constructor stub
	}
	public UserEntity(String name, String email, String password) {
		this.name = name;
		this.email = email;
		this.password = password;

	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPass() {
		return password;
	}

	/**
	 * 
	 * This static method will form UserEntity class using json format contains
	 * user data
	 * 
	 * @param json
	 *            String in json format contains user data
	 * @return Constructed user entity
	 */
	public static UserEntity getUser(String json) {

		JSONParser parser = new JSONParser();
		try {
			JSONObject object = (JSONObject) parser.parse(json);
			return new UserEntity(object.get("name").toString(), object.get(
					"email").toString(), object.get("password").toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * 
	 * This static method will form UserEntity class using user name and
	 * password This method will serach for user in datastore
	 * 
	 * @param name
	 *            user name
	 * @param pass
	 *            user password
	 * @return Constructed user entity
	 */

	public static UserEntity getUser(String name, String pass) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query gaeQuery = new Query("users");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for (Entity entity : pq.asIterable()) {
			//System.out.println(entity.getProperty("name").toString());
			
			if (entity.getProperty("name").toString().equals(name)
					&& entity.getProperty("password").toString().equals(pass)) {
				UserEntity returnedUser = new UserEntity(entity.getProperty(
						"name").toString(), entity.getProperty("email")
						.toString(), entity.getProperty("password").toString());
				return returnedUser;
			}
		}

		return null;
	}
	
	public static UserEntity getUserByMail(String email) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query gaeQuery = new Query("users");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for (Entity entity : pq.asIterable()) {
			//System.out.println(entity.getProperty("name").toString());
			
			if (entity.getProperty("email").toString().equals(email)) {
				UserEntity returnedUser = new UserEntity("1", entity.getProperty("email")
						.toString(),"2");
				System.out.println("Email:"+entity.getProperty("email").toString());
				return returnedUser;
			}
		}

		return null;
	}
	/**
	 * 
	 * This method will form UserEntity class using  email and check in database
	 * if this email sent request to the current user or the current user sent to this email
	 * and check the validation of this email
	 * 
	 * @param email
	 * 			friend email
	 * @return Constructed user entity
	 * 
	 */
	public static UserEntity check(String email) {
		ActiveUser User=new ActiveUser();
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query gaeQueryF = new Query("FriendRequest");
		Query gaeQuery = new Query("users");
		PreparedQuery pqF = datastore.prepare(gaeQueryF);
		PreparedQuery pq = datastore.prepare(gaeQuery);
		Boolean check=true;
		
		for(Entity entityF : pqF.asIterable()){
			//System.out.println("destination"+entityF.getProperty("destination").toString());
			if(entityF.getProperty("destination").toString().equals(email)&&entityF.getProperty("source").toString().equals(User.email) || entityF.getProperty("source").toString().equals(email)&&entityF.getProperty("destination").toString().equals(User.email) ){
				check=false;
				}
		}
	if(check){
		for (Entity entity : pq.asIterable()) {
			//System.out.println("email"+entity.getProperty("email").toString());
			if (entity.getProperty("email").toString().equals(email)) {
				
				UserEntity returnedUser = new UserEntity(entity.getProperty(
						"name").toString(), entity.getProperty("email")
						.toString(), entity.getProperty("password").toString());
				//System.out.println("Success");
				return returnedUser;
			  }
			}
		}
		return null;
		
	}
	/**
	 * 
	 * This method will form UserEntity class using  email and check in database
	 * if this email sent request to the current user and the status is pending
	 * if true return the index of this row in the database 
	 * and check the validation of this email
	 * 
	 *@param email
	 * 			friend email
	 * @return Constructed user entity
	 */
	public static UserEntity checkStatus(String email) {
		ActiveUser User=new ActiveUser();
		UserEntity returnedUser=new UserEntity("4", "b", "c");
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query gaeQueryF = new Query("FriendRequest");
		PreparedQuery pqF = datastore.prepare(gaeQueryF);
		Boolean check=true;
		int i=0;
		for(Entity entityF : pqF.asIterable()){
			i++;
			//System.out.println("destination"+entityF.getProperty("destination").toString()+"/"+"Source"+entityF.getProperty("source").toString()+"/"+"status"+entityF.getProperty("status").toString());
			if(entityF.getProperty("source").toString().equals(email)&&entityF.getProperty("destination").toString().equals(User.email)&&entityF.getProperty("status").toString().equals("Pending") ){
			
				returnedUser = new UserEntity(""+i, email, "c");
				check=true;
				break;
			}else{
				check=false;
			}
		}
		
		if (check==false){
			return null;	
		}else{
			return returnedUser;
		}
		
		
	}
	
	
	public static String getRequests(String email) {
		String req="";
		Boolean check=true;
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query gaeQuery = new Query("FriendRequest");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for (Entity entity : pq.asIterable()) {
			//System.out.println(entity.getProperty("name").toString());
			if (entity.getProperty("destination").toString().equals(email)) {
				
				String source=entity.getProperty("source").toString();
				//System.out.println("source"+entity.getProperty("source").toString());
				req=req+"/"+source;
				//System.out.println("req:"+req);
				check=false;
			}
		}
		
		if(check==false){
			return req;
		}
		return null;
	}
	

	/**
	 * This method will be used to save user object in datastore
	 * 
	 * @return boolean if user is saved correctly or not
	 */
	public Boolean saveUser() {
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query gaeQuery = new Query("users");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());

		Entity employee = new Entity("users", list.size() + 1);

		employee.setProperty("name", this.name);
		employee.setProperty("email", this.email);
		employee.setProperty("password", this.password);
		datastore.put(employee);

		return true;

	}
	/**
	 * 
	 * This method will form UserEntity class using source email , destination email and status 
	 * and put new record in the database
	 *@param 
	 * 			
	 * @return true
	 */
	public Boolean addFriend() {
		ActiveUser User=new ActiveUser();
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query gaeQuery = new Query("FriendRequest");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());

		Entity employee = new Entity("FriendRequest", list.size() + 1);

		employee.setProperty("source", User.email);
		employee.setProperty("destination", this.email);
		employee.setProperty("status", "Pending");
		datastore.put(employee);
		
		return true;
	}
	/**
	 * 
	 * This method will form UserEntity class using source email , destination email  
	 * and change the status to Accepted
	 *@param 
	 * 			
	 * @return true
	 */
	public Boolean done() {
		ActiveUser User=new ActiveUser();
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query gaeQuery = new Query("FriendRequest");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());
		int id=Integer.parseInt(this.name);
		//System.out.println("ID"+id);
		Entity employee = new Entity("FriendRequest",id);

		employee.setProperty("source", User.email);
		employee.setProperty("destination", this.email);
		employee.setProperty("status", "Accepted");
		datastore.put(employee);
		
		return true;
	}
	/**
	 * 
	 * @param msg
	 * @return
	 */
	public Boolean oneToOne(String msg) {
		ActiveUser User=new ActiveUser();
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query gaeQuery = new Query("Message");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());

		Entity employee = new Entity("Message", list.size() + 1);

		employee.setProperty("sender", User.email);
		employee.setProperty("receiver", this.email);
		employee.setProperty("msg", msg);
		employee.setProperty("seen", "no");
		datastore.put(employee);
		
		return true;
	}
	
	public Boolean conversation(String msg) {
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
		employee.setProperty("seen", seen);
		datastore.put(employee);
		
		return true;
	}
	 
	public Boolean  getNotification() {
		ActiveUser User=new ActiveUser();
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query gaeQuery = new Query("Message");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for (Entity entity : pq.asIterable()) {
			if (entity.getProperty("receiver").toString().equals(email)&&entity.getProperty("seen").toString().equals("no")){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 
	 * @param email
	 * @return
	 */
	public String getMessage(String email) {
		String msg="";
		int i=0;
		ActiveUser User=new ActiveUser();
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query gaeQuery = new Query("Message");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for (Entity entity : pq.asIterable()) {
			i++;
			String rec[]=entity.getProperty("receiver").toString().split("/");
			String seen[]=entity.getProperty("seen").toString().split("/");
			String seen2="";
			if(rec.length==1){
				if (entity.getProperty("receiver").toString().equals(email)&&entity.getProperty("seen").toString().equals("no")) {
					msg+=(" Sender : "+entity.getProperty("sender").toString()+" | Msg : "+entity.getProperty("msg").toString()+"<br>") ;
					Entity Msg = new Entity("Message",i);
					Msg.setProperty("sender", entity.getProperty("sender").toString());
					Msg.setProperty("receiver", User.email);
					Msg.setProperty("msg", entity.getProperty("msg").toString());
					Msg.setProperty("seen", "yes");
					datastore.put(Msg);
				}
			}else if (rec.length>1){
				for(int j=0 ; j<rec.length;j++){
					if (rec[j].equals(email)&&seen[j].equals("no")){
						seen[j]="yes";
						for(int k=0;k<seen.length;k++){
							seen2+=seen[k]+"/";
						}
						msg+=(" Sender : "+entity.getProperty("sender").toString()+" | Msg : "+entity.getProperty("msg").toString()
						+"| Conversation members :" +entity.getProperty("sender").toString()+"/"+entity.getProperty("receiver").toString()+"<br>") ;
						Entity Msg = new Entity("Message",i);
						Msg.setProperty("sender", entity.getProperty("sender").toString());
						Msg.setProperty("receiver", entity.getProperty("receiver").toString());
						Msg.setProperty("msg", entity.getProperty("msg").toString());
						Msg.setProperty("seen", seen2);
						datastore.put(Msg);
					}
				}
			}
		}		
		return msg;
	}
	
	public Boolean like(String ID){
		int id=Integer.parseInt(ID);
		int j=0;
		Boolean check=true;
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query gaeQueryF = new Query("Post");
		PreparedQuery pq = datastore.prepare(gaeQueryF);
		
		for (Entity entity : pq.asIterable()){
			j++;
			if (j==id){
				String like=entity.getProperty("Likes").toString();
				int likes=Integer.parseInt(like);
				likes=likes+1;
				
				Entity posts = new Entity("Post", j);

				posts.setProperty("User", entity.getProperty("User").toString());
				posts.setProperty("Text", entity.getProperty("Text").toString());
				posts.setProperty("People", entity.getProperty("People").toString()); 
				posts.setProperty("Likes", likes);				 
				posts.setProperty("feeling", entity.getProperty("feeling").toString());			
				datastore.put(posts);
				check=false;
			}
		}
		return check;
	}
	
	public String share(String ID){
		int id=Integer.parseInt(ID);
		int j=0;
		String content="";
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query gaeQueryF = new Query("Post");
		PreparedQuery pq = datastore.prepare(gaeQueryF);
		
		for (Entity entity : pq.asIterable()){
			j++;
			if (j==id){
				content+=entity.getProperty("Text").toString()+"/"+"Shared "+entity.getProperty("User").toString()+"'s post :   "+entity.getProperty("feeling").toString();
				break;
			}
			
		}
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>"+content);
		return content;
	}
	
	public String likePage(String ID){
		ActiveUser user=new ActiveUser();
		int id=Integer.parseInt(ID);
		int j=0;
		String check="Error";
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query gaeQueryF = new Query("Page");
		PreparedQuery pq = datastore.prepare(gaeQueryF);
		
		for (Entity entity : pq.asIterable()){
			j++;
			if (j==id){
				String like=entity.getProperty("numberOfLikes").toString();
				int likes=Integer.parseInt(like);
				likes=likes+1;
				
				
				
				Entity page = new Entity("Page", j);
				page.setProperty("owner", entity.getProperty("owner").toString());
				page.setProperty("type", entity.getProperty("type").toString());
				page.setProperty("category", entity.getProperty("category").toString());
				page.setProperty("name", entity.getProperty("name").toString());
				page.setProperty("numberOfActiveUsers", entity.getProperty("numberOfActiveUsers").toString());
				page.setProperty("numberOfLikes", likes);	
				
				datastore.put(page);
				
				DatastoreService datastore2 = DatastoreServiceFactory.getDatastoreService();
				Query gaeQueryFd = new Query("likedPages");
				PreparedQuery pqa = datastore2.prepare(gaeQueryFd);
				List<Entity> list = pqa.asList(FetchOptions.Builder.withDefaults());
				Entity posts = new Entity("likedPages", list.size() + 1);
				posts.setProperty("User", user.email);
				posts.setProperty("pageID", id);
				datastore2.put(posts);
				
				check="done";
			}
		}
		return check;
	}
 }



