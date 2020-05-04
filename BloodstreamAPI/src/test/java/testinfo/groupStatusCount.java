package testinfo;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

import java.util.Hashtable;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ReusableCode.auth;
import bloodstream.Suite;
import io.restassured.RestAssured;
import io.restassured.specification.ResponseSpecification;
import utilities.DataHandler;

public class groupStatusCount extends Suite{
public static ResponseSpecification responseSpec;	

@BeforeTest
 public void BeforeTest(){
	{
		RestAssured.useRelaxedHTTPSValidation(); 
	}
}

	@Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
	public void Assert200(Hashtable<String,String> dataTable) {
		
	responseSpec =auth.reuseAssert200();	
	given().header("Authorization",auth.ValidAuth).when().get(dataTable.get("EndPoint")).
	then().body("result",is(true)).spec(responseSpec);
}	
	
	@Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
	public void Assert401(Hashtable<String,String> dataTable){

		responseSpec =auth.reuseAssert401();
	given().header("Authorization",auth.InvalidAuth).when().get(dataTable.get("EndPoint")).
	then().spec(responseSpec);
			
	}
}



/*
and().contentType(ContentType.JSON).
assertThat().body("data.content[0].groupStatus", Is.is("all")).
	assertThat().body("data.content[1].groupStatus", Is.is("pendingPooling")).
	assertThat().body("data.content[2].groupStatus", Is.is("pendingLysing")).
	assertThat().body("data.content[3].groupStatus", Is.is("pendingTesting")).
	assertThat().body("data.content[4].groupStatus", Is.is("poolAndLysateError")).
	assertThat().body("data.content[5].groupStatus", Is.is("completed")).extract().response();


JsonPath jsonPathEvaluator = response.jsonPath();


System.out.println("GroupStatusCount of Test Info - all tab is: " + jsonPathEvaluator.get("data.content[0].count"));
System.out.println("GroupStatusCount of pendingPooling tab is: " + jsonPathEvaluator.get("data.content[1].count"));
	
System.out.println("GroupStatusCount of pendingLysing tab is: " + jsonPathEvaluator.get("data.content[2].count"));
System.out.println("GroupStatusCount of pendingTesting tab is: " + jsonPathEvaluator.get("data.content[3].count"));
	
System.out.println("GroupStatusCount of poolAndLysateError tab is: " + jsonPathEvaluator.get("data.content[4].count"));
System.out.println("GroupStatusCount of completed tab is: " + jsonPathEvaluator.get("data.content[5].count"));
	
}	*/