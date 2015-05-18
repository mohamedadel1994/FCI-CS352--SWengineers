package com.FCI.SWE.Models;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

public class publicPostTest {
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

		  publicPost p =new publicPost();
		  @DataProvider(name = "test1")
		  public static Object[][]writePostTest(){
		  	return new Object[][] {{"done","dd","20", "gg"}};
		  }
		  
		  @Test(dataProvider ="test1")
		  public void writePostTest(String result , String text,String ID,String feeling ) {
			  Assert.assertEquals(result,p.writePost(text, ID, feeling));
		  }
}
