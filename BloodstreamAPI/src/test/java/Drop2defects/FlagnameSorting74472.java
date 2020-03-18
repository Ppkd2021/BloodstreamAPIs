package Drop2defects;

import static io.restassured.RestAssured.given;

import java.util.Hashtable;
import org.testng.annotations.Test;

import utilities.DataHandler;
import utilities.config;

public class FlagnameSorting74472 {
	@Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
	public void DonationInfoFlagnameSort(Hashtable<String,String> dataTable) {
		
		String Authorization = config.property.getProperty("LoginToken");
		String endpoint = dataTable.get("EndPoint");
		given().relaxedHTTPSValidation().
		header("Authorization",Authorization). 
		param("GroupStatus",dataTable.get("GroupStatus")).    
		param("Sort",dataTable.get("Sort")).
		when().get(endpoint).then().     
		assertThat().statusCode(200);	
		
	}
		
		@Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
		public void TestInfoFlagnameSort(Hashtable<String,String> dataTable) {
			
			String Authorization = config.property.getProperty("LoginToken");
			String endpoint = dataTable.get("EndPoint");
			given().relaxedHTTPSValidation().
			header("Authorization",Authorization). 
			param("GroupStatus",dataTable.get("GroupStatus")).    
			param("Sort",dataTable.get("Sort")).
			when().get(endpoint).then().     
			assertThat().statusCode(200);	
			
		        /*def response = idsFromResponse.sort(false).reverse()
		        		assert idsFromResponse == descIds, "Response does not have the ids in descending order"
		*/
		
		
	}
		
		//config.log.debug(new Object() {}.getClass().getEnclosingMethod().getName()+ " Invoked");
		/*String Authorization = config.property.getProperty("LoginToken");
		String endpoint = dataTable.get("EndPoint");
		given().relaxedHTTPSValidation().
	   header("Authorization",Authorization).
	   param("GroupStatus",dataTable.get("GroupStatus")).when().get(endpoint).then().
	    //queryParam("sort", "firstName")
		        contentType(ContentType.JSON).statusCode(200).extract().response();
		        List<String> jsonResponse = response.jsonPath().getList("GroupStatus");
		       // assertTrue(Ordering.natural().isOrdered(jsonResponse));
	   statusCode(200).extract().response();
	    List<String> jsonResponse = response.jsonPath().getList("firstName");
	   //assertThat().body(containsString("Flagname"),containsString("error"));
		
	   
	   
	}*/
	

	
}
