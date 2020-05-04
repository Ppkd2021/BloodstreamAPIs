package Drop2defects;

import static io.restassured.RestAssured.given;

import java.util.Hashtable;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import ReusableCode.auth;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;
import utilities.DataHandler;

public class AssaySorting74979 {
public static ResponseSpecification responseSpec;	
	@BeforeTest
	 public void BeforeTest(){
		{
			RestAssured.useRelaxedHTTPSValidation(); 
		}
	 }
	@Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
	public void DonationInfoAssaySort(Hashtable<String,String> dataTable) {
		
	   auth.reuseAssert200();	
	  given().header("Authorization",auth.ValidAuth).param("GroupStatus",dataTable.get("GroupStatus")).param("Sort",dataTable.get("Sort")).
	  when().get(dataTable.get("EndPoint")).then().spec(responseSpec);
	 	
	 }
}
