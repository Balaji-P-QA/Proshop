package org.pro;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ProductPurchase {

	static WebDriver driver;
	JavascriptExecutor js;

	@Parameters()
	@BeforeClass
	private void starting() {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();

		driver.get("http://dev.proshop.ae/");

		driver.manage().window().maximize();
	}

	@Test(priority = 1)
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

		WebElement allowCookies = driver.findElement(By.xpath("//span[text()='Allow Cookies']"));
		allowCookies.click();

		Reporter.log("Login in successfully");
	}

	@Test(priority = 2)
	private void productSelection() throws InterruptedException {

		int num = 2;
		int i = 0;

		String generalSearch = "";
		String brandsForPageWise = "yes";
		String particularCategory = "no";

		// generalSearch is equals to yes if block will execute,generalSearch is not
		// equal to yes else block will execute
		if (generalSearch.equals("yes")) {

			// click the search icon
			WebElement searchBtn = driver.findElement(By.xpath("//a[@id='search-btn']"));
			searchBtn.click();

			// enter the Product name
			WebElement productName = driver.findElement(By.xpath("(//input[@type='text'])[1]"));
			productName.sendKeys("215/65R15 96H ZE914 _ Falken");

			// click the search btn
			WebElement selected = driver.findElement(By.xpath("//i[@class='fa fa-search']"));
			selected.click();
			Reporter.log("required product entered in the search bar");

			// enter the product Quantity
			List<WebElement> quantity = driver.findElements(By.xpath("//input[@class='product-qty']"));
			quantity.get(num).clear();
			quantity.get(num).sendKeys("3");

			// click the add to cart
			List<WebElement> addtocart = driver.findElements(By.xpath("//span[text()='Add to cart']"));
			addtocart.get(num).click();

		} else if (brandsForPageWise.equals("yes")) {

			WebElement FloatingBtn = driver.findElement(By.xpath("//a[@id='floating-product-search']"));
			FloatingBtn.click();

			Thread.sleep(2000);

			WebElement selectForBrandsOption = driver
					.findElement(By.xpath("//a[@class='arrow-link tyre-brand-search']"));
			selectForBrandsOption.click();

			Thread.sleep(2000);

			WebElement brand = driver.findElement(By.xpath("//label[text()='Dunlop']"));
			brand.click();

			Thread.sleep(2000);

			WebElement searchNowLink = driver.findElement(By.xpath("(//a[@class='arrow-link tyre-search-now'])[5]"));
			searchNowLink.click();

			Thread.sleep(5000);

			WebElement itemsPerpageLink = driver.findElement(By.xpath("(//span[text()='Items Per Page'])[2]"));
			itemsPerpageLink.click();

			Thread.sleep(2000);

			WebElement perpage = driver.findElement(By.xpath("(//a[@class='am-filter-item-'])[12]"));
			perpage.click();

			Thread.sleep(7000);

			Poj p = new Poj(driver);

			for (int k = 0; k < p.submit.size(); k++) {

				String ExpectProductName = p.submit.get(k).getText();

				if (ExpectProductName.equals("265/50R22 112V PT5A | Dunlop")) {

					List<WebElement> specipicProduct = driver.findElements(By.xpath("//span[text()='Add to cart']"));
					js = (JavascriptExecutor) driver;
					js.executeScript("arguments[0].click();", specipicProduct.get(k));
					i = k;
					break;

				}

				if (k == p.submit.size() - 1) {

					try {

						driver.findElement(By.xpath("(//span[text()='Next'])[2]")).click();
						k = -1;
						Thread.sleep(5000);

					} catch (Exception e) {

						System.out.println("User Expected Product is not Available");
						i = -2;
						break;

					}

				}
				
				Thread.sleep(1000);

			}

		} else if (particularCategory.equals("yes")) {

			// click the Tyres header link
			WebElement tyres = driver.findElement(By.xpath("(//span[text()='TYRES'])[1]"));
			tyres.click();

			// click the Add to Cart from product label
			List<WebElement> product = driver.findElements(By.xpath("//span[text()='Add to cart']"));
			product.get(num).click();

		}

		driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(1));

		if (i != -2) {

			// after clicked the cart ,popup is validate here
			WebElement cartPopup = driver.findElement(By.xpath("//div[@class='smcqp-messages msg-success']"));

			// popup is displayed if block will execute, popup is not displayed else block
			// will execute
			if (cartPopup.isDisplayed()) {

				String viewCart = "yes";

				// viewCart is equals to yes if block will execute,viewCart is not equals to yes
				// else block will execute
				if (viewCart.equals("yes")) {

					// click the view cart option
					WebElement viewCartOption = driver.findElement(By.xpath("//a[@title='View Cart']"));
					viewCartOption.click();

				} else {

					// click the continue shopping option
					WebElement continueShopping = driver.findElement(By.xpath("//a[@title='Continue']"));
					continueShopping.click();
				}

			} else {

				// cart popup is not showing print these statement
				System.out.println("product is not added to your cart");
			}

		}

	}

	@Test(priority = 4, enabled = false)
	private void cartDetails() throws InterruptedException {

		// click the calendar
		WebElement calendar = driver.findElement(By.xpath("//div[@class='dropdwon-img']"));
		calendar.click();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

		// select the year
		WebElement year = driver.findElement(By.xpath("//select[@class='ui-datepicker-year']"));
		Select s = new Select(year);
		s.selectByVisibleText("2022");

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

		// select the month
		WebElement month = driver.findElement(By.xpath("//select[@class='ui-datepicker-month']"));
		Select s1 = new Select(month);
		s1.selectByValue("11");

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

		// select the date
		WebElement date = driver.findElement(By.xpath("(//a[@class='ui-state-default'])[6]"));
		date.click();

		// scroll down
		WebElement scr1 = driver.findElement(By.xpath("//div[text()=' Shopping Cart Details:']"));
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true)", scr1);

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

		// enter the coupon code
		WebElement couponcode = driver.findElement(By.xpath("//input[@name='coupon_code']"));
		couponcode.sendKeys("Automation@123");

		// click the apply button
		WebElement apply = driver.findElement(By.xpath("//button[@value='Apply Discount']"));
		apply.click();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

		// scroll Down
		WebElement scdown1 = driver.findElement(By.xpath("//label[@for='coupon_code']"));
		js.executeScript("arguments[0].scrollIntoView(true)", scdown1);
		Thread.sleep(3000);

		// enter the vehicle number
		WebElement vehicleNo = driver.findElement(By.id("vehicle-plate-number"));
		vehicleNo.sendKeys("KA 01 BA 1234");

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

		// select the vehicle Brand
		WebElement brand = driver.findElement(By.xpath("(//select[@class='form-control'])[1]"));
		Select s2 = new Select(brand);
		s2.selectByValue("bmw");

		// click the Continue To Review & Pay
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		WebElement pay = driver.findElement(By.xpath("//a[text()='Continue To Review & Pay']"));
		pay.click();
		Reporter.log("redirect to checkout page");
	}

	@Test(priority = 5, enabled = false)
	private void checkout() throws InterruptedException {

		Thread.sleep(4000);

		// scroll Down
		WebElement scrolldown = driver.findElement(By.xpath("//div[@class='ccavenuepay_frontend_logo']"));
		js.executeScript("arguments[0].scrollIntoView(true)", scrolldown);

		Thread.sleep(3000);

		// click the place order button
		WebElement place = driver.findElement(By.xpath("(//button[@class='action primary checkout'])[2]"));
		place.click();
		Reporter.log("place order successful");
	}

	@Test(priority = 6, enabled = false)
	private void Payment() throws InterruptedException {

		// scroll Down
		WebElement scrollDown = driver.findElement(By.xpath("(//div[@id='sectionheading'])[2]"));
		js.executeScript("arguments[0].scrollIntoView(true)", scrollDown);

		// enter the caredit card Number
		WebElement cardNumber = driver.findElement(By.xpath("//input[@id='creditCardNumber']"));
		cardNumber.sendKeys("5123450000000008");

		// select the expire month
		WebElement month = driver.findElement(By.xpath("//select[@name='expiryMonthCreditCard']"));
		Select s = new Select(month);
		s.selectByIndex(1);

		// select the expire year
		WebElement year = driver.findElement(By.xpath("//select[@name='expiryYearCreditCard']"));
		Select s1 = new Select(year);
		s1.selectByVisibleText("2039");

		// enter the CCV Number
		WebElement ccvNum = driver.findElement(By.xpath("//input[@name='CVVNumberCreditCard']"));
		ccvNum.sendKeys("100");

		// click the make payment
		WebElement makePayment = driver.findElement(By.xpath("(//span[text()='Make Payment'])[1]"));
		makePayment.click();

		Thread.sleep(4000);
		Reporter.log("Payment proceed successful");
	}

	@Test(priority = 7, enabled = false)
	private void acsEmulator() throws InterruptedException {

		// click the submit button
		WebElement submit = driver.findElement(By.xpath("//input[@type='submit']"));
		submit.click();
		Thread.sleep(4000);
	}

	@Test(priority = 8, enabled = false)
	private void success() {

		// validate the success page ,title is equal to expected if block will
		// execute,title is not equal to expected else block will execute
		String expectTitle = "Success Page";

		String actualTitle = driver.getTitle();

		if (actualTitle.equals(expectTitle)) {

			System.out.println("Product ordered successful");
			Reporter.log("Product ordered successful");

		} else {

			System.out.println("product order not successful");
			Reporter.log("product order not successful");

		}

	}

	@Test(priority = 9, enabled = false)
	private void end() throws InterruptedException {

		Thread.sleep(4000);
		driver.quit();

	}

}
