package gridLayout;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.util.Hashtable;
import org.testng.annotations.Test;

import bloodstream.Suite;
import utilities.DataHandler;
import utilities.config;

public class putTestInfogridLayout extends Suite
{
	
	
	@Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
	public void Assert200(Hashtable<String,String> dataTable) {
		
		File file = new File(System.getProperty("user.dir")+"//payloads//putTestInfogridLayout.json");
		String Authorization = config.property.getProperty("LoginToken");
		String endpoint = dataTable.get("EndPoint");
	   given().relaxedHTTPSValidation().
	  header("Authorization",Authorization).
	  param("gridName",dataTable.get("gridName")).
	  body(file).
	 when().get(endpoint).then().      
	 assertThat().statusCode(200); 				
		 }
	
	
	@Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
	public void Assert400(Hashtable<String,String> dataTable)
	{
		
		String Endpoint = dataTable.get("EndPoint");
		String Authorization =config.property.getProperty("LoginToken");
		
		given().relaxedHTTPSValidation().
		header("Authorization",Authorization). 
		param("gridName",dataTable.get("gridName")).
		when().get(Endpoint).then().     
		assertThat().statusCode(400);	
	}
	
	@Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
	public void Assert401(Hashtable<String,String> dataTable)
	{
		
		String Endpoint = dataTable.get("EndPoint");
		String Authorization = "Invalid_Token";
		
		given().relaxedHTTPSValidation().
		header("Authorization",Authorization). 
		param("gridName",dataTable.get("gridName")).
		when().get(Endpoint).then().     
		assertThat().statusCode(401);	
	}
	


	}
