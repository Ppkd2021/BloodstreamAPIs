package session;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ReusableCode.auth;
import bloodstream.Suite;
import io.restassured.RestAssured;
import io.restassured.specification.ResponseSpecification;
import utilities.DataHandler;

public class heartbeat extends Suite{ 
public static ResponseSpecification responseSpec;	

    
	
	@Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
	public void Assert200(Hashtable<String,String> dataTable) throws Exception {
		
		FileReader reader = new FileReader(System.getProperty("user.dir")+"//payloads//global.properties");
		 JSONParser jsonParser = new JSONParser();
		  try {
				JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
				String LoginToken = (String) jsonObject.get("LoginToken");
				String Header  = LoginToken;
				FileInputStream fileISP1= new FileInputStream(new File(System.getProperty("user.dir")+"//testData//session.xlsx"));
				//FileInputStream fileISP2= new FileInputStream(new File(System.getProperty("user.dir")+"//testData//layoutConfig.xlsx"));

				XSSFWorkbook wb1= new  XSSFWorkbook(fileISP1); 
		        XSSFSheet worksheet1 = wb1.getSheetAt(1); 
				Cell cell1 = null; 
				//Cell cell2 = null; 
				cell1 = worksheet1.getRow(2).getCell(1);   
				//cell2 =worksheet.getRow(2).getCell(2);  
				cell1.setCellValue(Header); 
				//cell2.setCellValue(BasicBase64format); 
				
				fileISP1.close();
				FileOutputStream output_file1 =new FileOutputStream(new File(System.getProperty("user.dir")+"//testData//session.xlsx"));  
				wb1.write(output_file1); 
				 output_file1.close();  
				   // output_file2.close();  
			      } catch (IOException e) {
			       	e.printStackTrace();
				}   catch (ParseException e) {
					e.printStackTrace();
				}
				
				
		
		
		
		
		
		
		
		
		
		
		
		
		responseSpec =  auth.reuseAssert200();
		 given().header("Authorization",auth.ValidAuth).when().post(dataTable.get("EndPoint")).
		 then().spec(responseSpec);
	}

	 @Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
      public void Assert401(Hashtable<String,String> dataTable) {
    
			responseSpec =  auth.reuseAssert401();
		 given().header("Authorization",auth.InvalidAuth).when().post(dataTable.get("EndPoint")).
		 then().spec(responseSpec);
	 }
		
}

