package Drop2defects;

import static io.restassured.RestAssured.given;

import java.util.Hashtable;

import org.testng.annotations.Test;

import utilities.DataHandler;
import utilities.config;

public class AssaySorting74979 {
	@Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
	public void DonationInfoAssaySort(Hashtable<String,String> dataTable) {
		
		String Authorization = config.property.getProperty("LoginToken");
		String endpoint = dataTable.get("EndPoint");
		given().relaxedHTTPSValidation().
		header("Authorization",Authorization). 
		param("GroupStatus",dataTable.get("GroupStatus")).    
		param("Sort",dataTable.get("Sort")).
		when().get(endpoint).then().     
		assertThat().statusCode(200);	
		
	}
}
