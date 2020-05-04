package thresholdConfig;

import static io.restassured.RestAssured.given;
import java.io.File;
import java.util.Hashtable;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ReusableCode.auth;
import io.restassured.RestAssured;
import io.restassured.specification.ResponseSpecification;
import utilities.DataHandler;

public class ThresholdConfig_PUT {
public static ResponseSpecification responseSpec;	

	@BeforeTest
	 public void BeforeTest(){
		{
			RestAssured.useRelaxedHTTPSValidation(); 
		}
	}	
	
	@Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
	public void Assert200(Hashtable<String,String> dataTable) {
  
		responseSpec = auth.reuseAssert200();
		File file = new File(System.getProperty("user.dir")+"//payloads//ThresholdConfig_PUT.json");
		given().header("Authorization",auth.ValidAuth).body(file).when().put(dataTable.get("EndPoint")).then().spec(responseSpec);	
			
}
	
	 @Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
      public void Assert401(Hashtable<String,String> dataTable) {
			
		 responseSpec = auth.reuseAssert401();
		given().header("Authorization",auth.InvalidAuth).when().put(dataTable.get("EndPoint")).then().spec(responseSpec);	
}	
	 
	
	 @Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
	   public void Assert400(Hashtable<String,String> dataTable) {
				
		 responseSpec = auth.reuseAssert400();
		given().header("Authorization",auth.ValidAuth).when().put(dataTable.get("EndPoint")).then().spec(responseSpec);	
	 }	
}
