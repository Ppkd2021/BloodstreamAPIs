package clientCode;

import static io.restassured.RestAssured.given;

import java.util.Hashtable;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import ReusableCode.auth;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import utilities.DataHandler;

public class getClientSites {
	
	 @BeforeTest
	 public void BeforeTest(){
		{
			RestAssured.useRelaxedHTTPSValidation(); 
		}
		
	}
	
		@Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
		public void Assert200(Hashtable<String,String> dataTable) {

 			
			given().header("Authorization",auth.ValidAuth).when().get(dataTable.get("EndPoint")).then().assertThat().statusCode(200).and().contentType(ContentType.JSON).extract().response();  
			//Assert.assertTrue(response.getTimeIn(TimeUnit.SECONDS)<=10,"Response Time is not within limit");
			//System.out.println(response.getTimeIn(TimeUnit.SECONDS));
		
		
		
		}
		 @Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
          public void Assert401(Hashtable<String,String> dataTable) {
  			
  			//Response response = given().relaxedHTTPSValidation().
			 given().header("Authorization",auth.InvalidAuth).when().get(dataTable.get("EndPoint")).then().assertThat().statusCode(401).extract().response();  
 		//	Assert.assertTrue(response.getTimeIn(TimeUnit.SECONDS)<=10,"Response Time is not within limit");
 			//System.out.println(response.getTimeIn(TimeUnit.SECONDS));
		 }	
  			
  		    
}



