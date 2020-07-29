package CustomFilterList;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

import java.util.Hashtable;

import org.testng.annotations.Test;

import ReusableCode.auth;
import bloodstream.Suite;
import io.restassured.specification.ResponseSpecification;
import utilities.DataHandler;

public class ArchivedSamples extends Suite{ 
	public static ResponseSpecification responseSpec;	

	
	@Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
	public void Assert200(Hashtable<String,String> dataTable) {
		
		responseSpec = auth.reuseAssert200();
	   given().header("Authorization",auth.ValidAuth).when().param("gridName",dataTable.get("gridName")).
	   get(dataTable.get("EndPoint")).then().body("result",is(true)).spec(responseSpec);
	}

	@Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
	public void Assert400(Hashtable<String,String> dataTable)
	{
		responseSpec =  auth.reuseAssert400();
			given().header("Authorization",auth.ValidAuth).when().param("gridName",dataTable.get("gridName")).
		   get(dataTable.get("EndPoint")).then().body("result",is(false)).spec(responseSpec);
		    	
	}


	@Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
	public void Assert401(Hashtable<String,String> dataTable)
	{
		responseSpec =  auth.reuseAssert401();
		    given().header("Authorization",auth.InvalidAuth).when().param("gridName",dataTable.get("gridName")).
		   get(dataTable.get("EndPoint")).then().spec(responseSpec);
	 }
	}


