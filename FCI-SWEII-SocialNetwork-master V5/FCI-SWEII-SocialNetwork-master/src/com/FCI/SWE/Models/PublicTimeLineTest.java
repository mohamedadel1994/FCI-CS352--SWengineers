package com.FCI.SWE.Models;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
@Test(groups="publictimeline")
public class PublicTimeLineTest {
	PublicTimeLine Line =new PublicTimeLine();

	@DataProvider(name = "test1")
	  public static Object[][]getPostsTest(){
	  	return new Object[][] {};
	  }
	  
	  @Test(dataProvider ="test1")
	  public void getPostsTest(String result ) {
		  
		  Assert.assertEquals(result,Line.getPosts());
	  }
	  
	  
	  
	  
	  
}
