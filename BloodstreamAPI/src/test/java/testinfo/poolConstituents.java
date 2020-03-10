package testinfo;

import static io.restassured.RestAssured.given;

import java.util.Hashtable;
import org.testng.annotations.Test;

import bloodstream.Suite;
import utilities.DataHandler;
import utilities.config;

public class poolConstituents extends Suite
{

	@Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
	public void Assert200(Hashtable<String,String> dataTable) {
		
		//config.log.debug(new Object() {}.getClass().getEnclosingMethod().getName()+ " Invoked");
		String Authorization = config.property.getProperty("LoginToken");
		String endpoint = dataTable.get("EndPoint");
	   given().relaxedHTTPSValidation().
	  header("Authorization",Authorization).
	  param("PoolID",dataTable.get("PoolID")).
	 when().get(endpoint).then().      
	 assertThat().statusCode(200); 				
 }

	@Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
	public void Assert404(Hashtable<String,String> dataTable)
	{
	//	config.log.debug(new Object() {}.getClass().getEnclosingMethod().getName()+ " Invoked");
		String Authorization = config.property.getProperty("LoginToken");
		String Endpoint = dataTable.get("EndPoint");
		
		given().relaxedHTTPSValidation().
		header("Authorization",Authorization). 
		param("PoolID",dataTable.get("PoolID")).
		when().get(Endpoint).then().     
		assertThat().statusCode(404);	
	}
	
	@Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
	public void Assert401(Hashtable<String,String> dataTable)
	{
	//	config.log.debug(new Object() {}.getClass().getEnclosingMethod().getName()+ " Invoked");
		String Endpoint = dataTable.get("EndPoint");
		String Authorization = "Invalid_Token";
		
		given().relaxedHTTPSValidation().
		header("Authorization",Authorization).
		param("PoolID",dataTable.get("PoolID")).
		when().get(Endpoint).then().     
		assertThat().statusCode(401);	
	}

	}
