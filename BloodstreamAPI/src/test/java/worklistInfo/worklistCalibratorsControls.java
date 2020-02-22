package worklistInfo;

import static io.restassured.RestAssured.given;

import java.util.Hashtable;

import org.testng.annotations.Test;

import utilities.DataHandler;
import utilities.config;

public class worklistCalibratorsControls {


	@Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
	public void Assert200(Hashtable<String,String> dataTable) {
		
		
		//config.log.debug(new Object() {}.getClass().getEnclosingMethod().getName()+ " Invoked");
		String Authorization = config.property.getProperty("LoginToken");
		String endpoint = dataTable.get("EndPoint");
		given().relaxedHTTPSValidation().
	   header("Authorization",Authorization).
	   param("WorklistID",dataTable.get("WorklistID")).
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
		param("WorklistID",dataTable.get("WorklistID")).
		when().get(endpoint).then().  
		assertThat().statusCode(200); 
				
 }
	 @Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
      public void Assert401(Hashtable<String,String> dataTable) {
			
			//config.log.debug(new Object() {}.getClass().getEnclosingMethod().getName()+ " Invoked");
			String Authorization = config.property.getProperty("InvalidToken");
			String endpoint = dataTable.get("EndPoint");
		    given().relaxedHTTPSValidation().
			header("Authorization",Authorization).
			param("WorklistID",dataTable.get("WorklistID")).
			when().get(endpoint).then().      
			assertThat().statusCode(401); 
					
			 }
		
}


