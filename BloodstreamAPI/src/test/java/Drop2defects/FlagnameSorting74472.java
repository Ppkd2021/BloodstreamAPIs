package Drop2defects;

import static io.restassured.RestAssured.given;
import java.util.Hashtable;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ReusableCode.auth;
import bloodstream.Suite;
import io.restassured.RestAssured;
import io.restassured.specification.ResponseSpecification;
import utilities.DataHandler;


public class FlagnameSorting74472 extends Suite{ 
public static ResponseSpecification responseSpec;	


	
	@Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
	public void DonationInfoFlagnameSort(Hashtable<String,String> dataTable) {
		
	auth.reuseAssert200();	
	given().header("Authorization",auth.ValidAuth).param("GroupStatus",dataTable.get("GroupStatus")).param("Sort",dataTable.get("Sort")).
	when().get(dataTable.get("EndPoint")).then().spec(responseSpec);
	
}
		
	@Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
	public void TestInfoFlagnameSort(Hashtable<String,String> dataTable) {
			
	auth.reuseAssert200();	
	given().header("Authorization",auth.ValidAuth).param("GroupStatus",dataTable.get("GroupStatus")).param("Sort",dataTable.get("Sort")).
	when().get(dataTable.get("EndPoint")).then().spec(responseSpec);
			
	}
	
}
