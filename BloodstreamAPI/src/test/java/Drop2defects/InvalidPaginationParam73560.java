package Drop2defects;

import static io.restassured.RestAssured.given;

import java.util.Hashtable;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utilities.DataHandler;
import utilities.config;

public class InvalidPaginationParam73560 {
	

	@Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
	public void Assert400(Hashtable<String,String> dataTable) {
		
		//config.log.debug(new Object() {}.getClass().getEnclosingMethod().getName()+ " Invoked");
		String Authorization = config.property.getProperty("LoginToken");
		String endpoint = dataTable.get("EndPoint");
		given().relaxedHTTPSValidation().
				header("Authorization",Authorization). 
				param("GroupStatus",dataTable.get("GroupStatus")).    
				param("PageSize",dataTable.get("PageSize")).		   
				param("Page",dataTable.get("Page")).
				param("Sort",dataTable.get("Sort")).
				when().get(endpoint).then().     
				assertThat().statusCode(400);		
		
		
		}
		
	}

		
	
		/*@Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
		public void Assert400(Hashtable<String,String> dataTable)
		{
			
			String Authorization = config.property.getProperty("LoginToken");
			String endpoint = dataTable.get("EndPoint");
			Response response = given().
					header("Authorization",Authorization). // Use this to add headers
					param("GroupStatus",dataTable.get("GroupStatus")).    // Use this to add query parameters
					param("PageSize",dataTable.get("PageSize")).		   
					param("Page",dataTable.get("Page")).
					param("Sort",dataTable.get("Sort")).
					when().get(endpoint).then().      // Use this to specify the API path
					assertThat().statusCode(400).						 // Use this to assert status code (optional)
					extract().response(); 
			JsonPath jsonPathEvaluator = response.jsonPath();// Use this to get the response object
			Integer page = jsonPathEvaluator.get("Page");
			Assert.assertTrue(page==0);*/
			 		
					
	
			