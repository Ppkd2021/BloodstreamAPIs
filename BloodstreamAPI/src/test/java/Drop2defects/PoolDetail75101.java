package Drop2defects;

import static io.restassured.RestAssured.given;

import java.util.Hashtable;

import org.testng.annotations.Test;

import utilities.DataHandler;
import utilities.config;

public class PoolDetail75101 {

	@Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
	public void TestInfoInvalidPoolDetail(Hashtable<String,String> dataTable) {
		
		//config.log.debug(new Object() {}.getClass().getEnclosingMethod().getName()+ " Invoked");
		String Authorization = config.property.getProperty("LoginToken");
		String endpoint = dataTable.get("EndPoint");
		given().relaxedHTTPSValidation().
		header("Authorization",Authorization). 
		param("PoolID",dataTable.get("PoolID")).       
		when().get(endpoint).then().     
		assertThat().statusCode(404);		
	}

}
