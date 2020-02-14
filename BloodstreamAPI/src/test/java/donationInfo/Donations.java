package donationInfo;

import static io.restassured.RestAssured.given;

import java.lang.reflect.Array;
import java.util.Hashtable;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import bloodstream.Suite;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import utilities.DataHandler;
import utilities.config;

public class Donations extends Suite {
	
	@BeforeTest
	public void PreTestProcess() 
	{
		config.log.debug(new String(new char[100]).replace("\0", "-"));
		config.log.debug(this.getClass().getName()+ " Entered");
	}
	//Validate 200 Status code 
		@Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
		public void Assert200(Hashtable<String,String> dataTable) {
			
			config.log.debug(new Object() {}.getClass().getEnclosingMethod().getName()+ " Invoked");
			String Authorization = config.property.getProperty("LoginToken");
			String endpoint = dataTable.get("EndPoint");
		   given().relaxedHTTPSValidation().
					header("Authorization",Authorization).param("GroupStatus",dataTable.get("GroupStatus")).// Use this to add headers
					when().get(endpoint).then().      // Use this to specify the API path
					assertThat().statusCode(200); 
					
			 }
//Validate Flag Name for the Donation ID
	@Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
	public void FlagName(Hashtable<String,String> dataTable) {
		
		config.log.debug(new Object() {}.getClass().getEnclosingMethod().getName()+ " Invoked");
		String Authorization = config.property.getProperty("LoginToken");
		String endpoint = dataTable.get("EndPoint");
		String donationid = dataTable.get("DonationID");
		String flagname = dataTable.get("Flagname");
		Response response = given().relaxedHTTPSValidation().
				header("Authorization",Authorization).param("GroupStatus",dataTable.get("GroupStatus")).// Use this to add headers
				when().get(endpoint).then().      // Use this to specify the API path
				extract().response();
		String respon = response.asString();
		
		JsonPath x=new JsonPath(respon);
		int i=0;
		List orders = x.getList("data.content");
		
		for( i=0;i<orders.size();i++) {
			if(x.get("data.content["+i+"].TubeID".toString()).equals(donationid)){
				break;
				
			}
			
		}
		
	Assert.assertTrue(flagname.equals(x.get("data.content["+i+"].FlagName".toString())));
		 }
	}
