package org.pro;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ProductPurchaseExtent {

	public static ExtentReports getReport() {

		String path = System.getProperty("user.dir")+"\\ReportsCollection\\Repo.index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Product purchase result");
		reporter.config().setDocumentTitle("Product Purchase Report");
		
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester Name", "Balaji.p");
		return extent;
	}
	
}
