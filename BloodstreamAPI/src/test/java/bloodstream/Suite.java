package bloodstream;
import utilities.DataHandler;
import utilities.config;

import static io.restassured.RestAssured.given;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Base64;
import java.util.Hashtable;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Suite 
{
	@BeforeSuite
	public void BloodstreamLogin(ITestContext ctx)
	{
		
		config.init();
		//config.log.debug(new String(new char[100]).replace("\0", "-"));
		//config.log.debug("Suite name: "+ctx.getCurrentXmlTest().getSuite().getName());
		login();
	}
	
	@BeforeTest
	public void BeforeTest(){
		{
			RestAssured.useRelaxedHTTPSValidation(); 
		}
	}
	public void login()
	{
		RestAssured.baseURI = config.property.getProperty("LocalServer");
		RestAssured.basePath = config.property.getProperty("BasePath");
		//config.log.debug("login invoked");
		
		String authValue = "Basic "+getCredential();
		//config.log.debug("authValue: "+authValue);
		
		Response response = given().relaxedHTTPSValidation().header("Authorization",authValue)
		.post(config.property.getProperty("Login")).then().assertThat().statusCode(200).extract().response();
		String loginToken = response.header("LoginHash");
		
		config.property.setProperty("LoginToken","Basic "+loginToken );
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
		
		String credential = config.property.getProperty("UserName")+":"+config.property.getProperty("Password");
		String encodeCredential = encoder.encodeToString(credential.getBytes());  
		
		return encodeCredential;
	}
	
	
	
	/*@AfterSuite
	public void PostSuitProcess(ITestContext ctx) 
	{
		config.log.debug(ctx.getCurrentXmlTest().getSuite().getName()+" Finished");
		config.log.debug(new String(new char[100]).replace("\0", "-"));	
	}*/
	
}
