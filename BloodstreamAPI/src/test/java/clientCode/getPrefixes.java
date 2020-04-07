package clientCode;

import static io.restassured.RestAssured.given;

import java.util.Hashtable;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.Test;

import ReusableCode.auth;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utilities.DataHandler;

public class getPrefixes {
	
	
		@Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
		public void Assert200(Hashtable<String,String> dataTable) {
			
			Response response1 = given().relaxedHTTPSValidation().
 			header("Authorization",auth.ValidAuth).when().get(dataTable.get("EndPoint")).then().assertThat().statusCode(200).and().contentType(ContentType.JSON).extract().response();  
			Assert.assertTrue(response1.getTimeIn(TimeUnit.SECONDS)<=10,"Response Time is not within limit");
			System.out.println(response1.getTimeIn(TimeUnit.SECONDS));
					
	 }

			
		 @Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
          public void Assert401(Hashtable<String,String> dataTable) {
  			
			   //String Authorization = config.property.getProperty("LoginToken");
	 			//String endpoint = dataTable.get("EndPoint");
			 
	  			Response response2 = given().relaxedHTTPSValidation().
	  			header("Authorization",auth.InvalidAuth).when().get(dataTable.get("EndPoint")).then().assertThat().statusCode(401).extract().response();  
	 			Assert.assertTrue(response2.getTimeIn(TimeUnit.SECONDS)<=10,"Response Time is not within limit");
	 			System.out.println(response2.getTimeIn(TimeUnit.SECONDS));
		 }	
		 }
  			
  			
  		


