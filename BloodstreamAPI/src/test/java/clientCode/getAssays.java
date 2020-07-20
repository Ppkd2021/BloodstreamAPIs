package clientCode;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;

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
import bloodstream.Suite;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;
import utilities.DataHandler;
import utilities.config;

public final class getAssays extends Suite{ 
public static ResponseSpecification responseSpec;


	 
 	@Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
 	public void Assert200(Hashtable<String,String> dataTable) {


 	responseSpec = auth.reuseAssert200();
	Integer nv = given().header("Authorization",auth.ValidAuth).when().get(dataTable.get("EndPoint")).
			then().body("data.content.assays.name",hasItems("Babesia","DENV","HEV","Parvo/HAV","dHBV","dHCV","dHIV","Ultrio Elite","WNV","ZIKV")).
			spec(responseSpec).extract().path("data.content.version");
	 	    System.out.println("newversion:" +nv);
	 	   
			
	  try{
		 FileReader reader = new FileReader(System.getProperty("user.dir")+"//payloads//putAssays200.json");
         JSONParser jsonParser = new JSONParser();
	     JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
	     jsonObject.put("version", nv);
	   // System.out.println(jsonObject);
	     FileWriter fW = new FileWriter(System.getProperty("user.dir")+"//payloads//putAssays200.json");
		 fW.write(jsonObject.toString());
		 fW.close(); 
	     }catch (IOException ex) {
         ex.printStackTrace();
         }catch (ParseException ex){
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




