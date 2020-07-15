package ReusableCode;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Base64;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Base64WorklistConc {
public static void Encoder() throws IOException {

	FileReader reader = new FileReader(System.getProperty("user.dir")+"//payloads//SearchFilter_POSTWorklistConclusions.json");
    JSONParser jsonParser = new JSONParser();
    
	  try {
		JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
		String filterName = (String) jsonObject.get("filterName");
	 	System.out.println("filterName is "+ filterName);
	   	String BasicBase64format  = Base64.getEncoder().encodeToString(filterName.getBytes()); 
	   	System.out.println("BasicBase64format is "+ BasicBase64format);
	   	FileInputStream fileISP= new FileInputStream(new File(System.getProperty("user.dir")+"//testData//POST_GET_DELETE_Filter.xlsx")); //Read the spreadsheet that needs to be updated

	 	XSSFWorkbook wb= new  XSSFWorkbook(fileISP); 
	 	  
	 	XSSFSheet worksheet = wb.getSheetAt(4); 
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
	e.printStackTrace();
	} catch (ParseException e) {
	e.printStackTrace();
	}	
	  
}
}
	

