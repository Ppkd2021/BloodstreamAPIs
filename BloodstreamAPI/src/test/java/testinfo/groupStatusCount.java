package testinfo;

import static io.restassured.RestAssured.given;

import java.util.Hashtable;
import java.util.concurrent.TimeUnit;

import org.hamcrest.core.Is;
import org.testng.Assert;
import org.testng.annotations.Test;

import bloodstream.Suite;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utilities.DataHandler;
import utilities.config;

public class groupStatusCount extends Suite
{

	@Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
	public void Assert200(Hashtable<String,String> dataTable) {
		
		String Authorization = config.property.getProperty("LoginToken");
		String endpoint = dataTable.get("EndPoint");
		
		Response response1 = given().relaxedHTTPSValidation().
				
		header("Authorization",Authorization).when().get(endpoint).then().assertThat().statusCode(200).
		and().contentType(ContentType.JSON).
		assertThat().body("data.content[0].groupStatus", Is.is("all")).
	 	assertThat().body("data.content[1].groupStatus", Is.is("pendingPooling")).
	 	assertThat().body("data.content[2].groupStatus", Is.is("pendingLysing")).
	 	assertThat().body("data.content[3].groupStatus", Is.is("pendingTesting")).
	 	assertThat().body("data.content[4].groupStatus", Is.is("poolAndLysateError")).
	 	assertThat().body("data.content[5].groupStatus", Is.is("completed")).extract().response();
		
		
		JsonPath jsonPathEvaluator = response1.jsonPath();
		Integer count1 = jsonPathEvaluator.get("data.content[0].count");
		Integer count2 = jsonPathEvaluator.get("data.content[1].count");
		Integer count3 = jsonPathEvaluator.get("data.content[2].count");
		Integer count4 = jsonPathEvaluator.get("data.content[3].count");
		Integer count5 = jsonPathEvaluator.get("data.content[4].count");
		Integer count6 = jsonPathEvaluator.get("data.content[5].count");
     
		System.out.println("GroupStatusCount of Test Info - all tab is: " + jsonPathEvaluator.get("data.content[0].count"));
		System.out.println("GroupStatusCount of pendingPooling tab is: " + jsonPathEvaluator.get("data.content[1].count"));
			
		System.out.println("GroupStatusCount of pendingLysing tab is: " + jsonPathEvaluator.get("data.content[2].count"));
		System.out.println("GroupStatusCount of pendingTesting tab is: " + jsonPathEvaluator.get("data.content[3].count"));
			
		System.out.println("GroupStatusCount of poolAndLysateError tab is: " + jsonPathEvaluator.get("data.content[4].count"));
		System.out.println("GroupStatusCount of completed tab is: " + jsonPathEvaluator.get("data.content[5].count"));
			
		Assert.assertTrue(response1.getTimeIn(TimeUnit.MILLISECONDS)<=5,"Response Time is not within limit");
		System.out.println(response1.getTimeIn(TimeUnit.MILLISECONDS));		
	}
	
	 		
	
	@Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
	public void Assert401(Hashtable<String,String> dataTable)
	{
		    String Authorization = config.property.getProperty("InvalidToken");
			String endpoint = dataTable.get("EndPoint");
			Response response2 = given().relaxedHTTPSValidation().
			header("Authorization",Authorization).when().get(endpoint).then().assertThat().statusCode(401).extract().response();  
			Assert.assertTrue(response2.getTimeIn(TimeUnit.MILLISECONDS)<=5,"Response Time is not within limit");
			System.out.println(response2.getTimeIn(TimeUnit.MILLISECONDS));
	}
	
	

	}
