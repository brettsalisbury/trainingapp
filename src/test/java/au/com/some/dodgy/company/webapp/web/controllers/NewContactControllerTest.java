package au.com.some.dodgy.company.webapp.web.controllers;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.ModelAndViewAssert.assertModelAttributeAvailable;
import static org.springframework.test.web.ModelAndViewAssert.assertModelAttributeValue;
import static org.springframework.test.web.ModelAndViewAssert.assertViewName;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.web.servlet.ModelAndView;

import au.com.some.dodgy.company.webapp.web.formobjects.NewContactFormModel;


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
		ModelAndView actualModelAndView = this.newContactController.onPageLoad();
		
		// Then
		assertViewName(actualModelAndView, expectedViewName);
	}
	
	@Test
	public void shouldReturnEmptyNewContractFormModelOnPageLoad()
	{
		// Given
		String expectedModelAttributeName = "newContactFormModel";
		NewContactFormModel expectedModelObject = new NewContactFormModel();
		
		// When
		ModelAndView newContactModelAndView = this.newContactController.onPageLoad();
		
		// Then
		assertModelAttributeValue(newContactModelAndView, expectedModelAttributeName, expectedModelObject);
	}
}
