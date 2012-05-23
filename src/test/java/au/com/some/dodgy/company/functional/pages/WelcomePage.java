package au.com.some.dodgy.company.functional.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import au.com.some.dodgy.company.functional.common.Page;
import ch.lambdaj.Lambda;

public class WelcomePage extends Page {
	
	public WelcomePage(WebDriver driver)
	{
		super(driver);
	}
	
	public List<String> getDrivers()
	{
		List<WebElement> users = this.driver.findElements(By.id("userName"));
		return Lambda.extractProperty(users, "text");
	}
	
	public NewContactPage navigateToAddNewContact()
	{
		this.driver.findElement(By.id("newContactNav")).click();
		return new NewContactPage(this.driver);
	}

}
