package donationInfo;
import utilities.config;
import org.testng.annotations.Test;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import static io.restassured.RestAssured.given;
import java.util.Hashtable;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import utilities.DataHandler;
import bloodstream.Suite;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


public class Sampletest extends Suite
{
	
	@BeforeTest
	public void PreTestProcess() 
	{
		//config.log.debug(new String(new char[100]).replace("\0", "-"));
		//config.log.debug(this.getClass().getName()+ " Entered");
	}

	
	@Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
	public void TestExample(Hashtable<String,String> dataTable)
	{
		// Log the test method name; Log file: logs/report.log
		//config.log.debug(new Object() {}.getClass().getEnclosingMethod().getName()+ " Invoked");
		
		//Load the data from Global.Properties file and Excel-sheet
		//Use this to access Global Properties
		String Authorization = config.property.getProperty("LoginToken");
		
		//Use this format when dealing with Strings
		String GroupStatus = dataTable.get("GroupStatus");
		
		 //Use this format when dealing with Integers
		int PageSize = (int) Double.parseDouble(dataTable.get("PageSize"));
		
		//Use this format when dealing with Integers
		int Page = (int) Double.parseDouble(dataTable.get("Page"));
		
		//Use this format when dealing with Strings
		String Sort = dataTable.get("Sort");
		
		// Log the data extracted before making the API request
		//config.log.debug("GroupStatus: "+GroupStatus+" PageSize: "+PageSize+" Page: "+Page+" Sort: "+Sort);
		
		RestAssured.baseURI = config.property.getProperty("LocalServer");
		RestAssured.port =  Integer.parseInt(config.property.getProperty("Port"));
		
		// Make the API request
		/*Response response = given().
		header("Authorization",Authorization). // Use this to add headers
		param("GroupStatus",GroupStatus).      // Use this to add query parameters
		param("PageSize",PageSize).			   // Multiple parameters/headers can be added by repeating the keyword
		param("Page",Page).
		param("Sort",Sort).
		when().get(donationInfo.getDonations()).then().      // Use this to specify the API path
		assertThat().statusCode(200).						 // Use this to assert status code (optional)
		extract().response();                                // Use this to get the response object
		
		
		// Convert the response object into JSON Path
		JsonPath jsonPathEvaluator = response.jsonPath();
		
		// Directly call all the top level JSON properties 
		Boolean result = jsonPathEvaluator.get("result");
		
		// For nested JSON traverse each of its ancestor nodes till you get to the desired property
		Integer page = jsonPathEvaluator.get("data.paging.page");
		
		// For Arrays, traverse to the array and specify the index then call the property like before
		String donationId = jsonPathEvaluator.get("data.content[0].DonationID");
		
		// Log the response values before asserting
		config.log.debug("result: "+result+" donationId: "+donationId+" page: "+page);
 
		
		// In case the method is supposed to be tested multiple times 
		if ((int) Double.parseDouble(dataTable.get("Iteration")) == 1)
		{
			//For asserting boolean
			Assert.assertTrue(result);
			
			//For asserting Numbers
			Assert.assertTrue(page==2);
			
			//For asserting Strings
			Assert.assertTrue(donationId.equalsIgnoreCase("CLSMASKDL010002"));
		}
		
		// In case the method is supposed to be tested multiple times
		if ((int) Double.parseDouble(dataTable.get("Iteration")) == 2)
		{
			//For asserting boolean
			Assert.assertTrue(result);
			
			//For asserting Numbers
			Assert.assertTrue(page==1);
			
			//For asserting Strings
			Assert.assertTrue(donationId.equalsIgnoreCase("ITZ-6440001"));
		}
	}
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

	
	@AfterTest
	public void PostTestProcess() 
	{
		config.log.debug(this.getClass().getName()+ "  Exited");
		config.log.debug(new String(new char[100]).replace("\0", "-"));
	}*/
	}
}
