package au.com.some.dodgy.company.webapp.web.controllers;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class NewContactControllerTest {
	
	private NewContactController newContactController;

	@Before
	public void setUp()
	{
		this.newContactController = new NewContactController();
	}
	
	@Test
	public void shouldReturnNewContactViewOnPageLoad()
	{
		// Given
		String expectedViewName = "newContact";
		
		// When
		String actualViewName = this.newContactController.onPageLoad();
		
		// Then
		assertThat(actualViewName, is(equalTo(expectedViewName)));
	}
}
