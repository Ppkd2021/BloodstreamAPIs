package Drop2defects;

import static io.restassured.RestAssured.given;

import java.util.Hashtable;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import bloodstream.Suite;
import io.restassured.RestAssured;
import io.restassured.specification.ResponseSpecification;
import utilities.DataHandler;
import utilities.config;

public class UnauthorizedUser75039 extends Suite{ 
public static ResponseSpecification responseSpec;	

	
	@Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
	public void UnauthorizedAccess(Hashtable<String,String> dataTable) {
		
		//config.log.debug(new Object() {}.getClass().getEnclosingMethod().getName()+ " Invoked");
		String Authorization = config.property.getProperty("LoginToken");
		String endpoint = dataTable.get("EndPoint");
	   given().relaxedHTTPSValidation().
	   header("Authorization",Authorization).
	   param("donationId",dataTable.get("donationId")).
	   param("requestId",dataTable.get("requestId")).
	   when().get(endpoint).then().     
	   assertThat().statusCode(200); 
				
 }

}


	
	/*@Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
	public void Unauthlogin()
	{
		//RestAssured.baseURI = config.property.getProperty("LocalServer");
		//RestAssured.basePath = config.property.getProperty("BasePath");
		//config.log.debug("login invoked");
		
		String authValue = "Basic "+getCredential();
		//config.log.debug("authValue: "+authValue);
		
		Response response = given().relaxedHTTPSValidation().header("Authorization",authValue)
				.post(config.property.getProperty("Login")).then().assertThat().statusCode(200).extract().response();
		String UnauthloginToken = response.header("UnauthLoginHash");
		
		config.property.setProperty("UnauthloginToken","Basic "+UnauthloginToken );
		//config.log.debug("LoginToken: "+loginToken);
		
		try 
		{
			OutputStream outputStream;
			outputStream = new FileOutputStream(System.getProperty("user.dir")+"//global.properties");
			
			config.property.store(outputStream,"");
			outputStream.close();
		} 
		catch (Exception ex) 
		{
			//config.log.error(ex.toString());
        }
	}
	
	public String getCredential()
	{
		Base64.Encoder encoder = Base64.getUrlEncoder();  
		
		String credential = config.property.getProperty("UnauthUserName")+":"+config.property.getProperty("Password");
		String encodeCredential = encoder.encodeToString(credential.getBytes());  
		return encodeCredential;
	}
	
	*/
 
	