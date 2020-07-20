package layoutConfig;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Hashtable;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
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

public class LayoutConfig_GetTab extends Suite{ 
public static ResponseSpecification responseSpec;	

	
	
	@Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
	public void Assert200(Hashtable<String,String> dataTable) {
		
	responseSpec =  auth.reuseAssert200();
	Integer nv =given().header("Authorization",auth.ValidAuth).param("tabName",dataTable.get("tabName")).
			when().get(dataTable.get("EndPoint")).then().
			spec(responseSpec).extract().path("data.content.version");
	        System.out.println("newversion:" +nv);
  
	        try{
	   	
	   	   FileInputStream fileISP1= new FileInputStream(new File(System.getProperty("user.dir")+"//testData//layoutConfig.xlsx"));
	   	XSSFWorkbook wb1= new  XSSFWorkbook(fileISP1); 
        XSSFSheet worksheet1 = wb1.getSheetAt(3); 
        
      //  DataFormatter formatter = new DataFormatter();
      
		Cell cell1 = null; 
		
		cell1 = worksheet1.getRow(2).getCell(1);   
		//Object cellvalue = formatter.formatCellValue(worksheet1.getRow(2).getCell(2));
		cell1.setCellValue(nv); 
		 
		/*if(cell1.getCellType() == Cell.CELL_TYPE_STRING)
	        retVal=c.getStringCellValue();
	        else {
	            retVal = String.valueOf(c.getNumericCellValue());
	        }*/
				fileISP1.close();
			//	fileISP2.close();
				FileOutputStream output_file1 =new FileOutputStream(new File(System.getProperty("user.dir")+"//testData//layoutConfig.xlsx"));  
				//FileOutputStream output_file2 =new FileOutputStream(new File(System.getProperty("user.dir")+"//testData//DeleteCustomFilter.xlsx"));  
				
				wb1.write(output_file1); 
				
			//	wb2.write(output_file2); 
			    output_file1.close();  
			   // output_file2.close();  
		      } catch (IOException e) {
		       	e.printStackTrace();
			   
			
			}
			}
			

	
	 @Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
     public void Assert404(Hashtable<String,String> dataTable) {
		
    responseSpec =   auth.reuseAssert404();
	given().header("Authorization",auth.ValidAuth).param("tabName",dataTable.get("tabName")).when().get(dataTable.get("EndPoint")).
	 then().body("result",is(false)).spec(responseSpec); 	
 }
	 
	@Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
    public void Assert401(Hashtable<String,String> dataTable) {
		
		 responseSpec =  auth.reuseAssert401();
	given().header("Authorization",auth.InvalidAuth).param("tabName",dataTable.get("tabName")).when().get(dataTable.get("EndPoint")).
	then().spec(responseSpec); 
  }
	
	
}


