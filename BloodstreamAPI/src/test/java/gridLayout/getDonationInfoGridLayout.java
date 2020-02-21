package gridLayout;

import static io.restassured.RestAssured.given;

import java.util.Hashtable;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import utilities.DataHandler;
import utilities.config;

public class getDonationInfoGridLayout {
	
	
	@Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
	public void Assert200(Hashtable<String,String> dataTable) {
		
		//config.log.debug(new Object() {}.getClass().getEnclosingMethod().getName()+ " Invoked");
		String Authorization = config.property.getProperty("LoginToken");
		String endpoint = dataTable.get("EndPoint");
	   given().relaxedHTTPSValidation().
	  header("Authorization",Authorization).
	  param("gridName",dataTable.get("gridName")).
	 when().get(endpoint).then().      
	 assertThat().statusCode(200); 				
		 }
	
	
	@Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
	public void Assert400(Hashtable<String,String> dataTable)
	{
		//config.log.debug(new Object() {}.getClass().getEnclosingMethod().getName()+ " Invoked");
		String Endpoint = dataTable.get("EndPoint");
		String Authorization = "LoginToken";
		
		given().relaxedHTTPSValidation().
		header("Authorization",Authorization). 
		param("gridName",dataTable.get("gridName")).
		when().get(Endpoint).then().     
		assertThat().statusCode(401);	
	}
	
	@Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
	public void Assert401(Hashtable<String,String> dataTable)
	{
		//config.log.debug(new Object() {}.getClass().getEnclosingMethod().getName()+ " Invoked");
		String Endpoint = dataTable.get("EndPoint");
		String Authorization = "Invalid_Token";
		
		given().relaxedHTTPSValidation().
		header("Authorization",Authorization). 
		param("gridName",dataTable.get("gridName")).
		when().get(Endpoint).then().     
		assertThat().statusCode(401);	
	}
	
	
	

	

}
