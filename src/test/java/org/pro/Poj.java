package org.pro;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Poj {

	WebDriver driver;
	
	public WebDriver getDriver() {
		return driver;
	}


	public List<WebElement> getSubmit() {
		return submit;
	}


	public Poj(WebDriver driver) {
		
       this.driver = driver;
		
		PageFactory.initElements(driver, this);
		
	}
	
	
	@FindBy(xpath="//a[@class='product-item-link']")
	List<WebElement> submit;

	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}


	public WebElement get(int k) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
}
