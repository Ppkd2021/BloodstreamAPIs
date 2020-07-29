package PostCustomFilter;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

import java.io.File;
import java.io.FileInputStream;
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
import org.testng.annotations.Test;

import ReusableCode.auth;
import bloodstream.Suite;
import io.restassured.specification.ResponseSpecification;
import utilities.DataHandler;

public class ArchivedSamples  extends Suite{ 
	public static ResponseSpecification responseSpec;
	
		
    @Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
		public void Assert200(Hashtable<String,String> dataTable) throws IOException, Exception {
    		
    	    //Base64DonationGrid.Encoder();
			File file = new File(System.getProperty("user.dir")+"//payloads//SearchFilter_POSTArchivedSample.json");
			responseSpec = auth.reuseAssert200(); 
			given().header("Authorization",auth.ValidAuth).body(file).when().post(dataTable.get("EndPoint")).
			then().body("result",is(true)).spec(responseSpec);
    
/*	@Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
		 	public void GETFilterAssert200(Hashtable<String,String> dataTable) {
            
		    responseSpec = auth.reuseAssert200();
		 	given().header("Authorization",auth.ValidAuth).param("gridName",dataTable.get("gridName")).
		 	param("filterName",dataTable.get("filterName")).when().get(dataTable.get("EndPoint")).
		 	then().spec(responseSpec);
	}

    @Test(dataProviderClass = DataHandler.class,dataProvider="dataProvider")
		 	public void DeleteFilterAssert200(Hashtable<String,String> dataTable) {
      
    	      responseSpec =  auth.reuseAssert200();
		 	  given().header("Authorization",auth.ValidAuth).param("gridName",dataTable.get("gridName")).
		 	  param("filterName",dataTable.get("filterName")).when().delete(dataTable.get("EndPoint")).
		 	  then().spec(responseSpec);
		 	    
	}
	*/	 	

    try{
    	FileReader reader = new FileReader(System.getProperty("user.dir")+"//payloads//SearchFilter_POSTArchivedSample.json");
 	   
   	 JSONParser jsonParser = new JSONParser();
   JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
		String filterName = (String) jsonObject.get("filterName");
   	   		//JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
   	   		//String tabName = (String) jsonObject.get("tabName");
	   FileInputStream fileISP1= new FileInputStream(new File(System.getProperty("user.dir")+"//testData//GETCustomFilter.xlsx"));
	XSSFWorkbook wb1= new  XSSFWorkbook(fileISP1); 
XSSFSheet worksheet1 = wb1.getSheetAt(1); 




//  DataFormatter formatter = new DataFormatter();

Cell cell1 = null; 

cell1 = worksheet1.getRow(2).getCell(2);   
//Object cellvalue = formatter.formatCellValue(worksheet1.getRow(2).getCell(2));
cell1.setCellValue(filterName); 
 
/*if(cell1.getCellType() == Cell.CELL_TYPE_STRING)
    retVal=c.getStringCellValue();
    else {
        retVal = String.valueOf(c.getNumericCellValue());
    }*/
		fileISP1.close();
	//	fileISP2.close();
		FileOutputStream output_file1 =new FileOutputStream(new File(System.getProperty("user.dir")+"//testData//GETCustomFilter.xlsx"));  
		//FileOutputStream output_file2 =new FileOutputStream(new File(System.getProperty("user.dir")+"//testData//DeleteCustomFilter.xlsx"));  
		
		wb1.write(output_file1); 
		
		
		FileInputStream fileISP2= new FileInputStream(new File(System.getProperty("user.dir")+"//testData//DeleteCustomFilter.xlsx"));
		XSSFWorkbook wb2= new  XSSFWorkbook(fileISP2); 
	XSSFSheet worksheet2 = wb2.getSheetAt(5); 
	Cell cell2 = null; 

	cell2 = worksheet2.getRow(2).getCell(2);   
	//Object cellvalue = formatter.formatCellValue(worksheet1.getRow(2).getCell(2));
	cell2.setCellValue(filterName); 
	 
	/*if(cell1.getCellType() == Cell.CELL_TYPE_STRING)
	    retVal=c.getStringCellValue();
	    else {
	        retVal = String.valueOf(c.getNumericCellValue());
	    }*/
			fileISP2.close();
		//	fileISP2.close();
			FileOutputStream output_file2 =new FileOutputStream(new File(System.getProperty("user.dir")+"//testData//DeleteCustomFilter.xlsx"));  
			
	 wb2.write(output_file2); 
	    output_file2.close();  
	   // output_file2.close();   
      } catch (IOException e) {
       	e.printStackTrace();
	   
	
	}
	}
	
}



