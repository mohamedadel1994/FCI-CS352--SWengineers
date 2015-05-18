package com.FCI.SWE.Models;

import javax.validation.constraints.Null;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
@Test(groups="userentity")
public class UserEntityTest {
	private final LocalServiceTestHelper helper =
		      new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());

		  @BeforeClass
		  public void setUp() {
		    helper.setUp();
		  }

		  @AfterMethod
		  public void tearDown() {
		    helper.tearDown();
		  }

		  UserEntity user=new UserEntity();
		  ActiveUser User=new ActiveUser();
		
		  @DataProvider(name = "test1")
		  public static Object[][]addFriendtest(){
		  	return new Object[][] {{true}};
		  }
		  
		  @Test(dataProvider ="test1")
		  public void addFriendtest(Boolean result  ) {
			  user.email="amir";
			  User.setEmail("mohammed");
			  Assert.assertEquals(result,user.addFriend());
		    
		  }
		  
		  @DataProvider(name = "test2")
		  public static Object[][]checktest(){
			  UserEntity userTest=new UserEntity("amir", "amir", "1");
		  	return new Object[][] {{null,"amir","m"},{userTest,"amir","mohammed"}};
		  }
		  
		  @Test(dataProvider ="test2")
		  public void checktest(UserEntity result ,String email ,String activeEmail) {
			  User.setEmail(activeEmail);
			  Assert.assertEquals(result,user.check(email));
		  }
		  
		  @DataProvider(name = "test3")
		  public static Object[][]checkStatustest(){
			  UserEntity userTest=new UserEntity("4", "amir", "c");
			  	return new Object[][] {{null,"amir","m"},{userTest,"amir","mohammed"}};
		  }
		  
		  @Test(dataProvider ="test3")
		  public void checkStatustest(UserEntity result ,String email ,String activeEmail ) {
			  User.setEmail(activeEmail);
			  Assert.assertEquals(result,user.check(email));
		  }
		  
		  @DataProvider(name = "test4")
		  public static Object[][]conversationtest(){
		  	return new Object[][] {{true,"Hello"}};
		  }
		  
		  @Test(dataProvider ="test4")
		  public void conversationtest(Boolean result ,String msg ) {
		  		User.setEmail("hossam");
		  		user.email="amir";
			  Assert.assertEquals(result,user.conversation(msg));
		  }
		  
		  
		  @DataProvider(name = "test14")
		  public static Object[][]donetest(){
		  	return new Object[][] {{true}};
		  }
		  
		  @Test(dataProvider ="test14")
		  public void donetest(Boolean result ) {
			  user=new UserEntity("4", "amir", "c");
			  User.setEmail("mohamed");
			  Assert.assertEquals(result,user.done());
		    
		  }
		  
		  
		  @DataProvider(name = "test5")
		  public static Object[][]getMessagetest(){
		  	return new Object[][] {{" Sender : amir | MSG : hhhhhhhhhhh| Conversation members :amir/m/hossam","m/hosasm"}};
		  }
		  
		  @Test(dataProvider ="test5")
		  public void getMessagetest(String result ,String email) {
			  Assert.assertEquals(result,user.getMessage(email));
		    
		  }
		  
		  @DataProvider(name = "test6")
		  public static Object[][]getNotificationtest(){
		  	return new Object[][] {};
		  }
		  
		  @Test(dataProvider ="test6")
		  public void getNotificationtest(String result ) {
			  Assert.assertEquals(result,user.getNotification());
		    
		  }
		  
		  @DataProvider(name = "test7")
		  public static Object[][]getRequeststest(){
		  	return new Object[][] {{null,"amir"},{"/mohamed","amir"}};
		  }
		  
		  @Test(dataProvider ="test7")
		  public void getRequeststest(String result ,String email ) {
			  
			  Assert.assertEquals(result,user.getRequests(email));
		  }
		  
		  @DataProvider(name = "test8")
		  public static Object[][]getUserByMailtest(){
			  UserEntity user=new UserEntity("4", "amir", "c");
			  return new Object[][] {{user,"amir"},{null,"ahmed"}};
		  }
		  
		  @Test(dataProvider ="test8")
		  public void getUserByMailtest(UserEntity result ,String email ) {
			  Assert.assertEquals(result,user.getUserByMail(email));
		    
		  }
		  
		  @DataProvider(name = "test9")
		  public static Object[][]liketest(){
		  	return new Object[][] {{true,"1000"},{false,"1"}};
		  }
		  
		  @Test(dataProvider ="test9")
		  public void liketest(Boolean result ,String ID ) {
			  Assert.assertEquals(result,user.like(ID));
		    
		  }
		  
		  @DataProvider(name = "test10")
		  public static Object[][]likePagetest(){
		  	return new Object[][] {{"done","1"},{"Error","1000"}};
		  }
		  
		  @Test(dataProvider ="test10")
		  public void likePagetest(String result ,String ID ) {
			  Assert.assertEquals(result,user.likePage(ID));
		    
		  }
		  
		  @DataProvider(name = "test11")
		  public static Object[][]oneToOnetest(){
		  	return new Object[][] {{true,"Hello"}};
		  }
		  
		  @Test(dataProvider ="test11")
		  public void oneToOnetest(Boolean result ,String msg) {
			  user.email="hossam";
			  User.setEmail("amir");
			  Assert.assertEquals(result,user.oneToOne(msg));
		    
		  }
		  @DataProvider(name = "test12")
		  public static Object[][]saveUsertest(){
		  	return new Object[][] {{true}};
		  }
		  
		  @Test(dataProvider ="test12")
		  public void saveUsertest(Boolean result ) {
			  user = new UserEntity("Eslam", "Eslam", "1");
			  Assert.assertEquals(result,user.saveUser());
		  }
		  
		  @DataProvider(name = "test13")
		  public static Object[][]sharetest(){
		  	return new Object[][] {{"Trends Hashtag</br>zamalek</br>amir</br>mohammed</br>mo7sen</br>3adel</br>","1"},{"","1000"}};
		  }
		  
		  @Test(dataProvider ="test13")
		  public void sharetest(String result ,  String ID) {
			  Assert.assertEquals(result,user.share(ID));
		    
		  }
 			
  
}
