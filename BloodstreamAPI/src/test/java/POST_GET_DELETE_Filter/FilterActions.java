package POST_GET_DELETE_Filter;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
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
import io.restassured.RestAssured;
import io.restassured.specification.ResponseSpecification;
import utilities.DataHandler;

public class FilterActions {

public static ResponseSpecification responseSpec;	
@BeforeTest
public void BeforeTest()
	{
	 
		RestAssured.useRelaxedHTTPSValidation(); 	
	}
	
@Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
public void POSTFilterAssert200(Hashtable<String,String> dataTable) throws Exception, ParseException {
	
	
responseSpec = auth.reuseAssert200();
       File file = new File(System.getProperty("user.dir")+"//payloads//SearchFilter_POSTConclToApprove.json");
       given().header("Authorization",auth.ValidAuth).body(file).when().post(dataTable.get("EndPoint")).
       then().body("result",is(true)).spec(responseSpec);
      
		 FileReader reader = new FileReader(System.getProperty("user.dir")+"//payloads//SearchFilter_POSTConclToApprove.json");
      JSONParser jsonParser = new JSONParser();
 	  JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
  	 String filterName = (String) jsonObject.get("filterName");
  	System.out.println("filterName is "+ filterName);
  	String BasicBase64format  = Base64.getEncoder().encodeToString(filterName.getBytes()); 
  	System.out.println("BasicBase64format is "+ BasicBase64format);
 
try
{
FileInputStream fileISP= new FileInputStream(new File(System.getProperty("user.dir")+"//testData//POST_GET_DELETE_Filter.xlsx")); //Read the spreadsheet that needs to be updated

XSSFWorkbook wb= new  XSSFWorkbook(fileISP); 
     
          XSSFSheet worksheet = wb.getSheetAt(0); 
          Cell cell = null; 
        cell = worksheet.getRow(6).getCell(2);   
    cell =worksheet.getRow(10).getCell(2);  
          cell.setCellValue(BasicBase64format); 
          fileISP.close(); 
          FileOutputStream output_file =new FileOutputStream(new File(System.getProperty("user.dir")+"//testData//POST_GET_DELETE_Filter.xlsx"));  //Open FileOutputStream to write updates
          wb.write(output_file); 
      
          output_file.close();  
} 
catch (Exception e) 
{
    e.printStackTrace();
}
}

@Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
public void GETFilterAssert200(Hashtable<String,String> dataTable) {
	
	responseSpec = auth.reuseAssert200();
        given().header("Authorization",auth.ValidAuth).param("gridName",dataTable.get("gridName")).
        param("filterName",dataTable.get("filterName")).when().get(dataTable.get("EndPoint")).
        then().body("result",is(true)).spec(responseSpec).log().all(); 
  
}


@Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
public void DeleteFilterAssert200(Hashtable<String,String> dataTable) {
   
	responseSpec =  auth.reuseAssert200();
       given().header("Authorization",auth.ValidAuth).param("gridName",dataTable.get("gridName")).
       param("filterName",dataTable.get("filterName")).when().delete(dataTable.get("EndPoint")).
       then().body("result",is(true)).spec(responseSpec).log().all();
    
}
}



