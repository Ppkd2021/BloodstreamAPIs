package Drop2defects;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

import java.io.File;
import java.util.Hashtable;

import org.testng.annotations.Test;

import ReusableCode.auth;
import bloodstream.Suite;
import io.restassured.specification.ResponseSpecification;
import utilities.DataHandler;

public class Errormsg73596 extends Suite{ 
	public static ResponseSpecification responseSpec;
	
	@Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
	public void Assert400(Hashtable<String,String> dataTable){
	
		responseSpec = auth.reuseAssert400();
		File file = new File(System.getProperty("user.dir")+"//payloads//putTestInfogridLayout400.json");
	    given().header("Authorization",auth.ValidAuth).body(file).
	    when().put(dataTable.get("EndPoint")).then().body("result",is(false)).spec(responseSpec); 
	}
	
}
