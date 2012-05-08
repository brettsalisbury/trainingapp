package au.com.some.dodgy.company.webapp.web;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

import org.junit.Test;

public class WelcomePageControllerTest extends WelcomePageController {

	@Test
	public void testShouldReturnHelloWorldViewOnPageLoad() {
		// given
		String expectedViewName = "helloWorld";
		
		// when
		String actualViewName = this.onInitialPageLoad();
		
		// then
		assertThat(expectedViewName, is(equalTo(actualViewName)));
	}

}
