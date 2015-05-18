package com.FCI.SWE.Services;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class PageServiceTest {
	PageService pageservice =new PageService();
	
	@DataProvider(name = "test4")
	public static Object[][]createPagetest(){
	  	return new Object[][] {{"done","zamalek","sports","football"}};
	  }
	  
	  @Test(dataProvider ="test4",dependsOnGroups={"PageModelTest"})
	  public void createPageTest(String result ,String name ,String category ,String type ) {
		  Assert.assertEquals(result,pageservice.createPage(name, category, type));
	  } 


	  @DataProvider(name = "test8")
	  public static Object[][]likePagetest(){
	  	return new Object[][] {};
	  }
	  
	  @Test(dataProvider ="test8",dependsOnGroups={"userentity"})
	  public void likePageTest(String result ,String ID ) {
		  Assert.assertEquals(result,pageservice.likePage(ID));
	  }

	  @DataProvider(name = "test10")
	  public static Object[][]mypagestest(){
	  	return new Object[][] {};
	  }
	  
	  @Test(dataProvider ="test10",dependsOnGroups={"pagetest"})
	  public void mypagesTest(String result  ) {
		  Assert.assertEquals(result,pageservice.mypages());
	  }
	  
	  
	  
	  @DataProvider(name = "test13")
	  public static Object[][]pagePosttest(){
	  	return new Object[][] {};
	  }
	  
	  @Test(dataProvider ="test13", dependsOnGroups={"profiletimeline"})
	  public void pagePostTest(String result ,String id ) {
		  Assert.assertEquals(result,pageservice.createpagePost(id));
	  }

	  @DataProvider(name = "test17")
	  public static Object[][]searchPagetest(){
	  	return new Object[][] {};
	  }
	  
	  @Test(dataProvider ="test17")
	  public void searchPageTest(String result ,String name ) {
		  Assert.assertEquals(result,pageservice.searchPage(name));
	  }

	  @DataProvider(name = "test20")
	  public static Object[][]writepageposttest(){
	  	return new Object[][] {};
	  }
	  
	  @Test(dataProvider ="test20")
	  public void writepagepostTest(String result  ,String text ,String  id) {
		  Assert.assertEquals(result,pageservice.writepagepost(text, id));
	  }
}
