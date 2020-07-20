package layoutConfig;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URLDecoder;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import bloodstream.Suite;

public class DataEntryTab extends Suite{ 
	
	public static void Updater() throws IOException {
		  final FileReader reader = new FileReader(System.getProperty("user.dir")+"//payloads//postNewTab200.json");
			   
			  JSONParser jsonParser = new JSONParser();
			  try {
					JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
					String tabName = (String) jsonObject.get("tabName");
				//	String decodeURL = URLDecoder.decode( filterName, "UTF-8" );  

					FileInputStream fileISP1= new FileInputStream(new File(System.getProperty("user.dir")+"//testData//layoutConfig.xlsx"));
					//FileInputStream fileISP2= new FileInputStream(new File(System.getProperty("user.dir")+"//testData//layoutConfig.xlsx"));

					XSSFWorkbook wb1= new  XSSFWorkbook(fileISP1); 
			        XSSFSheet worksheet1 = wb1.getSheetAt(0); 
					Cell cell1 = null; 
					//Cell cell2 = null; 
					cell1 = worksheet1.getRow(2).getCell(1);   
					//cell2 =worksheet.getRow(2).getCell(2);  
					cell1.setCellValue(tabName); 
					//cell2.setCellValue(BasicBase64format); 
					
					
					XSSFWorkbook wb2= new  XSSFWorkbook(fileISP1); 
			        XSSFSheet worksheet2 = wb2.getSheetAt(3); 
					Cell cell2 = null; 
					cell2 = worksheet2.getRow(2).getCell(1);  
					cell2.setCellValue(tabName); 
					

					fileISP1.close();
				//	fileISP2.close();
					FileOutputStream output_file1 =new FileOutputStream(new File(System.getProperty("user.dir")+"//testData//layoutConfig.xlsx"));  
					//FileOutputStream output_file2 =new FileOutputStream(new File(System.getProperty("user.dir")+"//testData//DeleteCustomFilter.xlsx"));  
					
					wb1.write(output_file1); 
					wb2.write(output_file1);
				//	wb2.write(output_file2); 
				    output_file1.close();  
				   // output_file2.close();  
			      } catch (IOException e) {
			       	e.printStackTrace();
				}   catch (ParseException e) {
					e.printStackTrace();
				}
				}
				}
			
			
			
		
