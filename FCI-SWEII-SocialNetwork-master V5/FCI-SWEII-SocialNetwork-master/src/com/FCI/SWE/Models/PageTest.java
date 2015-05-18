package com.FCI.SWE.Models;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
@Test(groups="pagetest")
public class PageTest {
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
		  
		  
		  Page page=new Page();
		  
		  @DataProvider(name = "test1")
		  public static Object[][]searchPagetest(){
		  	return new Object[][] {{"ss","egypt"},{"zamalek","zamalek"},{"","ahly"}};
		  }
		  
		  @Test(dataProvider ="test1")
		  public void searchPagetest(String result , String name ) {
			  Assert.assertEquals(result,page.searchPage(name));
			  System.out.println(page.searchPage(name));
		  }
		  
		  @DataProvider(name = "test2")
		  public static Object[][]getPagetest(){
		  	return new Object[][] {{"ss","egypt"},{"zamalek","zamalek"},{"","ahly"}};
		  }
		  
		  @Test(dataProvider ="test2")
		  public void getPagetest(String result) {
			  Assert.assertEquals(result,page.getpages());
			 // System.out.println(page.getpages());
		  }
	
 
  

}
