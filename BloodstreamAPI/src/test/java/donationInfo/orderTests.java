package donationInfo;

import static io.restassured.RestAssured.given;

import java.util.Hashtable;
import java.util.concurrent.TimeUnit;

import org.hamcrest.core.Is;
import org.testng.Assert;
import org.testng.annotations.Test;

import ReusableCode.auth;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utilities.DataHandler;

public class orderTests {

	
		@Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
		public void Assert200(Hashtable<String,String> dataTable) {
			
			 Response response = given().relaxedHTTPSValidation().
			 header("Authorization",auth.ValidAuth).param("donationId",dataTable.get("donationId")).param("requestId",dataTable.get("requestId")).when().get(dataTable.get("EndPoint")).
			 then().assertThat().statusCode(200).and().contentType(ContentType.JSON).
			 assertThat().body("messages.message[0]", Is.is("Successful")).extract().response();  
			 Assert.assertTrue(response.getTimeIn(TimeUnit.SECONDS)<=10,"Response Time is not within limit");
			 System.out.println(response.getTimeIn(TimeUnit.SECONDS));
					
	 }
	
		 @Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
          public void Assert404(Hashtable<String,String> dataTable) {
			
			 Response response = given().relaxedHTTPSValidation().
			 header("Authorization",auth.ValidAuth).param("donationId",dataTable.get("donationId")).param("requestId",dataTable.get("requestId")).when().get(dataTable.get("EndPoint")).
			 then().assertThat().statusCode(404).extract().response();  
			 Assert.assertTrue(response.getTimeIn(TimeUnit.SECONDS)<=10,"Response Time is not within limit");
			 System.out.println(response.getTimeIn(TimeUnit.SECONDS));
					
	 }
		 @Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
          public void Assert401(Hashtable<String,String> dataTable) {
  			
			 Response response = given().relaxedHTTPSValidation().
			 header("Authorization",auth.InvalidAuth).param("donationId",dataTable.get("donationId")).param("requestId",dataTable.get("requestId")).when().get(dataTable.get("EndPoint")).
			 then().assertThat().statusCode(401).extract().response();  
			 Assert.assertTrue(response.getTimeIn(TimeUnit.SECONDS)<=10,"Response Time is not within limit");
			 System.out.println(response.getTimeIn(TimeUnit.SECONDS));
  					
  			 }
  		
}