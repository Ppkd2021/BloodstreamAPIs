package Drop2defects;

import static io.restassured.RestAssured.given;
import java.util.Hashtable;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ReusableCode.auth;
import io.restassured.RestAssured;
import io.restassured.specification.ResponseSpecification;
import utilities.DataHandler;

public class PoolDetail75101 {
public static ResponseSpecification responseSpec;	

	@BeforeTest
	 public void BeforeTest(){
		{
			RestAssured.useRelaxedHTTPSValidation(); 
		}
	 }
	
	@Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
	public void TestInfoInvalidPoolDetail(Hashtable<String,String> dataTable) {
		
	auth.reuseAssert404();		
	given().header("Authorization",auth.ValidAuth).param("PoolID",dataTable.get("PoolID")).param("Sort",dataTable.get("Sort")).
	when().get( dataTable.get("EndPoint")).then().spec(responseSpec);
}

}