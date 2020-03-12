package conclusions;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.util.Hashtable;

import org.testng.annotations.Test;

import bloodstream.Suite;
import utilities.DataHandler;
import utilities.config;

public class approve extends Suite {
	

	@Test(enabled=false, dataProviderClass = DataHandler.class,dataProvider="dataProvider")
	public void Assert200(Hashtable<String,String> dataTable) {
		
		
		File file = new File(System.getProperty("user.dir")+"//payloads//postApprove.json");
		String Authorization = config.property.getProperty("LoginToken");
		String endpoint = dataTable.get("EndPoint");
		given().relaxedHTTPSValidation().
	   header("Authorization",Authorization).
	   body(file).
	   when().post(endpoint).then().
	   assertThat().statusCode(200);
			
 }

	 @Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
      public void Assert401(Hashtable<String,String> dataTable) {
			
			//config.log.debug(new Object() {}.getClass().getEnclosingMethod().getName()+ " Invoked");
			String Authorization = config.property.getProperty("InvalidToken");
			String endpoint = dataTable.get("EndPoint");
		    given().relaxedHTTPSValidation().
			header("Authorization",Authorization).// Use this to add headers
			when().post(endpoint).then().      // Use this to specify the API path
			assertThat().statusCode(401); 
					
			 }
		
}
