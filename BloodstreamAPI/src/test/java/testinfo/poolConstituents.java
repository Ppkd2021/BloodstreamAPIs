package testinfo;

import static io.restassured.RestAssured.given;

import java.lang.reflect.Array;
import java.util.Hashtable;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import bloodstream.Suite;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import utilities.DataHandler;
import utilities.config;

public class poolConstituents extends Suite
{
	@BeforeTest
	public void PreTestProcess() 
	{
		config.log.debug(new String(new char[100]).replace("\0", "-"));
		config.log.debug(this.getClass().getName()+ " Entered");
	}
	@Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
	public void Assert200(Hashtable<String,String> dataTable) {
		
		config.log.debug(new Object() {}.getClass().getEnclosingMethod().getName()+ " Invoked");
		String Authorization = config.property.getProperty("LoginToken");
		String endpoint = dataTable.get("EndPoint");
	   given().relaxedHTTPSValidation().
	  header("Authorization",Authorization).
	  param("PoolID",dataTable.get("PoolID")).
	 when().get(endpoint).then().      
	 assertThat().statusCode(200); 				
		 }
	
	
	@Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
	public void Assert400(Hashtable<String,String> dataTable)
	{
		config.log.debug(new Object() {}.getClass().getEnclosingMethod().getName()+ " Invoked");
		String Endpoint = dataTable.get("EndPoint");
		String Authorization = "LoginToken";
		
		given().relaxedHTTPSValidation().
		header("Authorization",Authorization). 
		param("PoolID",dataTable.get("PoolID")).
		when().get(Endpoint).then().     
		assertThat().statusCode(401);	
	}
	
	@Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
	public void Assert401(Hashtable<String,String> dataTable)
	{
		config.log.debug(new Object() {}.getClass().getEnclosingMethod().getName()+ " Invoked");
		String Endpoint = dataTable.get("EndPoint");
		String Authorization = "Invalid_Token";
		
		given().relaxedHTTPSValidation().
		header("Authorization",Authorization).
		param("PoolID",dataTable.get("PoolID")).
		when().get(Endpoint).then().     
		assertThat().statusCode(401);	
	}
	
	
	
	@AfterTest
	public void PostTestProcess() 
	{
		config.log.debug(this.getClass().getName()+ "  Exited");
		config.log.debug(new String(new char[100]).replace("\0", "-"));
		}


	}
