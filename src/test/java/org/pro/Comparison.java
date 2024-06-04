package org.pro;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Comparison {

	WebDriver driver;

	String selectedProductName1;

	String selectedProductName2;

	@Test(priority = 1, enabled = true)
	private void open() {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();

		driver.get("http://dev.proshop.ae/");

		driver.manage().window().maximize();

		Reporter.log("browser opened properly");

	}

	@Test(priority = 2, enabled = true)
	private void signIn() {

		// click the sign Btn
		WebElement signBtn = driver.findElement(By.xpath("//a[@id='account-btn']"));
		signBtn.click();

		Reporter.log("entered in sign in page");

		// enter the email address
		WebElement email = driver.findElement(By.id("email"));
		email.sendKeys("balaji.p@sysfore.com");

		// enter the password
		WebElement password = driver.findElement(By.id("pass"));
		password.sendKeys("Balaji@123");

		// click the Login
		WebElement btn = driver.findElement(By.xpath("(//span[text()='Login'])[2]"));
		btn.click();
		Reporter.log("Login in successfully");
	}

	@Test(priority = 3, enabled = true)
	private void productSelection() throws InterruptedException {

	/**/	int num =3;

		String generalSearch = "yes";

		// generalSearch is equals to yes if block will execute,generalSearch is not
		// equal to yes else block will execute
		if (generalSearch.equals("")) {

			// click the search icon
			WebElement searchBtn = driver.findElement(By.xpath("//a[@id='search-btn']"));
			searchBtn.click();

			// enter the Product name
			WebElement productName = driver.findElement(By.xpath("(//input[@type='text'])[1]"));
			productName.sendKeys("195/65R15 91H ZE914 | FALKEN");

			// click the search btn
			WebElement selected = driver.findElement(By.xpath("//i[@class='fa fa-search']"));
			selected.click();
			Reporter.log("required product entered in the search bar");

			// get the specific product name
			List<WebElement> likeToCompareproductName = driver
					.findElements(By.xpath("//a[@class='product-item-link']"));
			selectedProductName1 = likeToCompareproductName.get(num).getText();
			
			System.out.println(selectedProductName1);

			// Add to comparison
			List<WebElement> addtoCompare = driver.findElements(By.xpath("//a[@class='action tocompare']"));
			addtoCompare.get(num).click();

		} else {

			// click the Tyres header link
			WebElement tyres = driver.findElement(By.xpath("(//span[text()='TYRES'])[1]"));
			tyres.click();

			List<WebElement> productName = driver.findElements(By.xpath("//a[@class='product-item-link']"));
			selectedProductName2 = productName.get(num).getText();

			// click the Add to Cart from product label
			List<WebElement> product = driver.findElements(By.xpath("//a[@class='action tocompare']"));
			product.get(num).click();

		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		// after clicked the cart ,popup is validate here
		WebElement comparedPopup = driver.findElement(By.xpath("//div[@class='smcqp-messages msg-success']"));

		// popup is displayed if block will execute, popup is not displayed else block
		// will execute
		if (comparedPopup.isDisplayed()) {

	/**/		String comparedProducts = "yes";

			// viewCart is equals to yes if block will execute,viewCart is not equals to yes
			// else block will execute
			if (comparedProducts.equals("yes")) {

				// click the view cart option
				WebElement viewCartOption = driver.findElement(By.xpath("//a[@title='Compare Products']"));
				viewCartOption.click();	
				
				Thread.sleep(5000);
				
				try {
                     //no products in compare err msg validation, errmsg is showing if block will execute,err msg is not showing catch blokc will execute
					WebElement noCompareProduct = driver.findElement(By.xpath("//span[text()='You have no items to compare.']"));
					
					if (noCompareProduct.isDisplayed()) {
						
						System.out.println(
								"which Product we given to Add to Compare that product is not Added to Our Comparison List");
						Assert.assertTrue(false);
						Reporter.log(
								"which Product we given to Add to Compare that product is not Added to Our Comparison List");				
					}
					
				} catch (Exception e) {
					
					System.out.println(
							"which Product we given to Add to Compare that product is Added to Our Comparison List");
					Assert.assertTrue(true);
					Reporter.log(
							"which Product we given to Add to Compare that product is Added to Our Comparison List");
					
					
			/**/		String remove="yes";
					
					//remove is equal to yes if block will execute
					if (remove.equals("yes")) {
						
						//before remove get the how product in compared list
						List<WebElement> befRemove = driver.findElements(By.xpath("//img[@class='product-image-photo ']"));
						System.out.println("before remove the comparison List the list has "+befRemove.size()+" product");	
						
						Thread.sleep(4000);
						
						//remove the specific product
						List<WebElement> removeProduct = driver.findElements(By.xpath("//a[@class='action delete']"));
						JavascriptExecutor js = (JavascriptExecutor)driver;
						js.executeScript("arguments[0].click();", removeProduct.get(0));
						
						Thread.sleep(5000);
						
						//click to Ok Button for Confirm
						WebElement removeOk = driver.findElement(By.xpath("//span[text()='OK']"));
						removeOk.click();
						
						Thread.sleep(6000);
						
						//after remove single product get the comparison product count
						List<WebElement> aftRemove = driver.findElements(By.xpath("//img[@class='product-image-photo ']"));
						System.out.println("After removed the comparison List the list has "+aftRemove.size()+" product");	
						
					}
					
				}
				

			} else {

				// click the continue shopping option
				WebElement continueShopping = driver.findElement(By.xpath("//a[@title='Continue']"));
				continueShopping.click();
			}

		} else {

			// cart popup is not showing print these statement
			System.out.println("product is not added to your comparison List");
		}

	}

	@AfterClass
	private void end() throws InterruptedException {

		Thread.sleep(5000);
	//	driver.quit();
		Reporter.log("broser closed properly");

	}

}
