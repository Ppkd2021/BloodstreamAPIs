package plugins;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Calendar;
import java.util.Date;
 
import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;
 
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class HtmlReport implements IReporter {
    private ExtentReports report;
 
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        report = new ExtentReports(outputDirectory + File.separator + "HtmlReport.html", true);
 int testpassed =0;
 int testskipped =0;
 int testfailed=0;
 int totaltests=0;
        for (ISuite suite : suites) {
            Map<String, ISuiteResult> result = suite.getResults();
 
            for (ISuiteResult r : result.values()) {
                ITestContext context = r.getTestContext();
 
                buildTestNodes(context.getPassedTests(), LogStatus.PASS);
                buildTestNodes(context.getFailedTests(), LogStatus.FAIL);
                buildTestNodes(context.getSkippedTests(), LogStatus.SKIP);
                
               testpassed =context.getPassedTests().getAllMethods().size();
               testskipped = context.getSkippedTests().getAllMethods().size();
               testfailed = context.getFailedTests().getAllMethods().size();
               totaltests = testpassed + testskipped + testfailed;
               
            }
            Properties props = new Properties();
            try {
				FileOutputStream fos = new FileOutputStream(outputDirectory+"//APItest.properties");
				props.setProperty("TotalTestPassed",  Integer.toString(testpassed));
		        props.setProperty("TotalTestSkipped",  Integer.toString(testskipped));
		        props.setProperty("TotalTestFailed",  Integer.toString(testfailed));
		        props.setProperty("TotalTests",  Integer.toString(totaltests));
		        try {
					props.store(fos,"");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
 
        report.flush();
        report.close();
    }
 
    private void buildTestNodes(IResultMap tests, LogStatus status) {
        ExtentTest test;
 
        if (tests.size() > 0) {
            for (ITestResult result : tests.getAllResults()) {
                test = report.startTest(result.getTestClass().getName().split("\\.")[1]+" -> "+result.getMethod().getMethodName() );
                
                test.getTest().setStartedTime(getTime(result.getStartMillis()));
                test.getTest().setEndedTime(getTime(result.getEndMillis()));
 
  
                for (String group : result.getMethod().getGroups())
                    test.assignCategory(group);
 
                String message = "Test " + status.toString().toLowerCase() + "ed";
 
                if (result.getThrowable() != null)
                    message = result.getThrowable().getMessage();
 
                test.log(status, message);
 
                report.endTest(test);
            }
        }
    }
    private Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();        
    }
 
}