package au.com.some.dodgy.company.functional.common;

import java.io.File;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

@RunWith(BlockJUnit4ClassRunner.class)
public class ChromeWebDriverTestBase {

	private WebDriver driver;
	private static ChromeDriverService service;

	@BeforeClass
	public static void setUpChromeService() throws Exception {
		service = new ChromeDriverService.Builder()
				.usingAnyFreePort()
				.usingChromeDriverExecutable(
						new File(
								"C:\\Development\\chromedriver\\chromedriver.exe"))
				.build();
		service.start();
	}

	@AfterClass
	public static void tearDownChromeService() {
		service.stop();
	}

	@Before
	public void createDriver() {
		driver = new RemoteWebDriver(service.getUrl(),
				DesiredCapabilities.chrome());
	}

	@After
	public void quitDriver() {
		driver.quit();
	}
	
	protected WebDriver getDriver()
	{
		return this.driver;
	}
}