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
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.mock.web.portlet.MockActionRequest;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import au.com.some.dodgy.company.webapp.web.formobjects.NewContactFormModel;

public class NewContactControllerTest {

	private NewContactController newContactController;

	@Before
	public void setUp() {
		this.newContactController = new NewContactController();
	}

	@Test
	public void shouldReturnNewContactViewOnPageLoad() {
		// Given
		String expectedViewName = "newContact";

		// When
		ModelAndView actualModelAndView = this.newContactController
				.onPageLoad();

		// Then
		assertViewName(actualModelAndView, expectedViewName);
	}

	@Test
	public void shouldReturnEmptyNewContractFormModelOnPageLoad() {
		// Given
		String expectedModelAttributeName = "newContactFormModel";
		NewContactFormModel expectedModelObject = new NewContactFormModel();

		// When
		ModelAndView newContactModelAndView = this.newContactController
				.onPageLoad();

		// Then
		assertModelAttributeValue(newContactModelAndView,
				expectedModelAttributeName, expectedModelObject);
	}

	@Test
	public void shouldRedirectToWelcomePageOnValidAddContactRequest() {
		validateViewNameGivenModelValidity("redirect:welcomePage", true);
	}
	
	@Test
	public void shouldStayInNewContactPageOnInvalidAddContactRequest() {
		validateViewNameGivenModelValidity("newContact", false);
	}

	private void validateViewNameGivenModelValidity(String expectedViewName, boolean modelValidity) {
		// Given
		BindingResult mockBindingResult = Mockito.mock(BindingResult.class);
		BDDMockito.given(mockBindingResult.hasErrors()).willReturn(!modelValidity);

		// When
		String actualViewName = this.newContactController.onNewContact(null,
				mockBindingResult);

		// Then
		assertThat(actualViewName, is(equalTo(expectedViewName)));
	}
}
