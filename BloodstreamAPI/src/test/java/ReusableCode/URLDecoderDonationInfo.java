package ReusableCode;


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

public class URLDecoderDonationInfo {

	 public static void Encoder() throws IOException {

	  final FileReader reader = new FileReader(System.getProperty("user.dir")+"//payloads//SearchFilter_POSTDonationInfo.json");
		   
		  JSONParser jsonParser = new JSONParser();
		  try {
				JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
				String filterName = (String) jsonObject.get("filterName");
				String decodeURL = URLDecoder.decode( filterName, "UTF-8" );  

				FileInputStream fileISP1= new FileInputStream(new File(System.getProperty("user.dir")+"//testData//GETCustomFilter.xlsx"));
				FileInputStream fileISP2= new FileInputStream(new File(System.getProperty("user.dir")+"//testData//DeleteCustomFilter.xlsx"));

				XSSFWorkbook wb1= new  XSSFWorkbook(fileISP1); 
		        XSSFSheet worksheet1 = wb1.getSheetAt(1); 
				Cell cell1 = null; 
                cell1 = worksheet1.getRow(2).getCell(2);   
				cell1.setCellValue(decodeURL); 
			
				XSSFWorkbook wb2= new  XSSFWorkbook(fileISP2); 
		        XSSFSheet worksheet2 = wb2.getSheetAt(1); 
				Cell cell2 = null; 
				cell2 = worksheet2.getRow(2).getCell(2);  
				cell2.setCellValue(decodeURL); 
				

				fileISP1.close();
				fileISP2.close();
				FileOutputStream output_file1 =new FileOutputStream(new File(System.getProperty("user.dir")+"//testData//GETCustomFilter.xlsx"));  
				FileOutputStream output_file2 =new FileOutputStream(new File(System.getProperty("user.dir")+"//testData//DeleteCustomFilter.xlsx"));  
				
				wb1.write(output_file1); 
				wb2.write(output_file2); 
			    output_file1.close();  
			    output_file2.close();  
		      } catch (IOException e) {
		       	e.printStackTrace();
			}   catch (ParseException e) {
				e.printStackTrace();
			}
		}
		}