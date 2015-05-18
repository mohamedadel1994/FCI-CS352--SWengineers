package com.FCI.SWE.Models;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

public class Hashtagtest {
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
	Hashtag h= new Hashtag(); 
	
	
	 
	  

	@DataProvider(name = "test1")
	  public static Object[][]getName(){
	  	return new Object[][] {{"fci","first post #fci"},{"fcii","first post #fci"}};
	  }
	  
	  @Test(dataProvider ="test1")
	  public void getNameTest(String result , String text ) {
		
		  DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
			Query gaeQuery = new Query("Hashtags");
			PreparedQuery pqs = datastore.prepare(gaeQuery);
			List<Entity> list = pqs.asList(FetchOptions.Builder.withDefaults());
			Entity hash = new Entity("Hashtags", list.size() + 1);
			hash.setProperty("key", "fci");
			hash.setProperty("post","first post #fci");
			hash.setProperty("user","amir");
			hash.setProperty("count", 1);
			datastore.put(hash);
		  Assert.assertEquals(result,h.add(text));
		  
	  }
	 /* @DataProvider(name = "test2")
	  public static Object[][]sortTest(){
	  	return new Object[][] {{"Trends Hashtag</br>zamalek</br>amir</br>mohammed</br>mo7sen</br>3adel</br>",""}};
	  }
	  
	  @Test(dataProvider ="test2")
	  public void sortTest(String result ) {
		 
		  Assert.assertEquals(result,h.sort());
	  }
	  
	  
	  @DataProvider(name = "test3")
	  public static Object[][]searchTest(){
	  	return new Object[][] {{"#mohamed #3adel","3adel"} , {"asdasd#zamalek adsd","zamalek"} ,  
		  		{"mohamed #mo7sen abo gresha","mo7sen"}};
	  }
	  
	  @Test(dataProvider ="test3")
	  public void searchTest(String result , String hash ) {
		  Assert.assertEquals(result,h.search(hash));
	  }*/

	   
}
