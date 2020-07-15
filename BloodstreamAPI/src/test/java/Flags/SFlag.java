package Flags;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

import java.util.Hashtable;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import ReusableCode.auth;
import bloodstream.Suite;
import io.restassured.RestAssured;
import io.restassured.specification.ResponseSpecification;
import utilities.DataHandler;

public class SFlag extends Suite{ 

	@BeforeTest
	public void BeforeTest()
		{
		 
			RestAssured.useRelaxedHTTPSValidation(); 	
		}
public static ResponseSpecification responseSpec;

	@Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
	public void DonationsAssert200(Hashtable<String,String> dataTable) {
		
    responseSpec = auth.reuseAssert200();	
	given().header("Authorization",auth.ValidAuth).
	param("GroupStatus",dataTable.get("GroupStatus")).param("Sort",dataTable.get("Sort")).
	when().get(dataTable.get("EndPoint")).then().body("result",is(true)).spec(responseSpec);
}		
	@Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
	public void TestsAssert200(Hashtable<String,String> dataTable) {
		
    responseSpec = auth.reuseAssert200();	
	given().header("Authorization",auth.ValidAuth).
	param("GroupStatus",dataTable.get("GroupStatus")).param("Sort",dataTable.get("Sort")).
	when().get(dataTable.get("EndPoint")).then().body("result",is(true)).spec(responseSpec);
}	
	@Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
	public void WorklistsAssert200(Hashtable<String,String> dataTable) {
		
    responseSpec = auth.reuseAssert200();	
	given().header("Authorization",auth.ValidAuth).
	param("GroupStatus",dataTable.get("GroupStatus")).param("Sort",dataTable.get("Sort")).
	when().get(dataTable.get("EndPoint")).then().body("result",is(true)).spec(responseSpec);
}		
	
	 @Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
	 public void Assert400(Hashtable<String,String> dataTable) {
    responseSpec = auth.reuseAssert400();		 
	given().header("Authorization",auth.ValidAuth).param("GroupStatus",dataTable.get("GroupStatus")).when().get(dataTable.get("EndPoint")).then().body("result",is(false)).spec(responseSpec);
	 }	 
 
	 
    @Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
    public void Assert401(Hashtable<String,String> dataTable) {
    responseSpec = auth.reuseAssert401();		 
    given().header("Authorization",auth.InvalidAuth).param("GroupStatus",dataTable.get("GroupStatus")).when().get(dataTable.get("EndPoint")).then().spec(responseSpec);
   }	   	
 }



