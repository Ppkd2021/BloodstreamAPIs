package POST_GET_DELETE_Filter;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Base64;
import java.util.Hashtable;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import ReusableCode.Base64ConcToAprove;
import ReusableCode.Base64TestGrid;
import ReusableCode.auth;
import bloodstream.Suite;
import io.restassured.RestAssured;
import io.restassured.specification.ResponseSpecification;
import utilities.DataHandler;

public class TestGrid  extends Suite {
public static ResponseSpecification responseSpec;
	@BeforeTest
	public void BeforeTest()
		{
		 
			RestAssured.useRelaxedHTTPSValidation(); 	
		}
		
		@Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
		public void PostAssert200(Hashtable<String,String> dataTable) throws IOException {

				Base64TestGrid.Encoder();
				File file = new File(System.getProperty("user.dir")+"//payloads//SearchFilter_POSTTestInfo.json");
				responseSpec = auth.reuseAssert200(); 
				given().header("Authorization",auth.ValidAuth).body(file).when().post(dataTable.get("EndPoint")).
				then().spec(responseSpec);
				
		}
		 	  
		 	 @Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
		 	public void GETFilterAssert200(Hashtable<String,String> dataTable) {
		 		
		 		responseSpec =  auth.reuseAssert200();
		 	    given().header("Authorization",auth.ValidAuth).param("gridName",dataTable.get("gridName")).
		 	    param("filterName",dataTable.get("filterName")).when().get(dataTable.get("EndPoint")).
		 	    then().spec(responseSpec).extract();
		 	    
		 	}


		 	@Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
		 	public void DeleteFilterAssert200(Hashtable<String,String> dataTable) {
		 	   
		 	    responseSpec =  auth.reuseAssert200();
		 	    given().header("Authorization",auth.ValidAuth).param("gridName",dataTable.get("gridName")).
		 	    param("filterName",dataTable.get("filterName")).when().delete(dataTable.get("EndPoint")).
		 	    then().extract().response();
		 	    
		 	}
		 	

		
		}

