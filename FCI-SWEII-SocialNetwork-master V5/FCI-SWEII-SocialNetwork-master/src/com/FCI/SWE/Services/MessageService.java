package com.FCI.SWE.Services;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.mvc.Viewable;
import org.json.simple.JSONObject;

import com.FCI.SWE.Models.ActiveUser;
import com.FCI.SWE.Models.UserEntity;

public class MessageService {

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
	
	
}
