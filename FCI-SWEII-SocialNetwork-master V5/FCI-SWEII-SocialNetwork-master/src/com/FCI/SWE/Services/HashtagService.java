package com.FCI.SWE.Services;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.mvc.Viewable;
import org.json.simple.JSONObject;

import com.FCI.SWE.Models.Hashtag;

public class HashtagService {

	
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
	
	
	

}
