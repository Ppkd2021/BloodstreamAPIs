package testinfo;

import static io.restassured.RestAssured.given;

import java.lang.reflect.Array;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import bloodstream.Suite;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import utilities.DataHandler;
import utilities.config;

public class poolDetails extends Suite
{
	
	@Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
	public void Assert200(Hashtable<String,String> dataTable) {
		
		 String Authorization = config.property.getProperty("LoginToken");
		 String endpoint = dataTable.get("EndPoint");
		 Response response1 = given().relaxedHTTPSValidation().
		 header("Authorization",Authorization).param("PoolID",dataTable.get("PoolID")).when().get(endpoint).
		 then().assertThat().statusCode(200).and().contentType(ContentType.JSON).extract().response();  
		 Assert.assertTrue(response1.getTimeIn(TimeUnit.SECONDS)<=10,"Response Time is not within limit");
		 System.out.println(response1.getTimeIn(TimeUnit.SECONDS));						
		 }

	@Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
	public void Assert404(Hashtable<String,String> dataTable)
	{
		String Authorization = config.property.getProperty("LoginToken");
		 String endpoint = dataTable.get("EndPoint");
		 Response response2 = given().relaxedHTTPSValidation().
		 header("Authorization",Authorization).param("PoolID",dataTable.get("PoolID")).when().get(endpoint).
		 then().assertThat().statusCode(404).extract().response();  
		 Assert.assertTrue(response2.getTimeIn(TimeUnit.SECONDS)<=10,"Response Time is not within limit");
		 System.out.println(response2.getTimeIn(TimeUnit.SECONDS));	
	}
	@Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
	public void Assert401(Hashtable<String,String> dataTable)
	{
		 String Authorization = config.property.getProperty("InvalidToken");
		 String endpoint = dataTable.get("EndPoint");
		 Response response3 = given().relaxedHTTPSValidation().
		 header("Authorization",Authorization).param("PoolID",dataTable.get("PoolID")).when().get(endpoint).
		 then().assertThat().statusCode(401).extract().response();  
		 Assert.assertTrue(response3.getTimeIn(TimeUnit.SECONDS)<=10,"Response Time is not within limit");
		 System.out.println(response3.getTimeIn(TimeUnit.SECONDS));
	}
}