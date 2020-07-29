package ToggleToIDT;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Hashtable;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import ReusableCode.auth;
import bloodstream.Suite;
import io.restassured.specification.ResponseSpecification;
import utilities.DataHandler;

public class TestInfoPP extends Suite{ 
	public static ResponseSpecification responseSpec;

	@Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
	public void TestInfoPendingPoolAssert200(Hashtable<String,String> dataTable) throws FileNotFoundException, IOException, ParseException {
	
		
		responseSpec =auth.reuseAssert200();
		String tubeID= given().header("Authorization",auth.ValidAuth).param("GroupStatus",dataTable.get("GroupStatus")).when().get(dataTable.get("EndPoint")).
		then().spec(responseSpec).extract().path("data.content[0].TubeID");
		String requestID= given().header("Authorization",auth.ValidAuth).param("GroupStatus",dataTable.get("GroupStatus")).when().get(dataTable.get("EndPoint")).
				then().spec(responseSpec).extract().path("data.content[0].RequestID");
		String assay= given().header("Authorization",auth.ValidAuth).param("GroupStatus",dataTable.get("GroupStatus")).when().get(dataTable.get("EndPoint")).
				then().spec(responseSpec).extract().path("data.content[0].AssayCode");		
		 Integer orderTestRowId= given().header("Authorization",auth.ValidAuth).param("GroupStatus",dataTable.get("GroupStatus")).when().get(dataTable.get("EndPoint")).
					then().spec(responseSpec).extract().path("data.content[0].OrderTestID");
		 Integer PoolID= given().header("Authorization",auth.ValidAuth).param("GroupStatus",dataTable.get("GroupStatus")).when().get(dataTable.get("EndPoint")).
					then().spec(responseSpec).extract().path("data.content[0].PoolID");
				
		 
		 
		 try{
			 FileReader reader = new FileReader(System.getProperty("user.dir")+"//payloads//PostChangetoIDT200.json");
			 FileInputStream fileISP1= new FileInputStream(new File(System.getProperty("user.dir")+"//testData//GTTeam.xlsx"));
				 XSSFWorkbook wb= new  XSSFWorkbook(fileISP1); 
		        XSSFSheet worksheet = wb.getSheetAt(1); 
				Cell cell = null;   
				cell = worksheet.getRow(2).getCell(1); 
				cell.setCellValue(PoolID); 
				fileISP1.close();
				FileOutputStream output_file =new FileOutputStream(new File(System.getProperty("user.dir")+"//testData//GTTeam.xlsx"));  
				wb.write(output_file); 
			 JSONParser jsonParser = new JSONParser();
		     JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
		     JSONArray jsonOrders=  (JSONArray) jsonObject.get("orders"); 
		     JSONObject  routeJsonObject = (JSONObject) jsonOrders.get(0);
		     System.out.println(routeJsonObject);
		     String requestID1 = (String) routeJsonObject.get("requestID"); 
		     //JSONObject jsonObjectreq = (JSONObject) routeJsonObject.get("requestID");
		    // jsonObject.put("requestID", requestID);
		     
		   System.out.println(requestID);
		     FileWriter fW = new FileWriter(System.getProperty("user.dir")+"//payloads//PostChangetoIDT200.json");
			 fW.write(jsonObject.toString());
			 fW.close(); 
			 
			 
			 
		     }catch (IOException ex) {
	         ex.printStackTrace();
	         }catch (ParseException ex){
	         ex.printStackTrace();
	        }  
	}
	
}
	
	
		/*  JSONParser jsonParser = new JSONParser();
		     
		     JSONObject obj = (JSONObject) jsonParser.parse(new FileReader(System.getProperty("user.dir")+"//payloads//PostChangetoIDT200.json"));
		     JSONArray jsonOrders=  (JSONArray) obj.get("orders"); //Gives Main JSoN

		        JSONObject  routeJsonObject = (JSONObject) jsonOrders.get(0);
		        System.out.println(routeJsonObject);// Route Array into JSON
		        routeJsonObject.put("tubeID", tubeID);
		        routeJsonObject.put("requestID", requestID);
		        routeJsonObject.put("assay", assay);
		        routeJsonObject.put("orderTestRowId", orderTestRowId);
		      
		        //JSONArray routeInfoArray = (JSONArray) routeJsonObject.get("routeInfo"); 
		     
	
		     
		     System.out.println(routeJsonObject);
		     
		     FileWriter fW = new FileWriter(System.getProperty("user.dir")+"//payloads//PostChangetoIDT200.json");
			 fW.write(obj.toString());
			 fW.close(); 
		    
	}
		     
	}  
		     */
		     
		     
		     
		     /*
		     
		        try{
					 FileReader reader = new FileReader(System.getProperty("user.dir")+"//payloads//PostChangetoIDT200.json");
			         JSONParser jsonParser = new JSONParser();
				     JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
				     System.out.println(jsonObject);
				    
				    // JSONObject ordersobj = (JSONObject) jsonObject.get("orders");
				   
		     
		     
		     
		        String tubeIDobj =String.valueOf((String) ordersobj.get("tubeID"));
	            System.out.println(tubeID);
	            ordersobj.put("tubeID", tubeID);
		     System.out.println(tubeIDobj);
		     
		     JSONObject objorderTestRowId = (JSONObject) jsonObject.get("orderTestRowId");
		     JSONObject objRequestID = (JSONObject) jsonObject.get("RequestID");
		     JSONObject objassay = (JSONObject) jsonObject.get("assay");
		     
		     
		     jsonObject.put("tubeID", TubeID);
		    System.out.println("tubeID is" + TubeID);
		     
		     jsonObject.put("orderTestRowId", OrderTestID);
		     System.out.println("OrderTestID is" + OrderTestID);
		     jsonObject.put("requestID", RequestID);
		     System.out.println("RequestID is" + RequestID);
		     jsonObject.put("assay", AssayCode);
		     System.out.println("AssayCode is" + AssayCode);
		    System.out.println(jsonObject);
		     FileWriter fW = new FileWriter(System.getProperty("user.dir")+"//payloads//PostChangetoIDT200.json");
			 fW.write(jsonObject.toString());
			 fW.close(); 
		     }catch (IOException ex) {
	         ex.printStackTrace();
	         }catch (ParseException ex){
	         ex.printStackTrace();
	        }  
}*/
		 
		 
/*		 
		 
		 JSONParser jsonParser = new JSONParser();
		 JSONObject obj = (JSONObject) jsonParser.parse(new FileReader(System.getProperty("user.dir")+"//payloads//PostChangetoIDT200.json"));
		 String value = (String) obj.get("tubeID");
		  System.out.println(value);
		 obj.put("tubeID", tubeID);
		  System.out.println(value);
		 
	}
		 
} */
