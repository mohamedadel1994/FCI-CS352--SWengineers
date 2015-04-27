package com.FCI.SWE.Services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.mvc.Viewable;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.FCI.SWE.Models.ActiveUser;
import com.FCI.SWE.Models.Hashtag;
import com.FCI.SWE.Models.Page;
import com.FCI.SWE.Models.PageModel;
import com.FCI.SWE.Models.PagePost;
import com.FCI.SWE.Models.Post;
import com.FCI.SWE.Models.PrivacyPost;
import com.FCI.SWE.Models.ProfileTimeLine;
import com.FCI.SWE.Models.PublicTimeLine;
import com.FCI.SWE.Models.Timeline;
import com.FCI.SWE.Models.UserEntity;
import com.FCI.SWE.Models.UserPost;
import com.FCI.SWE.Models.conversationSubject;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

/**
 * This class contains REST services, also contains action function for web
 * application
 * 
 * @author Mohamed Samir
 * @version 1.0
 * @since 2014-02-12
 *
 */
@Path("/")
@Produces("text/html")
public class Service {
	
	
	/*@GET
	@Path("/index")
	public Response index() {
		return Response.ok(new Viewable("/jsp/entryPoint")).build();
	}*/


		/**
	 * Registration Rest service, this service will be called to make
	 * registration. This function will store user data in data store
	 * 
	 * @param uname
	 *            provided user name
	 * @param email
	 *            provided user email
	 * @param pass
	 *            provided password
	 * @return Status json
	 */
	@POST
	@Path("/RegistrationService")
	public String registrationService(@FormParam("uname") String uname,
			@FormParam("email") String email, @FormParam("password") String pass) {
		UserEntity user = new UserEntity(uname, email, pass);
		user.saveUser();
		JSONObject object = new JSONObject();
		object.put("Status", "OK");
		return object.toString();
	}
	
	/**
	 * Login Rest Service, this service will be called to make login process
	 * also will check user data and returns new user from datastore
	 * @param uname provided user name
	 * @param pass provided user password
	 * @return user in json format
	 */
	@POST
	@Path("/LoginService")
	public String loginService(@FormParam("uname") String uname,
			@FormParam("password") String pass) {
		JSONObject object = new JSONObject();
		UserEntity user = UserEntity.getUser(uname, pass);
		if (user == null) {
			object.put("Status", "Failed");

		} else {
			ActiveUser User1=new ActiveUser();
			User1.setEmail(user.getEmail());
			User1.setName(user.getName());
			//System.out.println("currentE"+User1.email);
			//System.out.println("currentN"+User1.name);
			object.put("Status", "OK");
			object.put("name", user.getName());
			object.put("email", user.getEmail());
			object.put("password", user.getPass());
		}

		return object.toString();

	}
/**
 * Search Rest Service, this service will be called to add friend 
 * @param email provided user email
 * @return  Status in json format
 */
	@POST
	@Path("/Search")
	public String search(@FormParam("email") String email) {
		ActiveUser User =new ActiveUser();
		//System.out.println("Entered email:"+email);
		//System.out.println("Active User:"+User.email);
		//ActiveUser User =new ActiveUser();
		JSONObject object = new JSONObject();
		UserEntity user = UserEntity.check(email);
		if (user == null|| User.email.equals(email) ) {
			object.put("Status", "Failed");
		} else {
			object.put("Status", "OK");
			object.put("name", user.getName());
			object.put("email", user.getEmail());
			user.addFriend();
		}
		return object.toString();
	}
	
	/**
	 * AcceptFriend Rest Service, this service will be called to accept new friend 
	 * @param email  provided user email
	 * @return Status in json format
	 */
	@POST
	@Path("/AcceptFriend")
	public String acceptFriend(@FormParam("Email") String email) {
		ActiveUser User =new ActiveUser();
		JSONObject object = new JSONObject();
		UserEntity user = UserEntity.checkStatus(email);
		if (user == null|| User.email.equals(email)  ) {
			object.put("Status", "Failed");

		} else {
			object.put("Status", "Now You and"+email+" are Friends");
			user.done();
		}

		return object.toString();

	}
	/**
	 * 
	 * @param email
	 * @param msg
	 * @return
	 */
	
	@POST
	@Path("/OneToOne")
	public String oneToOne(@FormParam("Email") String email,@FormParam("MSG") String msg) {
		ActiveUser User =new ActiveUser();
		JSONObject object = new JSONObject();
		UserEntity user = UserEntity.getUserByMail(email);
		System.out.println("Email2:"+email);
		if (user == null|| User.email.equals(email)  ) {
			object.put("Status", "Failed");

		} else {
			object.put("Status", "Now your MSG send correctly");
			user.oneToOne(msg);
		}
		return object.toString();
	}
	
	/**
	 * 
	 * @param email
	 * @param msg
	 * @return
	 */
	
	@POST
	@Path("/Conversation")
	public String conversation(@FormParam("Email") String email,@FormParam("MSG") String msg) {
		ActiveUser User =new ActiveUser();
		JSONObject object = new JSONObject();
		UserEntity user = new UserEntity("1", email, "2");
		System.out.println("Email2:"+email);
		String users[]=email.split("/");
		for(int j=0 ; j<users.length;j++){
			UserEntity user2=UserEntity.getUserByMail(users[j]);
			if (user2 == null|| users[j].equals(email)  ) {
				object.put("Status", "Failed");
				return object.toString();
			}
		}
			object.put("Status", "Now your MSG send correctly");
			user.conversation(msg);
		
		return object.toString();
	}
	
	/*
	@POST
	@Path("/Conversation")
	public String conversation(@FormParam("Email") String email,@FormParam("MSG") String msg,@FormParam("convName") String convName) {
		ActiveUser User =new ActiveUser();
		conversationSubject obj = null;
		obj.createConv(User.getEmail() ,msg,convName,email);
		
		/*ActiveUser User =new ActiveUser();
		JSONObject object = new JSONObject();
		UserEntity user = new UserEntity("1", email, "2");
		System.out.println("Email2:"+email);
		String users[]=email.split("/");
		for(int j=0 ; j<users.length;j++){
			UserEntity user2=UserEntity.getUserByMail(users[j]);
			if (user2 == null|| users[j].equals(email)  ) {
				object.put("Status", "Failed");
				return object.toString();
			}
		}
			object.put("Status", "Now your MSG send correctly");
			user.conversation(msg);
		
		JSONObject object = new JSONObject();
		object.put("Status", "Now your MSG send correctly");
		return object.toString();
	}*/
	
	@POST
	@Path("/notification")
	public String notification(@FormParam("Email") String email) {
		ActiveUser User =new ActiveUser();
		JSONObject object = new JSONObject();
		UserEntity user = new UserEntity("1", email, "2");
		boolean check= user.getNotification();
		
		return object.toString();
	}
	
	/**
	 * 
	 * @return
	 */
	@POST
	@Path("/ShowMessage")
	public Response Show() {
		ActiveUser User =new ActiveUser();
		JSONObject object = new JSONObject();
		UserEntity user=new UserEntity("0", User.email, "0");
		String Msg=user.getMessage(User.email);
		if(Msg==""){
			object.put("MSGs", "no Messages");
		}else{
			object.put("MSGs", Msg);
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("MSGs",object.get("MSGs").toString());
		return Response.ok(new Viewable("/jsp/ShowMessage", map)).build();
	}

	/**
	 *  timeline Rest Service, this service will be called to create Post 
	 * @param text  to write post
	 * @param type   type of post
	 * @param emails  users that allow to see post
	 * @return go to page home 
	 */
	@POST
	@Path("/timeline")
	public Response timeLine(@FormParam("text") String text,@FormParam("post") String type,@FormParam("emails") String emails,@FormParam("feeling") String feeling){
		Post post;

		if(type.equals("Public")){
			post=new UserPost();
			String s="feeling "+feeling;
			post.writePost(text,emails,s);
		}else{
			post=new PrivacyPost();
			String s="feeling "+feeling;
			post.writePost(text,emails,s);
		}
	 return Response.ok(new Viewable("/jsp/home")).build();
	}

	/**
	 * getpost Rest Service, this service will be called to  get posts
	 * @param type  type of post
	 * @return  go to timeline page
	 */
	@POST
	@Path("/getpost")
	public Response getPosts(@FormParam("timeline") String type){
		String posts;
		JSONObject object = new JSONObject();
		Timeline timeline;
		if(type.equals("Public")){
			timeline=new PublicTimeLine();
			posts=timeline.getPosts();
		}else{
			timeline=new ProfileTimeLine();
			posts=timeline.getPosts();
		}
		object.put("posts", posts);
		Map<String, String> map = new HashMap<String, String>();
		map.put("posts",object.get("posts").toString());
		return Response.ok(new Viewable("/jsp/timeline", map)).build();
	}
	
	/**
	 * like Rest Service, this service will be called to like post
	 * @param ID provided id of post
	 * @return go to timeline page
	 */
	
	@POST
	@Path("/like")
	public Response like(@FormParam("text") String ID) {
		UserEntity user = new UserEntity();
		user.like( ID);
		return Response.ok(new Viewable("/jsp/timeline")).build();
	}
	
	/**
	 * searchPage Rest Service, this service will be called to search for page
	 * @param name provided name of page
	 * @return  go to searchpage page 
	 */
	@POST
	@Path("/searchPage")
	public Response searchPage(@FormParam("name") String name) {
		JSONObject object = new JSONObject();
		Page page = new Page();
		String p=page.searchPage(name);
		object.put("p",p );
		Map<String, String> map = new HashMap<String, String>();
		map.put("p",object.get("p").toString());
		
		return Response.ok(new Viewable("/jsp/searchPage",map)).build();
	}
	
	/**
	 * likePage Rest Service, this service will be called to like page
	 * @param ID provided id of page
	 * @return  go to timeline page
	 */
	@POST
	@Path("/likePage")
	public Response likePage(@FormParam("text") String ID) {
		UserEntity user = new UserEntity();
		user.likePage( ID);
		return Response.ok(new Viewable("/jsp/timeline")).build();
	}

    /**
     * trends Rest Service, this service will be called to list the trend hashtags
     * @return go to time line page
     */
	@POST
	@Path("/trends")
	public Response trends() {
		
		Hashtag h=new Hashtag();
		JSONObject object = new JSONObject();
		String trends=h.sort();
		object.put("trends",trends );
		Map<String, String> map = new HashMap<String, String>();
		map.put("trends",object.get("trends").toString());
		
		return Response.ok(new Viewable("/jsp/timeline",map)).build();
	}
	
	/**
	 * searchHash Rest Service, this service will be called to search for hashtage
	 * @param hash   provided hash name
	 * @return posts
	 */
	@POST
	@Path("/searchHash")
	public String searchHash(@FormParam("hash") String hash) {
		Hashtag h=new Hashtag();
		String posts=h.search(hash);
		return posts;
		
	}

	/**
	 * createPage Rest Service, this service will be called to create page
	 * @param name provided name of page
	 * @param category provided category name
	 * @param type provided type name
	 * @return done
	 */
	@POST
	@Path("/createPage")
	public String createPage(@FormParam("name") String name,@FormParam("category") String category,@FormParam("type") String type) {
		PageModel page=new PageModel();
		page.createPage(name, type, category);
		return "done";
		
	}

	/**
	 * pagePost Rest Service, this service will be called to write post in page
	 * @param id provided id of post
	 * @return go to mypages
	 */
	@POST
	@Path("/pagePost")
	public Response pagePost(@FormParam("text") String id){
		String posts="";
		ProfileTimeLine ProfileTimeLine1=new ProfileTimeLine();
		posts=ProfileTimeLine1.getpagePosts(id);
		JSONObject object = new JSONObject();
		object.put("posts", posts);
		Map<String, String> map = new HashMap<String, String>();
		map.put("posts",object.get("posts").toString());
		return Response.ok(new Viewable("/jsp/mypages", map)).build();
	}

	/**
	 * mypages Rest Service, this service will be called to page that i create it
	 * @return go to my pages
	 */
	@POST
	@Path("/mypages")
	public Response mypages(){
		Page mypage=new Page();
		String pages=mypage.getpages();
		JSONObject object = new JSONObject();
		object.put("pages", pages);
		Map<String, String> map = new HashMap<String, String>();
		map.put("pages",object.get("pages").toString());
		return Response.ok(new Viewable("/jsp/mypages", map)).build();
	}

	/**
	 * writepagepost Rest Service, this service will be called to write page post 
	 * @param text provided to write post
	 * @param id provided to number of post
	 * @return go to timeline page
	 */
	
	@POST
	@Path("/writepagepost")
	public Response writepagepost
	(@FormParam("text") String text,@FormParam("pageID") String id){
			Post post;
			post=new PagePost();
			post.writePost(text,id,"feeling ");
			
	 return Response.ok(new Viewable("/jsp/timeline")).build();
	}
	
	/**
	 * getUserPost Rest Service, this service will be called to get User Post
	 * @param user 
	 * @return go to userProfile page   
	 */
	@POST
	@Path("/getUserPost")
	public Response getUserPost(@FormParam("user") String user){
		String posts;
		JSONObject object = new JSONObject();
		
		ProfileTimeLine timeline=new ProfileTimeLine();
			posts=timeline.getUserPosts(user);
		
		object.put("posts", posts);
		object.put("email", user);
		Map<String, String> map = new HashMap<String, String>();
		map.put("posts",object.get("posts").toString());
		map.put("email",object.get("email").toString());
		return Response.ok(new Viewable("/jsp/userProfile", map)).build();
	}
	
	/**
	 * profileTimeline Rest Service, this service will be called to write post in friend profile
	 * @param text  provided to write post
	 * @param email provided to write email friend
	 * @param feeling provided to felling you 
	 * @return  go to userProfile page 
 	 */ 
	@POST
	@Path("/profileTimeline")
	public Response timeLine(@FormParam("text") String text,@FormParam("email") String email,@FormParam("feeling") String feeling){
			Post post;
			post=new PrivacyPost();
			String s="feeling "+feeling;
			post.writePost(text,email,s);
		
	 return Response.ok(new Viewable("/jsp/userProfile")).build();
	}

}



/*
@POST
@Path("/Requests")
public String Requests() {
	ActiveUser User=new ActiveUser();
	JSONObject object = new JSONObject();
	UserEntity user=new UserEntity(null, null, null);
	String source[]=user.getRequests(User.email).split("/");
	if (user == null) {
		object.put("Status", "Failed");

	} else {
		for(Integer i=1 ; i<source.length; i++){
			object.put(i.toString(), source[i]);
		}
	}

	return object.toString();

}
*/

