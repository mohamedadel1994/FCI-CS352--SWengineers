package com.FCI.SWE.Services;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.FCI.SWE.Models.ActiveUser;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalMemcacheServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.google.appengine.tools.development.testing.LocalTaskQueueTestConfig;

public class ServiceTest {
	private final LocalServiceTestHelper helper =
		      new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig(),
		    		                     new LocalMemcacheServiceTestConfig()  ,new LocalTaskQueueTestConfig()    );

		  @BeforeMethod
		  public void setUp() {
		    helper.setUp();
		  }

		  @AfterMethod
		  public void tearDown() {
		    helper.tearDown();
		  }
		  
		 
		  Service service =new Service();
		 
		  
		  @DataProvider(name = "test1")
		  public static Object[][]Showtest(){
		  	return new Object[][] {{"",}};
		  }
		  
		 // @Test(dataProvider ="test1" , dependsOnMethods = { "getMessagetest()" },dependsOnGroups={"userentity"})
		 @Test(dataProvider ="test1" ,dependsOnGroups={"userentity"})
		  public void ShowTest(String result  ) {
			  Assert.assertEquals(result,service.Show());
		  }
		
		  @DataProvider(name = "test2")
		  public static Object[][]acceptFriendtest(){  
			  JSONObject object = new JSONObject();
			  JSONObject object1 = new JSONObject();
			  object.put("Status", "Failed");
			  object1.put("Status", "Now You andamir are Friends");
		  	return new Object[][] {{object1.toString(),"amir"},{object.toString(),"m"}};
		  }
		  
		  @Test(dataProvider ="test2",dependsOnGroups={"userentity"})
		  public void acceptFriendTest(String result ,String email  ) {
			  ActiveUser user=new ActiveUser();
			  user.setEmail("mohamed");
			  Assert.assertEquals(result,service.acceptFriend(email));
			  
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
			  Assert.assertEquals(result,service.conversation(email, msg));
		  }
		  @DataProvider(name = "test4")
		  public static Object[][]createPagetest(){
		  	return new Object[][] {{"done","zamalek","sports","football"}};
		  }
		  
		  @Test(dataProvider ="test4",dependsOnGroups={"PageModelTest"})
		  public void createPageTest(String result ,String name ,String category ,String type ) {
			  Assert.assertEquals(result,service.createPage(name, category, type));
		  }
		  
		  @DataProvider(name = "test5")
		  public static Object[][]getPoststest(){
		  	return new Object[][] {{"amir : feeling good</br>sddddddddddddd</br>0 <form action=\"/rest/like\" method=\"post\"><input type=\"submit\" value=\"Like\"><input type=\"hidden\" name=\"text\" value=1></form> <form action=\"/rest/share\" method=\"post\"><input type=\"submit\" value=\"Share\"><input type=\"hidden\" name=\"text\" value=1></form>amir : feeling jjj</br>asdasd#zamalek adsd</br>0 <form action=\"/rest/like\" method=\"post\"><input type=\"submit\" value=\"Like\"><input type=\"hidden\" name=\"text\" value=3></form> <form action=\"/rest/share\" method=\"post\"><input type=\"submit\" value=\"Share\"><input type=\"hidden\" name=\"text\" value=3></form>m : feeling ww</br>sdfsdf#amir asdasd</br>0 <form action=\"/rest/like\" method=\"post\"><input type=\"submit\" value=\"Like\"><input type=\"hidden\" name=\"text\" value=4></form> <form action=\"/rest/share\" method=\"post\"><input type=\"submit\" value=\"Share\"><input type=\"hidden\" name=\"text\" value=4></form>m : feeling a</br>#mohammed asdasd</br>0 <form action=\"/rest/like\" method=\"post\"><input type=\"submit\" value=\"Like\"><input type=\"hidden\" name=\"text\" value=5></form> <form action=\"/rest/share\" method=\"post\"><input type=\"submit\" value=\"Share\"><input type=\"hidden\" name=\"text\" value=5></form>m : feeling ddd</br>mohamed #mo7sen abo gresha</br>0 <form action=\"/rest/like\" method=\"post\"><input type=\"submit\" value=\"Like\"><input type=\"hidden\" name=\"text\" value=6></form> <form action=\"/rest/share\" method=\"post\"><input type=\"submit\" value=\"Share\"><input type=\"hidden\" name=\"text\" value=6></form>m : feeling dd</br>#mohamed #3adel</br>0 <form action=\"/rest/like\" method=\"post\"><input type=\"submit\" value=\"Like\"><input type=\"hidden\" name=\"text\" value=7></form> <form action=\"/rest/share\" method=\"post\"><input type=\"submit\" value=\"Share\"><input type=\"hidden\" name=\"text\" value=7></form>m : Shared amir's post :   feeling good</br>sddddddddddddd</br>0 <form action=\"/rest/like\" method=\"post\"><input type=\"submit\" value=\"Like\"><input type=\"hidden\" name=\"text\" value=8></form> <form action=\"/rest/share\" method=\"post\"><input type=\"submit\" value=\"Share\"><input type=\"hidden\" name=\"text\" value=8></form>","Public"}};
		  }
		  
		  
		  @Test(dataProvider ="test5", dependsOnGroups={"publictimeline","profiletimeline"} )
		  public void getPostsTest(String result ,String type ) {
			  ActiveUser user=new ActiveUser();
			  user.setEmail("amir");
			  Assert.assertEquals(result,service.getPosts(type));
		  }
		  
		  
		  @DataProvider(name = "test6")
		  public static Object[][]getUserPosttest(){
		  	return new Object[][] {};
		  }
		  
		  @Test(dataProvider ="test6", dependsOnGroups={"profiletimeline"})
		  public void getUserPostTest(String result ,String user ) {
			  Assert.assertEquals(result,service.getUserPost(user));
		  }
		  
		  
		  @DataProvider(name = "test7")
		  public static Object[][]liketest(){
		  	return new Object[][] {};
		  }
		  
		  @Test(dataProvider ="test7",dependsOnGroups={"userentity"})
		  public void likeTest(String result ,String ID ) {
			  Assert.assertEquals(result,service.like(ID));
		  }
		  @DataProvider(name = "test8")
		  public static Object[][]likePagetest(){
		  	return new Object[][] {};
		  }
		  
		  @Test(dataProvider ="test8",dependsOnGroups={"userentity"})
		  public void likePageTest(String result ,String ID ) {
			  Assert.assertEquals(result,service.likePage(ID));
		  }
		  @DataProvider(name = "test9")
		  public static Object[][]loginServicetest(){
		  	return new Object[][] {};
		  }
		  
		  @Test(dataProvider ="test9",dependsOnGroups={"userentity"})
		  public void loginServiceTest(String result ,String uname,String pass ) {
			  Assert.assertEquals(result,service.loginService(uname, pass));
		  }
		  @DataProvider(name = "test10")
		  public static Object[][]mypagestest(){
		  	return new Object[][] {};
		  }
		  
		  @Test(dataProvider ="test10",dependsOnGroups={"pagetest"})
		  public void mypagesTest(String result  ) {
			  Assert.assertEquals(result,service.mypages());
		  }
		
		  @DataProvider(name = "test12")
		  public static Object[][]oneToOnetest(){
		  	return new Object[][] {};
		  }
		  
		  @Test(dataProvider ="test12",dependsOnGroups={"userentity"})
		  public void oneToOneTest(String result ,String email ,String msg  ) {
			  Assert.assertEquals(result,service.oneToOne(email, msg));
		  }
		  @DataProvider(name = "test13")
		  public static Object[][]pagePosttest(){
		  	return new Object[][] {};
		  }
		  
		  @Test(dataProvider ="test13", dependsOnGroups={"profiletimeline"})
		  public void pagePostTest(String result ,String id ) {
			  Assert.assertEquals(result,service.pagePost(id));
		  }
		  @DataProvider(name = "test14")
		  public static Object[][]registrationServicetest(){
		  	return new Object[][] {};
		  }
		  
		  @Test(dataProvider ="test14")
		  public void registrationServiceTest(String result ,String uname ,String email ,String pass ) {
			  Assert.assertEquals(result,service.registrationService(uname, email, pass));
		  }
		  @DataProvider(name = "test15")
		  public static Object[][]searchtest(){
		  	return new Object[][] {};
		  }
		  
		  @Test(dataProvider ="test15")
		  public void searchTest(String result ,String email ) {
			  Assert.assertEquals(result,service.search(email));
		  }
		/*  @DataProvider(name = "test16")
		  public static Object[][]searchHashtest(){
			  return new Object[][] {{"","dsddddd"},{"#mohamed #3adel","3adel"} , {"asdasd#zamalek adsd","zamalek"} ,  
				  		{"mohamed #mo7sen abo gresha","mo7sen"}};
		  }
		  
		  @Test(dataProvider ="test16")
		  public void searchHashTest(String result ,String hash ) {
			  Assert.assertEquals(result,service.searchHash(hash));
		  }
		  @DataProvider(name = "test17")
		  public static Object[][]searchPagetest(){
		  	return new Object[][] {};
		  }
		  
		  @Test(dataProvider ="test17")
		  public void searchPageTest(String result ,String name ) {
			  Assert.assertEquals(result,service.searchPage(name));
		  }
		  @DataProvider(name = "test18")
		  public static Object[][]sharetest(){
		  	return new Object[][] {};
		  }
		  
		  @Test(dataProvider ="test18")
		  public void shareTest(String result ,String ID ) {
			  Assert.assertEquals(result,service.share(ID));
		  }
		  @DataProvider(name = "test19")
		  public static Object[][]trendstest(){
		  	return new Object[][] {};
		  }
		  
		  @Test(dataProvider ="test19")
		  public void trendsTest(String result  ) {
			  Assert.assertEquals(result,service.trends());
		  }
		  @DataProvider(name = "test20")
		  public static Object[][]writepageposttest(){
		  	return new Object[][] {};
		  }
		  
		  @Test(dataProvider ="test20")
		  public void writepagepostTest(String result  ,String text ,String  id) {
			  Assert.assertEquals(result,service.writepagepost(text, id));
		  }
			*/
		
  

 


 

 

 

 
}
