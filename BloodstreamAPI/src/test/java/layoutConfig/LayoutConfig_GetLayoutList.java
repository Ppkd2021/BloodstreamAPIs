package layoutConfig;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Base64;
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

public class LayoutConfig_GetLayoutList extends Suite{ 
public static ResponseSpecification responseSpec;	


	
	@Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
	public void Assert200(Hashtable<String,String> dataTable) throws FileNotFoundException {
	
	responseSpec =  auth.reuseAssert200();
	String tab = given().header("Authorization",auth.ValidAuth).when().get(dataTable.get("EndPoint")).
    then().spec(responseSpec).extract().path("data.content[1]");
	System.out.println("tab is :" + tab);
	}
	
/*	final FileReader reader = new FileReader(System.getProperty("user.dir")+"//payloads//postNewTab200.json");
	   
	 JSONParser jsonParser = new JSONParser();
	   try {
	   		//JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
	   		//String tabName = (String) jsonObject.get("tabName");
	   		//String BasicBase64format  = Base64.getEncoder().encodeToString(filterName.getBytes()); 
	   		FileInputStream fileISP1= new FileInputStream(new File(System.getProperty("user.dir")+"//testData//layoutConfig.xlsx"));
	   //		FileInputStream fileISP2= new FileInputStream(new File(System.getProperty("user.dir")+"//testData//PantherARTCard.xlsx"));
	   			
	XSSFWorkbook wb1= new  XSSFWorkbook(fileISP1); 
//	XSSFWorkbook wb2= new  XSSFWorkbook(fileISP2); 
    XSSFSheet worksheet1 = wb1.getSheetAt(0); 
    XSSFSheet worksheet2 = wb1.getSheetAt(3); 
    
 //   XSSFSheet worksheet1 = wb1.getSheetAt(0); 
    
	Cell cell1 = null; 
	Cell cell2 = null; 
//	Cell cell1b = null; 
	cell1 = worksheet1.getRow(2).getCell(1);   
	cell1.setCellValue(tab); 
    cell2 = worksheet2.getRow(2).getCell(1);   
	cell2.setCellValue(tab); 
//	cell1 = worksheet1.getRow(2).getCell(1);   
	//cell1.setCellValue(tab); 
	
	fileISP1.close(); 
	//fileISP2.close(); 
	FileOutputStream output_file1 =new FileOutputStream(new File(System.getProperty("user.dir")+"//testData//layoutConfig.xlsx"));  
//	FileOutputStream output_file2 =new FileOutputStream(new File(System.getProperty("user.dir")+"//testData//PantherARTCard.xlsx"));  

	wb1.write(output_file1); 
    output_file1.close();  
  //  wb2.write(output_file2); 
   // output_file2.close(); 
  } catch (IOException e) {
   	e.printStackTrace();

}	
    	
    }
	*/
	
	@Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
	public void Assert401(Hashtable<String,String> dataTable){
	
		responseSpec = auth.reuseAssert401();
		given().header("Authorization",auth.InvalidAuth).when().get(dataTable.get("EndPoint")).then().spec(responseSpec); 
	}

}




