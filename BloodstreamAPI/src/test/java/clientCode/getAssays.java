package clientCode;

import static io.restassured.RestAssured.given;

import java.util.Hashtable;

import org.testng.annotations.Test;

import utilities.DataHandler;
import utilities.config;

public class getAssays {
	
	
		
		//config.log.debug(new String(new char[100]).replace("\0", "-"));
		//config.log.debug(this.getClass().getName()+ " Entered");
	
	//Validate 200 Status code 
		@Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
		public void Assert200(Hashtable<String,String> dataTable) {
			
			//config.log.debug(new Object() {}.getClass().getEnclosingMethod().getName()+ " Invoked");
			String Authorization = config.property.getProperty("LoginToken");
			String endpoint = dataTable.get("EndPoint");
			
		   given().relaxedHTTPSValidation().
		   header("Authorization",Authorization).
		   
		   when().get(endpoint).then().     
		   assertThat().statusCode(200); 
					
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
  			
 		   param("requestId",dataTable.get("requestId")).
  			when().get(endpoint).then().     
  			assertThat().statusCode(401); 
  					
  			 }
  		
}

