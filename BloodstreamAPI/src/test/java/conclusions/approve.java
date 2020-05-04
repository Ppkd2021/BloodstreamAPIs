package conclusions;
import static io.restassured.RestAssured.given;

import java.io.File;
import java.util.Hashtable;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import ReusableCode.auth;
import bloodstream.Suite;
import io.restassured.RestAssured;
import io.restassured.specification.ResponseSpecification;
import utilities.DataHandler;
public class approve extends Suite {
	 
public static ResponseSpecification responseSpec;
	 @BeforeTest
	 public void BeforeTest(){
		{
			RestAssured.useRelaxedHTTPSValidation(); 
		}
	 }
	 

	@Test(enabled=false, dataProviderClass = DataHandler.class,dataProvider="dataProvider")
	public void Assert200(Hashtable<String,String> dataTable) {
		
	File file = new File(System.getProperty("user.dir")+"//payloads//postApprove.json");
	responseSpec = auth.reuseAssert200();
	given().header("Authorization",auth.ValidAuth).body(file).when().post(dataTable.get("EndPoint")).then().spec(responseSpec);

	}

	 @Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
      public void Assert401(Hashtable<String,String> dataTable) {
			
	  File file = new File(System.getProperty("user.dir")+"//payloads//postApprove.json");
	  auth.reuseAssert401();
	  given().header("Authorization",auth.InvalidAuth).body(file).when().post(dataTable.get("EndPoint")).then().spec(responseSpec);
	 	
		
	}
		
}
