package clientCode;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.util.Hashtable;

import org.testng.annotations.Test;

import utilities.DataHandler;
import utilities.config;

public class putClientSites {
	
		@Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
		public void Assert200(Hashtable<String,String> dataTable) {
			File file = new File(System.getProperty("user.dir")+"//payloads//putClientSites.json");
			
			//config.log.debug(new Object() {}.getClass().getEnclosingMethod().getName()+ " Invoked");
			String Authorization = config.property.getProperty("LoginToken");
			String endpoint = dataTable.get("EndPoint");
			
		   given().relaxedHTTPSValidation().
		   header("Authorization",Authorization).
		   body(file).
		   when().get(endpoint).then().     
		   assertThat().statusCode(400); 
					//added
	 }
	
		 @Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
          public void Assert400(Hashtable<String,String> dataTable) {
			
			//config.log.debug(new Object() {}.getClass().getEnclosingMethod().getName()+ " Invoked");
			String Authorization = config.property.getProperty("LoginToken");
			String endpoint = dataTable.get("EndPoint");
		    given().relaxedHTTPSValidation().
			header("Authorization",Authorization).
			
			when().get(endpoint).then().     
			assertThat().statusCode(400); 
					
	 }
		 @Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
          public void Assert401(Hashtable<String,String> dataTable) {
  			
  			//config.log.debug(new Object() {}.getClass().getEnclosingMethod().getName()+ " Invoked");
  			String Authorization = config.property.getProperty("InvalidToken");
  			String endpoint = dataTable.get("EndPoint");
  		    given().relaxedHTTPSValidation().
  			header("Authorization",Authorization).
  			
  			when().get(endpoint).then().     
  			assertThat().statusCode(401); 
  					
  			 }
  		
}

