package clientCode;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.util.Hashtable;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.Test;

import ReusableCode.auth;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utilities.DataHandler;
import utilities.config;

public class putPrefixes {
	

		@Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
		public void Assert200(Hashtable<String,String> dataTable) {
			File file = new File(System.getProperty("user.dir")+"//payloads//putPrefixes.json");
			Response response = given().relaxedHTTPSValidation().
			header("Authorization",auth.ValidAuth).body(file).when().put(dataTable.get("EndPoint")).then().assertThat().statusCode(200).and().contentType(ContentType.JSON).extract().response(); 
			Assert.assertTrue(response.getTimeIn(TimeUnit.SECONDS)<=10,"Response Time is not within limit");
			System.out.println(response.getTimeIn(TimeUnit.SECONDS));
	
					
	 }
	
		 @Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
        public void Assert400(Hashtable<String,String> dataTable) {
		Response response = given().relaxedHTTPSValidation().
		header("Authorization",auth.ValidAuth).when().put(dataTable.get("EndPoint")).then().assertThat().statusCode(400).and().contentType(ContentType.JSON).extract().response(); 
		Assert.assertTrue(response.getTimeIn(TimeUnit.SECONDS)<=10,"Response Time is not within limit");
		System.out.println(response.getTimeIn(TimeUnit.SECONDS));
				
					
	 }
		 @Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
          public void Assert401(Hashtable<String,String> dataTable) {
  			
			Response response = given().relaxedHTTPSValidation().
			header("Authorization",auth.InvalidAuth).when().put(dataTable.get("EndPoint")).then().assertThat().statusCode(401).and().contentType(ContentType.JSON).extract().response(); 
			Assert.assertTrue(response.getTimeIn(TimeUnit.SECONDS)<=10,"Response Time is not within limit");
			System.out.println(response.getTimeIn(TimeUnit.SECONDS));
								
  					
  			 }
  		
}

