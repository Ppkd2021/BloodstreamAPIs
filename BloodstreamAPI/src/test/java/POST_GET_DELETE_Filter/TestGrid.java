package POST_GET_DELETE_Filter;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.FileInputStream;
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
import io.restassured.RestAssured;
import io.restassured.specification.ResponseSpecification;
import utilities.DataHandler;

public class TestGrid {
	public static ResponseSpecification responseSpec;
	@BeforeTest
	public void BeforeTest()
		{
		 
			RestAssured.useRelaxedHTTPSValidation(); 	
		}
		
		@Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
		public void Assert200(Hashtable<String,String> dataTable) throws IOException {

			File file = new File(System.getProperty("user.dir")+"//payloads//SearchFilter_POSTTestInfo.json");
			responseSpec = auth.reuseAssert200(); 
			given().header("Authorization",auth.ValidAuth).body(file).when().post(dataTable.get("EndPoint")).
			then().extract().response();
			FileReader reader = new FileReader(System.getProperty("user.dir")+"//payloads//SearchFilter_POSTTestInfo.json");
		      JSONParser jsonParser = new JSONParser();
		      
		 	  try {
				JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
				String filterName = (String) jsonObject.get("filterName");
			 	System.out.println("filterName is "+ filterName);
			   	String BasicBase64format  = Base64.getEncoder().encodeToString(filterName.getBytes()); 
			   	System.out.println("BasicBase64format is "+ BasicBase64format);
			   	FileInputStream fileISP= new FileInputStream(new File(System.getProperty("user.dir")+"//testData//POST_GET_DELETE_Filter.xlsx")); //Read the spreadsheet that needs to be updated

			 	XSSFWorkbook wb= new  XSSFWorkbook(fileISP); 
			 	  
			 	XSSFSheet worksheet = wb.getSheetAt(2); 
		          Cell cell1 = null; 
		          Cell cell2 = null; 
		        cell1 = worksheet.getRow(6).getCell(2);   
		    cell2 =worksheet.getRow(10).getCell(2);  
		          cell1.setCellValue(BasicBase64format); 
		          cell2.setCellValue(BasicBase64format); 
		          fileISP.close(); 
		          FileOutputStream output_file =new FileOutputStream(new File(System.getProperty("user.dir")+"//testData//POST_GET_DELETE_Filter.xlsx"));  //Open FileOutputStream to write updates
		          wb.write(output_file); 
		      
		          output_file.close();  
			 	  
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		 	  
		}
		 	  
		 	 @Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
		 	public void GETFilterAssert200(Hashtable<String,String> dataTable) {
		 		
		 		responseSpec = auth.reuseAssert200();
		 	        given().header("Authorization",auth.ValidAuth).param("gridName",dataTable.get("gridName")).
		 	        param("filterName",dataTable.get("filterName")).when().get(dataTable.get("EndPoint")).
		 	       then().extract().response();
		 	}


		 	@Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
		 	public void DeleteFilterAssert200(Hashtable<String,String> dataTable) {
		 	   
		 		responseSpec =  auth.reuseAssert200();
		 	       given().header("Authorization",auth.ValidAuth).param("gridName",dataTable.get("gridName")).
		 	       param("filterName",dataTable.get("filterName")).when().delete(dataTable.get("EndPoint")).
		 	      then().extract().response();
		 	    
		 	}
		 	

		
		}

