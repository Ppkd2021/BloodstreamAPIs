package ReusableCode;

import org.testng.annotations.BeforeTest;

import utilities.config;

public final class auth {
	
	
	
  
	public static String ValidAuth =  config.property.getProperty("LoginToken");
	  public static String InvalidAuth = config.property.getProperty("InvalidToken"); 
	  
}
  
	/*public static void r(){
	ResponseSpecification checkStatusCodeAndContentType = new ResponseSpecBuilder().
        expectStatusCode(200).
        expectContentType(ContentType.JSON).
        build();*/

/*
	@Test()
	public static void useResponseSpecBuilder() {
		ResponseSpecBuilder rsBuilder = new ResponseSpecBuilder();
		
		rsBuilder.
			expectStatusCode(200).
				expectContentType(ContentType.JSON);
		
		ResponseSpecification respSpec = (ResponseSpecification) rsBuilder.build();
//	io.restassured.specification.ResponseSpecification checkStatusCodeAndContentType = new ResponseSpecBuilder().
	       // expectStatusCode(200).
	       // expectContentType(ContentType.JSON).build();*/
