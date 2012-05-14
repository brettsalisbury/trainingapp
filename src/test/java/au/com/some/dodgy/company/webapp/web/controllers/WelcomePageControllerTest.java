package au.com.some.dodgy.company.webapp.web.controllers;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import au.com.some.dodgy.company.webapp.web.controllers.WelcomePageController;

public class WelcomePageControllerTest {

	private WelcomePageController welcomePageController;

	@Before
	public void setUp()
	{
		this.welcomePageController = new WelcomePageController();
	}
	
	@Test
	public void testShouldReturnHelloWorldViewOnPageLoad() {
		// given
		String expectedViewName = "welcomePage";
		
		// when
		String actualViewName = this.welcomePageController.onInitialPageLoad();
		
		// then
		assertThat(expectedViewName, is(equalTo(actualViewName)));
	}

}
