package layoutConfig;

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

public class LayoutConfig_GetDeviceType extends Suite{ 
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
       given().header("Authorization",auth.ValidAuth).param("dashboardSpecific",dataTable.get("dashboardSpecific")).
       when().get(dataTable.get("EndPoint")).then().body("result",is(true)).spec(responseSpec);
}


@Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
public void Assert401(Hashtable<String,String> dataTable){
	  
	    responseSpec = auth.reuseAssert401();
		given().header("Authorization",auth.InvalidAuth).param("dashboardSpecific",dataTable.get("dashboardSpecific")).when().get(dataTable.get("EndPoint")).
		then().spec(responseSpec); 
    } 
}

