package ChangeConclusions;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Hashtable;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ReusableCode.auth;
import io.restassured.RestAssured;
import io.restassured.specification.ResponseSpecification;
import utilities.DataHandler;

public class ChangeAnyConclusions {

	public static ResponseSpecification responseSpec;
	@BeforeTest
	public void BeforeTest()
		{
		 
			RestAssured.useRelaxedHTTPSValidation(); 	
		}
		
		@Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
		public void Assert200(Hashtable<String,String> dataTable) {

			File file = new File(System.getProperty("user.dir")+"//payloads//ChangeAnyConclusions.json");
			responseSpec = auth.reuseAssert200(); 
			given().header("Authorization",auth.ValidAuth).body(file).when().post(dataTable.get("EndPoint")).
			then().body("result",is(true)).spec(responseSpec);
	}


		@Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
		public void Assert401(Hashtable<String,String> dataTable) {

			responseSpec = auth.reuseAssert401();
			given().header("Authorization",auth.InvalidAuth).when().post(dataTable.get("EndPoint")).then().spec(responseSpec);
			
			
	}
		@Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
		public void Assert400(Hashtable<String,String> dataTable) {

			responseSpec = auth.reuseAssert401();
			given().header("Authorization",auth.InvalidAuth).when().post(dataTable.get("EndPoint")).then().spec(responseSpec);
		}
		
		
		@Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
		public void Assert409(Hashtable<String,String> dataTable) {

			responseSpec = auth.reuseAssert401();
			given().header("Authorization",auth.InvalidAuth).when().post(dataTable.get("EndPoint")).then().spec(responseSpec);
		}

	}