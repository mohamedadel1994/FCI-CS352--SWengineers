package com.FCI.SWE.Models;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
@Test(groups="profiletimeline")
public class ProfileTimeLineTest {
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
ProfileTimeLine profile =new ProfileTimeLine();


	@DataProvider(name = "test1")
	  public static Object[][]getPostsTest(){
	  	return new Object[][] {};
	  }
	  
	  @Test(dataProvider ="test1")
	  public void getPostsTest(String result ) {
		  
		  Assert.assertEquals(result,profile.getPosts());
	  }
	  
	  
	  
	  
	  @DataProvider(name = "test2")
	  public static Object[][]getpagePostsTest(){
	  	return new Object[][] {};
	  }
	  
	  @Test(dataProvider ="test2")
	  public void getpagePostsTest(String result , String ID ) {
		  
		  Assert.assertEquals(result,profile.getpagePosts(ID));
	  }
	   
	  
	  
	  @DataProvider(name = "test3")
	  public static Object[][]getUserPostsTest(){
	  	return new Object[][] {};
	  }
	  
	  @Test(dataProvider ="test3")
	  public void getUserPostsTest(String result , String user ) {
		 
		  Assert.assertEquals(result,profile.getUserPosts(user));
	  }
}
