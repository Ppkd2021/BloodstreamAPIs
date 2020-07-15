package ManualOrderEntry;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Hashtable;
import java.util.concurrent.TimeUnit;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import ReusableCode.auth;
import bloodstream.Suite;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.util.Hashtable;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import ReusableCode.auth;
import bloodstream.Suite;
import io.restassured.RestAssured;
import io.restassured.specification.ResponseSpecification;
import utilities.DataHandler;

public class GET_VerifiedSamples extends Suite {
	public static ResponseSpecification responseSpec;
	@BeforeTest
	public void BeforeTest()
		{
		 
			RestAssured.useRelaxedHTTPSValidation(); 	
		}


	@Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
	public void Assert200_SampleID(Hashtable<String,String> dataTable) {
		
    responseSpec = auth.reuseAssert200();	
	given().header("Authorization",auth.ValidAuth).param("prefix",dataTable.get("prefix")).
	param("sampleID",dataTable.get("sampleID")).when().get(dataTable.get("EndPoint")).then().body("result",is(true)).spec(responseSpec);
}		
	
	@Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
	public void Assert200_Range(Hashtable<String,String> dataTable) {
		
    responseSpec = auth.reuseAssert200();	
	given().header("Authorization",auth.ValidAuth).param("prefix",dataTable.get("prefix")).
	param("range",dataTable.get("range")).when().get(dataTable.get("EndPoint")).then().body("result",is(true)).spec(responseSpec);
}		
	
	 @Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
	 public void Assert400(Hashtable<String,String> dataTable) {
    responseSpec = auth.reuseAssert400();		 
    given().header("Authorization",auth.ValidAuth).param("prefix",dataTable.get("prefix")).param("range",dataTable.get("range")).
	when().get(dataTable.get("EndPoint")).then().body("result",is(false)).spec(responseSpec);
	 }	 
 
	 
    @Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
    public void Assert401(Hashtable<String,String> dataTable) {
    responseSpec = auth.reuseAssert401();		 
    given().header("Authorization",auth.InvalidAuth).param("prefix",dataTable.get("prefix")).
    param("sampleID",dataTable.get("sampleID")).param("range",dataTable.get("range")).
    when().get(dataTable.get("EndPoint")).then().spec(responseSpec);
   }	   	
 }