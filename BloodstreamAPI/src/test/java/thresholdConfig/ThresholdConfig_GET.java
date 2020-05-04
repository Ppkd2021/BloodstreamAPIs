package thresholdConfig;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
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

public class ThresholdConfig_GET {
public static ResponseSpecification responseSpec;	

	@BeforeTest
	 public void BeforeTest(){
		{
			RestAssured.useRelaxedHTTPSValidation(); 
		}
	}	
   @Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
	public void Assert200(Hashtable<String,String> dataTable) {
	   
	   responseSpec = auth.reuseAssert200();
	Integer nv=  given().header("Authorization",auth.ValidAuth).when().get(dataTable.get("EndPoint")).
			     then().spec(responseSpec).extract().path("data.content.Version");
    System.out.println("newversion:" +nv);			 	

	 
	  try{
		 FileReader reader = new FileReader(System.getProperty("user.dir")+"//payloads//ThresholdConfig_PUT.json");
      JSONParser jsonParser = new JSONParser();
	     JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
	     jsonObject.put("Version", nv);
	    System.out.println(jsonObject);
	     FileWriter fW = new FileWriter(System.getProperty("user.dir")+"//payloads//ThresholdConfig_PUT.json");
		 fW.write(jsonObject.toString());
	     fW.close(); 
	     }
   
   catch (IOException ex) {
       ex.printStackTrace();
   } catch (ParseException ex) {
       ex.printStackTrace();
   } 

}

		
		 @Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
      public void Assert401(Hashtable<String,String> dataTable) {
			
			responseSpec = auth.reuseAssert401();
			given().header("Authorization",auth.InvalidAuth).when().put(dataTable.get("EndPoint")).
			then().spec(responseSpec);
			}
}