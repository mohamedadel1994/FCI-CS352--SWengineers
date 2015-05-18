package com.FCI.SWE.Services;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.mvc.Viewable;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import com.FCI.SWE.Models.Page;
import com.FCI.SWE.Models.PageModel;
import com.FCI.SWE.Models.PagePost;
import com.FCI.SWE.Models.Post;
import com.FCI.SWE.Models.ProfileTimeLine;
import com.FCI.SWE.Models.UserEntity;

public class PageService {

	
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
		try {
			user.likePage( ID);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Response.ok(new Viewable("/jsp/timeline")).build();
	}
	
	
	/**
	 * pagePost Rest Service, this service will be called to write post in page
	 * @param id provided id of post
	 * @return go to mypages
	 */
	@POST
	@Path("/pagePost")
	public Response createpagePost(@FormParam("text") String id){
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


}

