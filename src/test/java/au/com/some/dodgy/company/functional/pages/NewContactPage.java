package au.com.some.dodgy.company.functional.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import au.com.some.dodgy.company.functional.common.Page;

public class NewContactPage extends Page{

	public NewContactPage(WebDriver driver)
	{
		super(driver);
	}
	
	public void addUserDetails(String name, String age)
	{
		this.driver.findElement(By.id("name")).sendKeys(name);
		this.driver.findElement(By.id("age")).sendKeys(age);
	}
	
	public Page addUser()
	{
		String newContactPageTitle = this.getPageTitle();
		this.driver.findElement(By.id("age")).submit();
		if (this.driver.getTitle().equals(newContactPageTitle)) {
			return this;
		}
		else {
			return new WelcomePage(this.driver);
		}
	}
	
	public boolean isValidationError() 
	{
		return this.driver.findElement(By.id("nameError")).isDisplayed() || this.driver.findElement(By.id("ageError")).isDisplayed();
	}
}
