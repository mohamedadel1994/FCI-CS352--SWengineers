package com.FCI.SWE.Models;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

public class conversationObserverTest {
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
  @Test
  public void getEmail() {
    throw new RuntimeException("Test not implemented");
  }

  @Test
  public void setEmail() {
    throw new RuntimeException("Test not implemented");
  }

  @Test
  public void update() {
    throw new RuntimeException("Test not implemented");
  }
}
