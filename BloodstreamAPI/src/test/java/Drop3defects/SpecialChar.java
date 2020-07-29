package Drop3defects;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import org.testng.annotations.Test;

import ReusableCode.auth;
import bloodstream.Suite;
import io.restassured.specification.ResponseSpecification;
import utilities.DataHandler;

public class SpecialChar  extends Suite {
	public static ResponseSpecification responseSpec;
	
	
	@Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
	public void TestInfoFilterAssert200(Hashtable<String,String> dataTable) throws IOException {

			//Base64TestGrid.Encoder();
			File file = new File(System.getProperty("user.dir")+"//payloads//Drop3defect_TestInfoFilter.json");
			responseSpec = auth.reuseAssert200(); 
			given().header("Authorization",auth.ValidAuth).body(file).when().post(dataTable.get("EndPoint")).
			then().body("result",is(true)).spec(responseSpec);
	}
	
		@Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
		public void DeleteTestInfoFilterAssert200(Hashtable<String,String> dataTable) throws Exception {
  
	//URLDecoderTestInfo.Encoder();
			responseSpec = auth.reuseAssert200();
			given().header("Authorization",auth.ValidAuth).when().param("gridName",dataTable.get("gridName")).
			param("filterName",dataTable.get("filterName")).delete(dataTable.get("EndPoint"))
			.then().body("result",is(true)).spec(responseSpec);
  
}
	
	@Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
	public void LayoutConfigTabAssert200(Hashtable<String,String> dataTable) {
		
		responseSpec = auth.reuseAssert200();
		File file = new File(System.getProperty("user.dir")+"//payloads//Drop3defect_LayoutConfigTab.json");
	    given().header("Authorization",auth.ValidAuth).body(file).when().post(dataTable.get("EndPoint")).
	    then().body("result",is(true)).spec(responseSpec); 
	  }
	
	@Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
	 public void Assert200(Hashtable<String,String> dataTable) {
		
		    responseSpec =  auth.reuseAssert200();
	       given().header("Authorization",auth.ValidAuth).param("tabName",dataTable.get("tabName")).param("version",dataTable.get("version")).when().delete(dataTable.get("EndPoint")).
	       then().body("result",is(true)).spec(responseSpec); 	
	}

	
	
}
