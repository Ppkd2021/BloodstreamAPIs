package layoutConfig;

import static io.restassured.RestAssured.given;
import java.util.Hashtable;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ReusableCode.auth;
import io.restassured.RestAssured;
import io.restassured.specification.ResponseSpecification;
import utilities.DataHandler;

public class LayoutConfig_DeleteTab {
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
       given().header("Authorization",auth.ValidAuth).param("tabName",dataTable.get("tabName")).param("version",dataTable.get("version")).when().delete(dataTable.get("EndPoint")).
       then().spec(responseSpec); 		
}


 @Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
 public void Assert401(Hashtable<String,String> dataTable)
{
	    auth.reuseAssert401();
		given().header("Authorization",auth.InvalidAuth).param("tabName",dataTable.get("tabName")).param("version",dataTable.get("version")).when().delete(dataTable.get("EndPoint")).
		then().spec(responseSpec); 
			
	  }
 @Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
 public void Assert400(Hashtable<String,String> dataTable) {
		
	    auth.reuseAssert400();
		given().header("Authorization",auth.ValidAuth).param("tabName",dataTable.get("tabName")).param("version",dataTable.get("version")).when().delete(dataTable.get("EndPoint")). 
		then().spec(responseSpec); 
			
	  }

 @Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
 public void Assert409(Hashtable<String,String> dataTable) {
		
	
	    auth.reuseAssert409();
		given().header("Authorization",auth.ValidAuth).param("tabName",dataTable.get("tabName")).param("version",dataTable.get("version")).when().delete(dataTable.get("EndPoint")). 
		then().spec(responseSpec); 
 }

}
