package com.FCI.SWE.Services;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class HashtagServiceTest {
	HashtagService hashtagservicetest =new HashtagService();
	
	@DataProvider(name = "test16")
	  public static Object[][]searchHashtest(){
		  return new Object[][] {{"","dsddddd"},{"#mohamed #3adel","3adel"} , {"asdasd#zamalek adsd","zamalek"} ,  
			  		{"mohamed #mo7sen abo gresha","mo7sen"}};
	  }
	  
	  @Test(dataProvider ="test16")
	  public void searchHashTest(String result ,String hash ) {
		  Assert.assertEquals(result,hashtagservicetest.searchHash(hash));
	  }

  @DataProvider(name = "test19")
  public static Object[][]trendstest(){
  	return new Object[][] {};
  }
  
  @Test(dataProvider ="test19")
  public void trendsTest(String result  ) {
	  Assert.assertEquals(result,hashtagservicetest.trends());
  }
}
