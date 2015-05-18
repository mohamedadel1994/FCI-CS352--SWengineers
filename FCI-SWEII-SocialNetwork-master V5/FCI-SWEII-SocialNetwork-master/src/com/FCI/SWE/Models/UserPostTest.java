package com.FCI.SWE.Models;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.annotations.Test;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

public class UserPostTest {
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
UserPost user =new UserPost();



@DataProvider(name = "test1")
public static Object[][]writePosttest(){
	return new Object[][] {};
}

@Test(dataProvider ="test1")
public void writePosttest(String result , String text ,String emails ,String feeling ) {
	//  Assert.assertEquals(result,user.writePost(text, emails, feeling));
}
}