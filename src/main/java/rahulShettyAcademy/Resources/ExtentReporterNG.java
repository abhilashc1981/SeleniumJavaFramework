package rahulShettyAcademy.Resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	
	public static ExtentReports getReportObject() {
		
		String path = System.getProperty("user.dir") + "//reports//index.html";
		ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(path);
		extentSparkReporter.config().setReportName("Web Automation Results");
		extentSparkReporter.config().setDocumentTitle("Test Results");
		
		ExtentReports extentReport = new ExtentReports();
		extentReport.attachReporter(extentSparkReporter);
		extentReport.setSystemInfo("Tester", "Rahul Shetty");
		return extentReport;
		
	}
}
