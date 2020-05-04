package Drop2defects;

import static io.restassured.RestAssured.given;
import java.util.Hashtable;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ReusableCode.auth;
import io.restassured.RestAssured;
import io.restassured.specification.ResponseSpecification;
import utilities.DataHandler;

public class StatusHistoryWithoutTests75017{
public static ResponseSpecification responseSpec;	

	@BeforeTest
	 public void BeforeTest(){
		{
			RestAssured.useRelaxedHTTPSValidation(); 
		}
	 }
	
	@Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
	public void Assert200(Hashtable<String,String> dataTable) {
		
		 auth.reuseAssert200();	
		 given().header("Authorization",auth.ValidAuth).param("donationId",dataTable.get("donationId")).param("requestId",dataTable.get("requestId")).when().get(dataTable.get("EndPoint")).
		 then().spec(responseSpec);
	}

	@Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
      public void Assert404(Hashtable<String,String> dataTable) {
		
		
		 auth.reuseAssert404();	
		 given().header("Authorization",auth.ValidAuth).param("donationId",dataTable.get("donationId")).param("requestId",dataTable.get("requestId")).when().get(dataTable.get("EndPoint")).
		 then().spec(responseSpec);
	}
	
	@Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
      public void Assert401(Hashtable<String,String> dataTable) {
		
		 auth.reuseAssert401();	
	     given().header("Authorization",auth.InvalidAuth).param("donationId",dataTable.get("donationId")).param("requestId",dataTable.get("requestId")).when().get(dataTable.get("EndPoint")).
	     then().spec(responseSpec); 
	}
}
	
	
