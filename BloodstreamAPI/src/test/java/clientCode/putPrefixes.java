package clientCode;

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
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;
import utilities.DataHandler;

public class putPrefixes {
	public static ResponseSpecification responseSpec;
	@BeforeTest
	public void BeforeTest()
		{
		 
			RestAssured.useRelaxedHTTPSValidation(); 	
		}
		

		@Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
		public void Assert200(Hashtable<String,String> dataTable) {
			File file = new File(System.getProperty("user.dir")+"//payloads//putPrefixes.json");
			responseSpec = auth.reuseAssert200();
			given().header("Authorization",auth.ValidAuth).body(file).when().put(dataTable.get("EndPoint")).then().body("result",is(true)).spec(responseSpec); 
			
	}
	
		 @Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
        public void Assert400(Hashtable<String,String> dataTable) {
			 
	     responseSpec = auth.reuseAssert400();
		given().relaxedHTTPSValidation().
		header("Authorization",auth.ValidAuth).when().put(dataTable.get("EndPoint")).then().spec(responseSpec); 
			
		}
		 
		 @Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
          public void Assert401(Hashtable<String,String> dataTable) {
			 
			 responseSpec = auth.reuseAssert401();
		     given().relaxedHTTPSValidation().header("Authorization",auth.InvalidAuth).when().put(dataTable.get("EndPoint")).then().spec(responseSpec); 
			
				
			}
		 @Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
			public void Assert409(Hashtable<String,String> dataTable) {
			
			 try{
				 FileReader reader = new FileReader(System.getProperty("user.dir")+"//payloads//putPrefixes.json");
		         JSONParser jsonParser = new JSONParser();
			     JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
			     //System.out.println(jsonObject);
			     jsonObject.put("version", 0);
			  //   System.out.println(jsonObject);
			     FileWriter fW = new FileWriter(System.getProperty("user.dir")+"//payloads//putPrefixes.json");
				 fW.write(jsonObject.toString());
			     fW.close(); 
			     }catch (IOException ex) {ex.printStackTrace();}
			      catch (ParseException ex) { ex.printStackTrace();}
			        
			           
			 responseSpec = auth.reuseAssert409(); 
			 File file = new File(System.getProperty("user.dir")+"//payloads//putPrefixes.json");
			 given().header("Authorization",auth.ValidAuth).body(file).when().put(dataTable.get("EndPoint")).then().spec(responseSpec);
			
			
			}
}

