package SearchableColumns;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

import java.util.Hashtable;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ReusableCode.auth;
import bloodstream.Suite;
import io.restassured.RestAssured;
import io.restassured.specification.ResponseSpecification;
import utilities.DataHandler;

public class ConclToApprove extends Suite{ 
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
	 given().header("Authorization",auth.ValidAuth).param("gridName",dataTable.get("gridName")).
	 when().get(dataTable.get("EndPoint")).then().body("result",is(true)).spec(responseSpec);
}
	
	
    @Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
    public void Assert400(Hashtable<String,String> dataTable) {

    	 responseSpec = auth.reuseAssert400();
	given().header("Authorization",auth.ValidAuth).param("gridName",dataTable.get("gridName")).
	when().get(dataTable.get("EndPoint")).then().body("result",is(false)).spec(responseSpec);
	
					
}
    @Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
	public void Assert401(Hashtable<String,String> dataTable){
	
    	 responseSpec =  auth.reuseAssert401();
	given().header("Authorization",auth.InvalidAuth).param("gridName",dataTable.get("gridName")).
	when().get(dataTable.get("EndPoint")).then().spec(responseSpec);
}	

}

	/*@Test(dataProvider="DataVC")
    public void Assert400(Hashtable<String,String> dataTable) {

    auth.reuseAssert400();
	given().header("Authorization",auth.ValidAuth).param("gridName",dataTable.get("gridName")).
	when().get(dataTable.get("EndPoint")).then().spec(responseSpec);
	
					
}
	@Test(dataProvider="DataVC")
	public void Assert401(Hashtable<String,String> dataTable){
	
    auth.reuseAssert401();
	given().header("Authorization",auth.InvalidAuth).param("gridName",dataTable.get("gridName")).
	when().get(dataTable.get("EndPoint")).then().spec(responseSpec);
}	
*/

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



@DataProvider(name="mergedataprovider")
    public static Object[][] mergedataprovider(){  
		
		HashMap<String,String> dp3 = new HashMap<String,String>();
		dp3.putAll((HashMap<String, String>) dataprovidertwo()[0][0]);
		dp3.putAll((Hashtable<String, String>) getDataObject()[][]);
		Object[][] o = new Object[1][];
		o[0]=new Object[]{dp3};
		
	}
	
	
	
	@DataProvider(name="dataprovidertwo")
    public static Object[][] dataprovidertwo(Method m){  
		Object[][] o = new Object[1][];
		HashMap<String,String> dp2 = new HashMap<String,String>();
		dp2.put("gridName", "TestInformation");
		dp2.put("gridName", "DonationInformation");
		
		o[0]=new Object[]{dp2};
		return o;
	}
	*/