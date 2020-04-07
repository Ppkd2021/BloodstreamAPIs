package Drop3;

import static io.restassured.RestAssured.given;

import java.util.Hashtable;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.Test;

import ReusableCode.auth;
import io.restassured.response.Response;
import utilities.DataHandler;

public class LayoutConfig_DeleteTab {

 @Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
 public void Assert200(Hashtable<String,String> dataTable) {
	

   Response response = given().relaxedHTTPSValidation().
   header("Authorization",auth.ValidAuth).param("tabName",dataTable.get("tabName")).param("version",dataTable.get("version")).when().delete(dataTable.get("EndPoint")).
   then().assertThat().statusCode(200).extract().response(); 
   Assert.assertTrue(response.getTimeIn(TimeUnit.SECONDS)<=10,"Response Time is not within limit");
   System.out.println(response.getTimeIn(TimeUnit.SECONDS));
}


 @Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
 public void Assert401(Hashtable<String,String> dataTable)
{
		Response response = given().relaxedHTTPSValidation().
		header("Authorization",auth.InvalidAuth).param("tabName",dataTable.get("tabName")).param("version",dataTable.get("version")).when().delete(dataTable.get("EndPoint")).
		then().assertThat().statusCode(401).extract().response(); 
		Assert.assertTrue(response.getTimeIn(TimeUnit.SECONDS)<=10,"Response Time is not within limit");
		System.out.println(response.getTimeIn(TimeUnit.SECONDS));
}
 
 @Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
 public void Assert400(Hashtable<String,String> dataTable) {
		
		Response response = given().relaxedHTTPSValidation().
		header("Authorization",auth.ValidAuth).param("tabName",dataTable.get("tabName")).param("version",dataTable.get("version")).when().delete(dataTable.get("EndPoint")). 
		then().assertThat().statusCode(400).extract().response(); 
		Assert.assertTrue(response.getTimeIn(TimeUnit.SECONDS)<=10,"Response Time is not within limit");
		System.out.println(response.getTimeIn(TimeUnit.SECONDS));
		
}

 @Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
 public void Assert409(Hashtable<String,String> dataTable) {
		
	
		Response response = given().relaxedHTTPSValidation().
		header("Authorization",auth.ValidAuth).param("tabName",dataTable.get("tabName")).param("version",dataTable.get("version")).when().delete(dataTable.get("EndPoint")). 
		then().assertThat().statusCode(409).extract().response(); 
		Assert.assertTrue(response.getTimeIn(TimeUnit.SECONDS)<=10,"Response Time is not within limit");
		System.out.println(response.getTimeIn(TimeUnit.SECONDS));
			
}

}
