package Drop3defects;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

import java.io.File;
import java.util.Hashtable;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import ReusableCode.auth;
import io.restassured.RestAssured;
import io.restassured.specification.ResponseSpecification;
import utilities.DataHandler;

public class POST_Defaultfilter {
	public static ResponseSpecification responseSpec;	

    @BeforeTest
	 public void BeforeTest(){
		{
			RestAssured.useRelaxedHTTPSValidation(); 
		}
 }	
@Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
public void Assert200(Hashtable<String,String> dataTable) {
	
	    auth.reuseAssert200();
        File file = new File(System.getProperty("user.dir")+"//payloads//DefaultFilter_DonationInfo.json");
		given().header("Authorization",auth.ValidAuth).body(file).when().post(dataTable.get("EndPoint")).
		then().body("result",is(true)).spec(responseSpec);
   
}

}
