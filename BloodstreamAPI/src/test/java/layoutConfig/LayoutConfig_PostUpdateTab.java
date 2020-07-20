package layoutConfig;

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

public class LayoutConfig_PostUpdateTab extends Suite{ 
public static ResponseSpecification responseSpec;	

	

	@Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
	public void Assert200(Hashtable<String,String> dataTable) {
		
	    auth.reuseAssert200();
	    File file = new File(System.getProperty("user.dir")+"//payloads//putUpdateTab200.json");
		given().header("Authorization",auth.ValidAuth).param("deleteTab",dataTable.get("deleteTab")).body(file).when().put( dataTable.get("EndPoint")).
		then().spec(responseSpec);   
}
	
	 @Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
     public void Assert400(Hashtable<String,String> dataTable) {
		
		 auth.reuseAssert400();
		 File file = new File(System.getProperty("user.dir")+"//payloads//putUpdateTab400.json");
		 given().header("Authorization",auth.ValidAuth).param("deleteTab",dataTable.get("deleteTab")).body(file).when().put( dataTable.get("EndPoint")).
		 then().spec(responseSpec);   
 }
	@Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
    public void Assert401(Hashtable<String,String> dataTable) {
			
		 auth.reuseAssert401();
    	 given().header("Authorization",auth.InvalidAuth).param("deleteTab",dataTable.get("deleteTab")).when().put( dataTable.get("EndPoint")).
		 then().spec(responseSpec);   
	}
	
}



