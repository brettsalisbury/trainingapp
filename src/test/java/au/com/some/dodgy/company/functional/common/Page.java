package au.com.some.dodgy.company.functional.common;

import org.openqa.selenium.WebDriver;

public abstract class Page {

	protected WebDriver driver;

	public Page(WebDriver driver) {
		this.driver = driver;
	}

	public String getPageTitle() {
		return this.driver.getTitle();
	}
}