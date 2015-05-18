package com.FCI.SWE.Models;


import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
public class conversationSubjectTest {
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
		  
		  conversationSubject conv=new conversationSubject();
		  
		  @DataProvider(name = "test1")
		  public static Object[][]createConvtest(){
		  	return new Object[][] {};
		  }
		  
		  @Test(dataProvider ="test1")
		  public void createConvtest(String result , String sender , String msg , String convName,String receivers) {
			//  Assert.assertEquals(result,conv.createConv(sender, msg, convName, receivers));
		  }
		  
  @Test
  public void Notify() {
    throw new RuntimeException("Test not implemented");
  }

  @Test
  public void attach() {
    throw new RuntimeException("Test not implemented");
  }


}
