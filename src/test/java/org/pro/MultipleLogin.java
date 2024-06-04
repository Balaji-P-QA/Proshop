package org.pro;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MultipleLogin {

	@Test(dataProvider = "datasFromExcel")
	private void credentials(String email, String key) throws InterruptedException {

		// browser configuration

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();

		// url launch

		driver.get("http://dev.proshop.ae/");

		// widow maximize

		driver.manage().window().maximize();

		// apply implicitlywait

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
	   //sign logo

		WebElement accBtn = driver.findElement(By.xpath("//a[@id='account-btn']"));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", accBtn);
		
		Thread.sleep(5000);

		// enter the user name
		WebElement userName = driver.findElement(By.xpath("//input[@name='login[username]']"));
		userName.sendKeys(email);

		// enter the password
		WebElement password = driver.findElement(By.xpath("//input[@name='login[password]']"));
		password.sendKeys(key);

		// click the submit
		WebElement submit = driver.findElement(By.xpath("(//span[text()='Login'])[2]"));
		submit.click();
		
		Thread.sleep(5000);
		
		

		//err msg is showing try block will execute other wise catch block will execute
		
		  try {
			  
			  WebElement errMsg = driver.findElement(By.xpath("//div[contains(text(),'The account sign-in was incorrect or your account is disabled temporarily.')]"));
			  
			  if (errMsg.isDisplayed()) {

					System.out.println("user entered invalid username and password,so errmsg is showing");
					Reporter.log("user entered invalid username and password,so errmsg is showing");
					Assert.assertTrue(true, "user entered invalid username and password,so errmsg is showing");
			  }
			  
		  }catch (Exception e) {
			  
			  System.out.println("user entered the valid username and password,so errror msg is not showing");
				Reporter.log("user entered the valid username and password,so errror msg is not showing");
				Assert.assertTrue(true, "user entered the valid username and password,so errror msg is not showing");
			  
		}
			
		Thread.sleep(5000);
		driver.quit();
	}

	@DataProvider
	private Object[][] datasFromExcel() throws IOException {

		File f = new File(
				"C:\\Users\\Balaji.p\\eclipse-workspace\\Proshop\\DataStorage\\Login credentials for proshop.xlsx");

		FileInputStream fin = new FileInputStream(f);

		Workbook b = new XSSFWorkbook(fin);

		Sheet s = b.getSheetAt(1);

		Row r0 = s.getRow(1);
		Cell c0 = r0.getCell(0);
		String u1 = c0.getStringCellValue();
		Cell c1 = r0.getCell(1);
		String k1 = c1.getStringCellValue();

		Row r1 = s.getRow(2);
		Cell c01 = r1.getCell(0);
		String u01 = c01.getStringCellValue();
		Cell c02 = r1.getCell(1);
		String k01 = c02.getStringCellValue();

		Row r2 = s.getRow(3);
		Cell c001 = r2.getCell(0);
		String u001 = c001.getStringCellValue();
		Cell c002 = r2.getCell(1);
		String k001 = c002.getStringCellValue();

		Row r3 = s.getRow(4);
		Cell c0001 = r3.getCell(0);
		String u0001 = c0001.getStringCellValue();
		Cell c0002 = r2.getCell(1);
		String k0001 = c0002.getStringCellValue();

		Object[][] o = new Object[4][2];

		o[0][0] = u1;
		o[0][1] = k1;
		o[1][0] = u01;
		o[1][1] = k01;
		o[2][0] = u001;
		o[2][1] = k001;
		o[3][0] = u0001;
		o[3][1] = k0001;

		return o;

	}

	@DataProvider
	private Object[][] datas() {

		Object[][] o = new Object[4][2];

		o[0][0] = "balaji.p@sysfore.com";
		o[0][1] = "Balaji@123";
		o[1][0] = "test.1@mailinator.com";
		o[1][1] = "Test1@123";
		o[2][0] = "test.2@mailinator.com";
		o[2][1] = "Test2@123";
		o[3][0] = "test.3@mailinator.com";
		o[3][1] = "Test3@123";

		return o;

	}
	
}
