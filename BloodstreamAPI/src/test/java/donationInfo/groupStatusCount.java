package donationInfo;

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

      @Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
	 public void Assert200(Hashtable<String,String> dataTable) {
 		
    responseSpec = auth.reuseAssert200();	  
    given().header("Authorization",auth.ValidAuth).when().get(dataTable.get("EndPoint")).then().body("result",is(true)).spec(responseSpec);
    }
		
		
		@Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
		public void Assert401(Hashtable<String,String> dataTable)
		{
			
			responseSpec = auth.reuseAssert401();	
			given().header("Authorization",auth.InvalidAuth).when().get(dataTable.get("EndPoint")).then().spec(responseSpec);  
  			
  		}
		

	}

	


    //assertThat().body("messages.message[0]", Is.is("Successful")).
	//assertThat().body("content.groupStatus[0]", Is.is("all")).log().all().

/*
given().header("Authorization",auth.ValidAuth).when().get(dataTable.get("EndPoint")).then().
	assertThat().statusCode(200).and().contentType(ContentType.JSON).
	assertThat().body("data.content[0].groupStatus", Is.is("all")).
	assertThat().body("data.content[1].groupStatus", Is.is("donationwithouttestorders")).extract().response();
	
	JsonPath jsonPathEvaluator = response.jsonPath();
	jsonPathEvaluator.get("data.content[0].count");
	System.out.println("GroupStatusCount of Donation Info - all tab is: " + jsonPathEvaluator.get("data.content[0].count"));
	System.out.println("GroupStatusCount of Donation without test orders tab is: " + jsonPathEvaluator.get("data.content[1].count"));
	
	 Assert.assertTrue(response.getTimeIn(TimeUnit.MILLISECONDS)<=10000,"Response Time is not within limit");
	 System.out.println(response.getTimeIn(TimeUnit.MILLISECONDS));		
		*/