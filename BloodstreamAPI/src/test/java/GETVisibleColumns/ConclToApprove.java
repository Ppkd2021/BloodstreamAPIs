package GETVisibleColumns;

import static io.restassured.RestAssured.given;

import java.util.Hashtable;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ReusableCode.auth;
import io.restassured.RestAssured;
import io.restassured.specification.ResponseSpecification;
import utilities.DataHandler;

public class ConclToApprove {
	public static ResponseSpecification responseSpec;	

    @BeforeTest
	 public void BeforeTest(){
		{
			RestAssured.useRelaxedHTTPSValidation(); 
		}
 }
	
   /* @DataProvider(name="DataVC")
    public Object[][] dataforGET()
    {
    	
    	Object[][] data= new Object[5][3];
    	data[0][0]="/gridLayout/filters";
    	data[0][1]="ConclusionsToApprove";
    	return new Object[][]{
    		{""
    	}
    };
    	*/

	@Test(dataProvider="DataVC" )
	
	public void Assert200(Hashtable<String,String> dataTable) {
		
	 auth.reuseAssert200();
	 given().header("Authorization",auth.ValidAuth).param("gridName",dataTable.get("gridName")).param("supressDate",dataTable.get("supressDate")).
	 when().get(dataTable.get("EndPoint")).then().spec(responseSpec);  
}
	
	
	@Test(dataProvider="DataVC")
    public void Assert400(Hashtable<String,String> dataTable) {

    auth.reuseAssert400();
	given().header("Authorization",auth.ValidAuth).param("gridName",dataTable.get("gridName")).param("supressDate",dataTable.get("supressDate")).
	when().get(dataTable.get("EndPoint")).then().spec(responseSpec);
	
					
}
	@Test(dataProvider="DataVC")
	public void Assert401(Hashtable<String,String> dataTable){
	
    auth.reuseAssert401();
	given().header("Authorization",auth.InvalidAuth).param("gridName",dataTable.get("gridName")).param("supressDate",dataTable.get("supressDate")).
	when().get(dataTable.get("EndPoint")).then().spec(responseSpec);
}	

}
