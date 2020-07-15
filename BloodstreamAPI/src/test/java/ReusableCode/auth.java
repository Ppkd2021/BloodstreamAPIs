package ReusableCode;
import static org.hamcrest.Matchers.lessThan;
import java.util.concurrent.TimeUnit;

import bloodstream.Suite;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;
import utilities.config;

public class auth {
	public static ResponseSpecification responseSpec;
	
	public static  String ValidAuth =  config.property.getProperty("LoginToken");
	public static String InvalidAuth = config.property.getProperty("InvalidToken"); 
	

public static  ResponseSpecification reuseAssert200(){
	
	ResponseSpecBuilder builder = new ResponseSpecBuilder();	
	builder.expectStatusCode(200).expectContentType(ContentType.JSON);
	builder.expectResponseTime(lessThan(4000L), TimeUnit.MILLISECONDS);
	responseSpec = builder.build();
	return responseSpec;
}
	
public static ResponseSpecification reuseAssert401(){
	
	ResponseSpecBuilder builder = new ResponseSpecBuilder();	
	builder.expectStatusCode(401);
	builder.expectResponseTime(lessThan(4000L), TimeUnit.MILLISECONDS);
	responseSpec = builder.build();
	return responseSpec;
}


public static ResponseSpecification reuseAssert400(){
	
	ResponseSpecBuilder builder = new ResponseSpecBuilder();		
	builder.expectStatusCode(400);
	builder.expectResponseTime(lessThan(4000L), TimeUnit.MILLISECONDS);
	responseSpec = builder.build();
	return responseSpec;
	
}

public static ResponseSpecification reuseAssert404(){
	
	ResponseSpecBuilder builder = new ResponseSpecBuilder();		
	builder.expectStatusCode(404);
	builder.expectResponseTime(lessThan(4000L), TimeUnit.MILLISECONDS);
	responseSpec = builder.build();
	return responseSpec;
	
}

public static ResponseSpecification reuseAssert409(){
	
	ResponseSpecBuilder builder = new ResponseSpecBuilder();		
	builder.expectStatusCode(409);
	builder.expectResponseTime(lessThan(4000L), TimeUnit.MILLISECONDS);
	responseSpec = builder.build();
	return responseSpec;
	
}

public static ResponseSpecification reuseAssert500(){
	
	ResponseSpecBuilder builder = new ResponseSpecBuilder();		
	builder.expectStatusCode(500);
	builder.expectResponseTime(lessThan(4000L), TimeUnit.MILLISECONDS);
	responseSpec = builder.build();
	return responseSpec;
	
}
}
//System.out.println();		

	//rsBuilder.expectBody("endpoint", equalTo("5"));


/*
@Test
public void expectingSpecificationMergesTheCurrentSpecificationWithTheSuppliedOne() throws Exception {
    final ResponseSpecBuilder builder = new ResponseSpecBuilder();
    builder.equals("56");
    builder.expectBody("", 4);
    .expectBody("endpoint", equalTo("5".getEndpoint()));
    
    builder.expectBody("store.book.size()", is(4)).expectStatusCode(200);
    final ResponseSpecification responseSpecification = builder.build();
    expect().specification(responseSpecification).body("store.book[0].author", equalTo("Nigel Rees")).when().get("/jsonStore");
}
.expectBody("result", equalTo("true"));
}














/*
ResponseSpecification responseSpec;
ResponseSpecBuilder rsBuilder = new ResponseSpecBuilder();
rsBuilder.expectStatusCode(200).expectContentType(ContentType.JSON);
rsBuilder.expectHeader("ContentType", "application/json; charset=utf-8");
responseSpec = rsBuilder.build();
 



/*public static void time(){
	ResponseSpecBuilder expectResponseTime(Matcher<Long> matcher, TimeUnit timeUnit) {
		  spec.time(matcher, timeUnit);
		  return this;
		}
	
	
	//rsBuilder.expectStatusCode(200).expectContentType(ContentType.JSON);
		rsBuilder.expectStatusLine(containsString("GOOD"));
		rsBuilder.expectResponseTime(lessThan(5000L), TimeUnit.MILLISECONDS).
		new ResponseSpecBuilder().expectBody("name", startsWith("good"));
	}

	//RestAssured.responseSpecification = new ResponseSpecBuilder().expectResponseTime(lessThan(5000L), TimeUnit.MILLISECONDS).expectStatusCode(401).build();
	
	//ResponseSpecBuilder rsBuilder = new ResponseSpecBuilder();
	//rsBuilder.expectStatusCode(200).expectContentType(ContentType.JSON);
	
}	
}
	//ResponseSpecification checkStatusCodeAndContentType = new ResponseSpecBuilder().
	
//ResponseSpecBuilder rsBuilder = new ResponseSpecBuilder();
//rsBuilder.expectStatusCode(401).expectContentType(ContentType.JSON);

//ResponseSpecification checkStatusCodeAndContentType = new ResponseSpecBuilder().



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
