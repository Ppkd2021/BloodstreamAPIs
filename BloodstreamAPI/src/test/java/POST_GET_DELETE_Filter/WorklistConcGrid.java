package POST_GET_DELETE_Filter;
import static io.restassured.RestAssured.given;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import ReusableCode.Base64WorklistConc;
import ReusableCode.auth;
import io.restassured.RestAssured;
import io.restassured.specification.ResponseSpecification;
import utilities.DataHandler;

public class WorklistConcGrid {
	public static ResponseSpecification responseSpec;
	@BeforeTest
	public void BeforeTest()
		{
		 
			RestAssured.useRelaxedHTTPSValidation(); 	
		}
		
		@Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
		public void Assert200(Hashtable<String,String> dataTable) throws IOException {
			
			Base64WorklistConc.Encoder();
			File file = new File(System.getProperty("user.dir")+"//payloads//SearchFilter_POSTWorklistConclusions.json");
			responseSpec = auth.reuseAssert200(); 
			given().header("Authorization",auth.ValidAuth).body(file).when().post(dataTable.get("EndPoint")).
			then().spec(responseSpec);
			
		}	  
		 	 @Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
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
		 	

		
		}



