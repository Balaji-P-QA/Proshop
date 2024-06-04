package snippet;

public class Snippet {
	@BeforeTest
		private void start() throws InterruptedException {
	
		
			ChromeDriver driver = new ChromeDriver();
	
			driver.get("http://dev.proshop.ae/");
	
			driver.manage().window().maximize();
	
			Thread.sleep(3000);
		}
	
		@Test(priority = 1, enabled = true)
		private void login() throws InterruptedException {
	
			WebElement accBtn = driver.findElement(By.xpath("//a[@id='account-btn']"));
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("arguments[0].click();", accBtn);
			
			Thread.sleep(5000);
	
		}
	
		@Test(priority = 2, enabled = false)
		private void credentials() throws InterruptedException {
	
			// enter the user name
			WebElement userName = driver.findElement(By.xpath("//input[@name='login[username]']"));
			userName.sendKeys("balaji.p@sysfore.com");
	
			// enter the password
			WebElement password = driver.findElement(By.xpath("//input[@name='login[password]']"));
			password.sendKeys("Balaji@123");
	
			// click the submit
			WebElement submit = driver.findElement(By.xpath("(//span[text()='Login'])[2]"));
			submit.click();
	
			Thread.sleep(4000);
	
			try {
	
				WebElement errMsg = driver.findElement(By.xpath("//div[contains(text(),'The account sign-in was incorrect or your account is disabled temporarily.')]"));
	
				// error msg is showing the if block will execute, otherwise errmsg is not
				// showing else block is will execute
	
				if (errMsg.isDisplayed()) {
	
					// if error msg is showing print the error msg
					System.out.println(errMsg.getText());
	
				}
	
			} catch (NoSuchElementException e) {
	
				// error msg is not showing this statement will execute
				System.out.println("user entered the proper username and password");
	
			}
	
		}
	
		@Test(priority = 3, enabled = false)
		private void withoutRule() throws InterruptedException {
	
			// enter the user name
			WebElement userName = driver.findElement(By.xpath("//input[@name='login[username]']"));
			userName.sendKeys("balaji.p@sysfore.com");
	
			Thread.sleep(3000);
	
			// enter the password
			WebElement password = driver.findElement(By.xpath("//input[@name='login[password]']"));
			password.sendKeys("Balaji@123");
	
			// can get the username field value
			String emailId = userName.getAttribute("value");
	
			// click the submit button
			WebElement submit = driver.findElement(By.xpath("(//span[text()='Login'])[2]"));
			submit.click();
	
			Thread.sleep(3000);
			
			// user name is without @ and .com if block will execute
			if (!emailId.contains("@") | !emailId.contains(".com")) {
	
				WebElement errmsg = driver.findElement(By.xpath("//div[@id='email-error']"));
	
				// error msg is showing if block will execute,
				if (errmsg.isDisplayed()) {
	
					System.out.println("error msg showing properly");
					System.out.println(errmsg.getText());
	
					// error msg is not showing else block will execute.
				} else {
	
					System.out.println("error msg is not showing");
				}
	
				// username with @ and .com else block will execute
			} else {
	
				System.out.println("user entered the emaild is with @ and .com");
	
			}
	
			Thread.sleep(3000);
	
		}
	
		@Test(priority = 4, enabled = true)
		private void withoutEmpty() {
	
			// enter the user name
			WebElement userName = driver.findElement(By.xpath("//input[@name='login[username]']"));
			userName.sendKeys("balaji.p@sysfore.com");
	
			// enter the password
			WebElement password = driver.findElement(By.xpath("//input[@name='login[password]']"));
			password.sendKeys("Balaji");
	
			// can get the username field value
			String emailId = userName.getAttribute("value");
	
			String keyword = password.getAttribute("value");
	
			// click the submit button
			WebElement submit = driver.findElement(By.xpath("(//span[text()='Login'])[2]"));
			submit.click();
	
			// email field is empty if block will execute,email not empty else block will
			// execute
			if (emailId.isEmpty()) {
	
				System.out.println("username  field is empty");
	
				WebElement emailErrMsg = driver.findElement(By.xpath("//div[@id='email-error']"));
	
				if (emailErrMsg.isDisplayed()) {
	
					System.out.println("please enter your user name");
					System.out.println(emailErrMsg.getText());
				}
			} else {
	
				System.out.println("username  field is not empty");
			}
	
			// password field is empty if block will execute,password not empty else block
			// will execute
			if (keyword.isEmpty()) {
	
				System.out.println("password field is empty");
				WebElement passErrMsg = driver.findElement(By.xpath("//div[@id='pass-error']"));
	
				if (passErrMsg.isDisplayed()) {
	
					System.out.println("please enter your password");
					System.out.println(passErrMsg.getText());
				}
			} else {
	
				System.out.println("password field is not empty");
			}
	
		}
	
		@AfterClass
		private void end() {
	
			driver.quit();
	
		}
	
		
}

