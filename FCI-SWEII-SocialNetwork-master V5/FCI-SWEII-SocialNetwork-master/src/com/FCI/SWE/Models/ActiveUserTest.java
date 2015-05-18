package com.FCI.SWE.Models;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test(groups="activeUser")
public class ActiveUserTest {
	ActiveUser activeuser =new ActiveUser();

  @DataProvider(name = "test1")
  public static Object[][]getNameTest(){
  	return new Object[][] { {"amir",""} };
  }
  
  @Test(dataProvider ="test1")
  public void getNameTest(String result , String Name ) {
	  activeuser.setName("amir");
	  Assert.assertEquals(result,activeuser.getName());
  }
  
  @DataProvider(name = "test2")
  public static Object[][]getEmailTest(){
  	return new Object[][] {{"amir","amir"}};
  }
  
  @Test(dataProvider ="test2")
  public void getEmailTest(String result , String Email ) {
	  activeuser.setEmail(Email);
	  Assert.assertEquals(result,activeuser.getEmail());
  }
   
  
  @DataProvider(name = "test3")
  public static Object[][]getPass(){
  	return new Object[][] {{"123","123"}};
  }
  
  @Test(dataProvider ="test3")
  public void getPass(String result , String pass ) {
	  activeuser.password=pass;
	  Assert.assertEquals(result,activeuser.getPass());
  }
  
  @DataProvider(name = "test4")
  public static Object[][]signOut(){
  	return new Object[][] {{null,"amir","amir"}};
  }
  
  @Test(dataProvider ="test4")
  public void signOut(String result , String email,String name ) {
	  activeuser.setEmail(email);
	  activeuser.setName(name);
	  Assert.assertEquals(result,activeuser.signOut());
  }
  
}
