package Drop2defects;

import static io.restassured.RestAssured.given;

import java.util.Hashtable;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utilities.DataHandler;
import utilities.config;

public class AssaySorting74979 {
	
	@Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
	public void DonationInfoAssaySort(Hashtable<String,String> dataTable) {
		
		 String Authorization = config.property.getProperty("LoginToken");
		 String endpoint = dataTable.get("EndPoint");
		Response response1 = given().relaxedHTTPSValidation().header("Authorization",Authorization).param("GroupStatus",dataTable.get("GroupStatus")).param("Sort",dataTable.get("Sort")).
		when().get(endpoint).then().assertThat().statusCode(200).and().contentType(ContentType.JSON).extract().response();  
		Assert.assertTrue(response1.getTimeIn(TimeUnit.SECONDS)<=20,"Response Time is not within limit");
		System.out.println(response1.getTimeIn(TimeUnit.SECONDS));
	}
}
