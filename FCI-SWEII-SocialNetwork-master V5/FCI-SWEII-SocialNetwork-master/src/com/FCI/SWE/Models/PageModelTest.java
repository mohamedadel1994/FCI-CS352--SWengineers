package com.FCI.SWE.Models;


import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
@Test(groups="PageModelTest")
public class PageModelTest {
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
	PageModel pagemodel=new PageModel();
	
	@DataProvider(name = "test1")
	  public static Object[][]createPage(){
	  	return new Object[][] { {"Good","zamalek","sports","football"},{"Good","j","h","y"}  };
	  }
	
  @Test (dataProvider ="test1")
  public void createPage(String result , String Name,String type , String category) {
    
	  Assert.assertEquals(result,pagemodel.createPage(Name,type,category));
  }

  
}
