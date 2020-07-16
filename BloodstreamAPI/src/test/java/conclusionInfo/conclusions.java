package conclusionInfo;
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
import bloodstream.Suite;
import io.restassured.RestAssured;
import io.restassured.specification.ResponseSpecification;
import utilities.DataHandler;

public final class conclusions extends Suite{ 
public static ResponseSpecification responseSpec;

@BeforeTest
public void BeforeTest(){
{
	RestAssured.useRelaxedHTTPSValidation(); 
}
}
	

@Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
public void Assert200_pendingtoapprove(Hashtable<String,String> dataTable) {
		
 responseSpec = auth.reuseAssert200();
 Integer ConclusionID= given().header("Authorization",auth.ValidAuth).param("GroupStatus",dataTable.get("GroupStatus")).
 when().get(dataTable.get("EndPoint")).then().body("result",is(true)).spec(responseSpec).extract().path("data.content[0].ConclusionID");
 System.out.println("newID:" +ConclusionID);
	 
 Integer ConclusionRowVersion= given().header("Authorization",auth.ValidAuth).param("GroupStatus",dataTable.get("GroupStatus")).
 when().get(dataTable.get("EndPoint")).then().body("result",is(true)).spec(responseSpec).extract().path("data.content[0].ConclusionRowVersion");
 System.out.println("newrowversion:" +ConclusionRowVersion);
	
try{
		 FileReader reader = new FileReader(System.getProperty("user.dir")+"//payloads//postApprove200.json");
         JSONParser jsonParser = new JSONParser();
	     JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
	     //System.out.println(jsonObject);
	     jsonObject.put("conclusionRowID", ConclusionID);
	     jsonObject.put("conclusionRowVersion", ConclusionRowVersion);
	  //   System.out.println(jsonObject);
	     FileWriter fW = new FileWriter(System.getProperty("user.dir")+"//payloads//postApprove200.json");
		 fW.write(jsonObject.toString());
	     fW.close(); 
	     }catch (IOException ex) {ex.printStackTrace();}
	      catch (ParseException ex) { ex.printStackTrace();}
}
	@Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
	public void Assert200_approved(Hashtable<String,String> dataTable) {
		
	 responseSpec = auth.reuseAssert200();	
	given().header("Authorization",auth.ValidAuth).param("GroupStatus",dataTable.get("GroupStatus")).
	when().get(dataTable.get("EndPoint")).then().body("result",is(true)).spec(responseSpec);	
}
	
	@Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
    public void Assert400(Hashtable<String,String> dataTable) {
	
	responseSpec = auth.reuseAssert400();
    given().header("Authorization",auth.ValidAuth).param("GroupStatus",dataTable.get("GroupStatus")).
    when().get(dataTable.get("EndPoint")).then().body("result",is(false)).spec(responseSpec);  
}
	
	@Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
    public void Assert401(Hashtable<String,String> dataTable) {
	
	responseSpec = auth.reuseAssert401();
	given().header("Authorization",auth.InvalidAuth).param("GroupStatus",dataTable.get("GroupStatus")).
	when().get(dataTable.get("EndPoint")).then().spec(responseSpec);
		
	}
}