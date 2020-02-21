package gridLayout;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.util.Hashtable;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import utilities.DataHandler;
import utilities.config;

public class putDonationInfogridLayout {

	@BeforeTest
	public void PreTestProcess() 
	{
		//config.log.debug(new String(new char[100]).replace("\0", "-"));
		//config.log.debug(this.getClass().getName()+ " Entered");
	}
	//Validate 200 Status code 
		@Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
		public void Assert200(Hashtable<String,String> dataTable) {
			File file = new File(System.getProperty("user.dir")+"//payload//global.properties");
			config.log.debug(new Object() {}.getClass().getEnclosingMethod().getName()+ " Invoked");
			String Authorization = config.property.getProperty("LoginToken");
			String endpoint = dataTable.get("EndPoint");
			
		   given().relaxedHTTPSValidation().
		   header("Authorization",Authorization).param("gridName",dataTable.get("gridName")).
		   body(file).
		   when().put(endpoint).then().     
		   assertThat().statusCode(200); 
					
	 }
	
		@Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
		public void Assert400(Hashtable<String,String> dataTable)
		{
			//config.log.debug(new Object() {}.getClass().getEnclosingMethod().getName()+ " Invoked");
			String Endpoint = dataTable.get("EndPoint");
			String Authorization = config.property.getProperty("LoginToken");
			System.out.println(Authorization);
			
			given().relaxedHTTPSValidation().
			header("Authorization",Authorization). 
			param("gridName",dataTable.get("gridName")).
			
			when().get(Endpoint).then().     
			assertThat().statusCode(200);	
		}
		
	 
		  @Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
          public void Assert401(Hashtable<String,String> dataTable) {
  			
  			config.log.debug(new Object() {}.getClass().getEnclosingMethod().getName()+ " Invoked");
  			String Authorization = config.property.getProperty("InvalidToken");
  			String endpoint = dataTable.get("EndPoint");
  		    given().relaxedHTTPSValidation().
  			header("Authorization",Authorization).
  			param("gridName",dataTable.get("gridName")).
  			
  			when().get(endpoint).then().      
  			assertThat().statusCode(401); 
  					
  			 }
  		
}
