package PostCustomFilter;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ReusableCode.Base64WorklistInfo;
import ReusableCode.auth;
import io.restassured.RestAssured;
import io.restassured.specification.ResponseSpecification;
import utilities.DataHandler;

public class WorklistInfGrid {

	public static ResponseSpecification responseSpec;
	@BeforeTest
	public void BeforeTest()
		{
		 
			RestAssured.useRelaxedHTTPSValidation(); 	
		}
		
		@Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
		public void Assert200(Hashtable<String,String> dataTable) throws IOException {
			
			//Base64WorklistInfo.Encoder();
			File file = new File(System.getProperty("user.dir")+"//payloads//SearchFilter_POSTWorklistInformation.json");
			responseSpec = auth.reuseAssert200(); 
			given().header("Authorization",auth.ValidAuth).body(file).when().post(dataTable.get("EndPoint")).
			then().body("result",is(true)).spec(responseSpec);
		}
		/*	@Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
		 	public void GETFilterAssert200(Hashtable<String,String> dataTable) {
		 		
		 	 responseSpec = auth.reuseAssert200();
		 	 given().header("Authorization",auth.ValidAuth).param("gridName",dataTable.get("gridName")).
		 	 param("filterName",dataTable.get("filterName")).when().get(dataTable.get("EndPoint")).
		 	 then().spec(responseSpec);
		 	}


		 	@Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
		 	public void DeleteFilterAssert200(Hashtable<String,String> dataTable) {
		 	   
		 	 responseSpec =  auth.reuseAssert200();
		 	 given().header("Authorization",auth.ValidAuth).param("gridName",dataTable.get("gridName")).
		 	 param("filterName",dataTable.get("filterName")).when().delete(dataTable.get("EndPoint")).
		 	 then().spec(responseSpec);
		 	}    
		 	*/
		 	

		
		}


