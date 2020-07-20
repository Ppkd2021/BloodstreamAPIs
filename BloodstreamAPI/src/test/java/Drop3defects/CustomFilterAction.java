package Drop3defects;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

import java.io.File;
import java.util.Hashtable;

import CustomFilterList.DonationInfo;
import DeleteCustomFilter.DelDonationInfo;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import ReusableCode.auth;
import bloodstream.Suite;
import io.restassured.RestAssured;
import io.restassured.specification.ResponseSpecification;
import utilities.DataHandler;

public class CustomFilterAction extends Suite{ 
public static ResponseSpecification responseSpec;	

	   
	 @Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
	 public void Assert200(Hashtable<String,String> dataTable) throws Exception {
		
		    responseSpec = auth.reuseAssert200();
	        File file = new File(System.getProperty("user.dir")+"//payloads//CustomFilter_DonationInfo.json");
			given().header("Authorization",auth.ValidAuth).body(file).when().post(dataTable.get("EndPoint")).
			then().body("result",is(true)).spec(responseSpec);
			
			
			DonationInfo i1 = new DonationInfo();
			i1.Assert200(dataTable);
			DelDonationInfo i2= new DelDonationInfo();
			i2.Assert200(dataTable);
			
	
	 }
	
}


