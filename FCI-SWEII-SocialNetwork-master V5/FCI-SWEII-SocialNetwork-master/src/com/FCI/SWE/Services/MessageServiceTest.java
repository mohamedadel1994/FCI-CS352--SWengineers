package com.FCI.SWE.Services;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.FCI.SWE.Models.ActiveUser;

public class MessageServiceTest {

  private static final String MessageService = null;

  MessageService messageservice =new MessageService();
  @DataProvider(name = "test1")
  public static Object[][]Showtest(){
  	return new Object[][] {{"",}};
  }
  
 // @Test(dataProvider ="test1" , dependsOnMethods = { "getMessagetest()" },dependsOnGroups={"userentity"})
 @Test(dataProvider ="test1" ,dependsOnGroups={"userentity"})
  public void ShowTest(String result  ) {
	  Assert.assertEquals(result,messageservice.Show());
  }
  @DataProvider(name = "test3")
  public static Object[][]conversationtest(){
	  JSONObject object = new JSONObject();
	  JSONObject object1 = new JSONObject();
	  object.put("Status", "Failed");
	  object1.put("Status", "Now your MSG send correctly");
  	return new Object[][] {{object.toString(),"m","DDD"},{object1.toString(),"amir/hossam","sss"}};
  }
  
  @Test(dataProvider ="test3",dependsOnGroups={"userentity"})
  public void conversationTest(String result ,String email ,String msg ) {
	  ActiveUser user=new ActiveUser();
	  user.setEmail("m");
	 
  
	  Assert.assertEquals(result,messageservice.conversation(email, msg));
  }

  @DataProvider(name = "test12")
  public static Object[][]oneToOnetest(){
  	return new Object[][] {};
  }
  
  @Test(dataProvider ="test12",dependsOnGroups={"userentity"})
  public void oneToOneTest(String result ,String email ,String msg  ) {
	  Assert.assertEquals(result,messageservice.oneToOne(email, msg));
  }
}
