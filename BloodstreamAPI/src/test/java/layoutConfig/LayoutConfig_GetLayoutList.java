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

	@BeforeTest
	 public void BeforeTest(){
		{
			RestAssured.useRelaxedHTTPSValidation(); 
		}
	 }	
	
	@Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
	public void Assert200(Hashtable<String,String> dataTable) throws FileNotFoundException {
	
	responseSpec =  auth.reuseAssert200();
	String tab = given().header("Authorization",auth.ValidAuth).when().get(dataTable.get("EndPoint")).
    then().spec(responseSpec).extract().path("data.content[0]");
	System.out.println("tab is :" + tab);
	
	final FileReader reader = new FileReader(System.getProperty("user.dir")+"//payloads//postNewTab200.json");
	   
	 JSONParser jsonParser = new JSONParser();
	   try {
	   		JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
	   		String tabName = (String) jsonObject.get("tabName");
	   		//String BasicBase64format  = Base64.getEncoder().encodeToString(filterName.getBytes()); 
	   		FileInputStream fileISP= new FileInputStream(new File(System.getProperty("user.dir")+"//testData//layoutConfig.xlsx"));
	   			
	XSSFWorkbook wb= new  XSSFWorkbook(fileISP); 
    XSSFSheet worksheet1 = wb.getSheetAt(0); 
    XSSFSheet worksheet2 = wb.getSheetAt(3); 
	Cell cell1 = null; 
	Cell cell2 = null; 
	cell1 = worksheet1.getRow(2).getCell(1);   
	cell1.setCellValue(tab); 
    cell2 = worksheet2.getRow(2).getCell(1);   
	cell2.setCellValue(tab); 
	
	fileISP.close(); 
	FileOutputStream output_file =new FileOutputStream(new File(System.getProperty("user.dir")+"//testData//layoutConfig.xlsx"));  
	wb.write(output_file); 
    output_file.close();  
  } catch (IOException e) {
   	e.printStackTrace();
}   catch (ParseException e) {
	e.printStackTrace();
}	
    	
    }
	
	
	@Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
	public void Assert401(Hashtable<String,String> dataTable){
	
		responseSpec = auth.reuseAssert401();
		given().header("Authorization",auth.InvalidAuth).when().get(dataTable.get("EndPoint")).then().spec(responseSpec); 
	}

}




