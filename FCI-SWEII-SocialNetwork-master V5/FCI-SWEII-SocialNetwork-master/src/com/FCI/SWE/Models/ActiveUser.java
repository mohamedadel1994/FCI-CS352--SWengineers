package com.FCI.SWE.Models;

public class ActiveUser {

	public static String name;
	public static String email;
	public String password;
	
	
	 public static String  signOut(){
		 email=null;
		 name=null;
		 return email;
	 }
	 
	public String getName() {
		return name;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPass() {
		return password;
	}
	
	public String getEmail() {
		return email;
	}

}
