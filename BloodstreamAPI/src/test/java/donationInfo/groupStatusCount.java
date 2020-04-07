package donationInfo;

import static io.restassured.RestAssured.given;

import java.util.Hashtable;
import java.util.concurrent.TimeUnit;

import org.hamcrest.core.Is;
import org.testng.Assert;
import org.testng.annotations.Test;

import ReusableCode.auth;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utilities.DataHandler;

public class groupStatusCount {



			@Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
		    public void Assert200(Hashtable<String,String> dataTable) {
 			
			Response response = given().relaxedHTTPSValidation().
 			header("Authorization",auth.ValidAuth).when().get(dataTable.get("EndPoint")).then().
 			assertThat().statusCode(200).and().contentType(ContentType.JSON).
 	 		assertThat().body("data.content[0].groupStatus", Is.is("all")).
 	 		assertThat().body("data.content[1].groupStatus", Is.is("donationwithouttestorders")).extract().response();
 	 		
 			JsonPath jsonPathEvaluator = response.jsonPath();
 			jsonPathEvaluator.get("data.content[0].count");
 			System.out.println("GroupStatusCount of Donation Info - all tab is: " + jsonPathEvaluator.get("data.content[0].count"));
 			System.out.println("GroupStatusCount of Donation without test orders tab is: " + jsonPathEvaluator.get("data.content[1].count"));
 			
			Assert.assertTrue(response.getTimeIn(TimeUnit.SECONDS)<=10,"Response Time is not within limit");
			System.out.println(response.getTimeIn(TimeUnit.SECONDS));		
		}
		
		
		@Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
		public void Assert401(Hashtable<String,String> dataTable)
		{
			
  			Response response = given().relaxedHTTPSValidation().
  			header("Authorization",auth.InvalidAuth).when().get(dataTable.get("EndPoint")).then().assertThat().statusCode(401).extract().response();  
 			Assert.assertTrue(response.getTimeIn(TimeUnit.SECONDS)<=10,"Response Time is not within limit");
 			System.out.println(response.getTimeIn(TimeUnit.SECONDS));
		}
		

		}

	


    //assertThat().body("messages.message[0]", Is.is("Successful")).
	//assertThat().body("content.groupStatus[0]", Is.is("all")).log().all().