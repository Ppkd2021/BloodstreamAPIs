package utilities;
import java.lang.reflect.Method;
import java.util.Hashtable;
import utilities.config;

import org.testng.annotations.DataProvider;

	 

public class DataHandler
{
	@DataProvider(name="dataProvider")
	public static Object[][] getDataObject(Method m) 
    {
		config.log.debug(new String(new char[100]).replace("\0", "-"));	
		config.log.debug("DataHandler Invoked");
		String testCaseName = m.getDeclaringClass().getName();
		String[] str = testCaseName.split("\\.", 2);
		String workbookName = str[0];
		
		String sheetName = str[1];
		String testName = m.getName();
		System.out.println(testName);
		config.log.debug("workbookName: "+workbookName+"   sheetName: "+sheetName+"   testName: "+testName);
		int testCaseRowNum=1;
		int cols=0;
		int rows=0;
		config.excel = new ExcelReader(System.getProperty("user.dir")+"//testData//"+workbookName+".xlsx");
		
		
		while(!config.excel.getCellData(sheetName, 0, testCaseRowNum).equalsIgnoreCase(testName)) {testCaseRowNum++;}
		config.log.debug("Test case starts from row number : "+testCaseRowNum);
		
		int colsStartRowNum=testCaseRowNum+1;
		int dataStartRowNum=colsStartRowNum+1;
		
		while(!config.excel.getCellData(sheetName, cols, colsStartRowNum).trim().equals("")){cols++;}
		config.log.debug("Total number of columns: "+cols);
		
		while(!config.excel.getCellData(sheetName, 0, dataStartRowNum+rows).trim().equals("")){rows++;}
		config.log.debug("Total number of rows: "+rows);
		

		Object[][] dataObject = new Object[rows][1];
		
		Hashtable<String,String> dataTable = null;

		for (int rowNum = dataStartRowNum; rowNum < dataStartRowNum+rows; rowNum++) 
        {
			dataTable = new Hashtable<String,String>();
			for (int colNum = 0; colNum < cols; colNum++) 
            {
				dataTable.put(config.excel.getCellData(sheetName, colNum, colsStartRowNum).trim(), config.excel.getCellData(sheetName, colNum, rowNum).trim());
				dataObject[rowNum - dataStartRowNum][0] = dataTable;
				
			}
		}
		
		config.log.debug("DataHandler Exited");
		config.log.debug(new String(new char[100]).replace("\0", "-"));	
		
		return dataObject;
	}
	
}
