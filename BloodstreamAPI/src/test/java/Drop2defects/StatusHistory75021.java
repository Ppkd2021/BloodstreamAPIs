package Drop2defects;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

import java.util.Hashtable;

import org.testng.annotations.Test;

import ReusableCode.auth;
import bloodstream.Suite;
import io.restassured.specification.ResponseSpecification;
import utilities.DataHandler;

public class StatusHistory75021 extends Suite{ 

	public static ResponseSpecification responseSpec;	

	
	@Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
	public void InvalidColumnSortAssert400(Hashtable<String,String> dataTable) {
		
		responseSpec = auth.reuseAssert400();	
		 given().header("Authorization",auth.ValidAuth).param("donationId",dataTable.get("donationId")).param("requestId",dataTable.get("requestId")).
		 param("Sort",dataTable.get("Sort")).when().get(dataTable.get("EndPoint")).then().body("result",is(false)).spec(responseSpec);
		 
		}
}
