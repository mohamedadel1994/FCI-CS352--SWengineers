package com.FCI.SWE.Models;

import java.util.ArrayList;

public abstract class  Post {
	public ArrayList<UserEntity> currentAllowed=new 	ArrayList <UserEntity>();
	public abstract String writePost(String text,String emails,String feeling);
}
