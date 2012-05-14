package au.com.some.dodgy.company.webapp.web.controllers;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.ModelAndViewAssert.assertModelAttributeAvailable;
import static org.springframework.test.web.ModelAndViewAssert.assertModelAttributeValue;
import static org.springframework.test.web.ModelAndViewAssert.assertViewName;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.mock.web.portlet.MockActionRequest;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import au.com.some.dodgy.company.domain.TrainingSiteDomainObject;
import au.com.some.dodgy.company.webapp.web.formobjects.NewContactFormModel;

public class NewContactControllerTest {

	private NewContactController newContactController;
	private TrainingSiteDomainObject trainingSiteDomainObject;

	@Before
	public void setUp() {
		this.trainingSiteDomainObject = new TrainingSiteDomainObject();
		this.newContactController = new NewContactController(this.trainingSiteDomainObject);
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
		BindingResult mockBindingResult = createMockBindingResultWithHasErrorsValueOf(!modelValidity);

		// When
		String actualViewName = this.newContactController.onNewContact(null,
				mockBindingResult);

		// Then
		assertThat(actualViewName, is(equalTo(expectedViewName)));
	}
	
	@Test
	public void shouldAddNewUserToDomainModelOnValidSubmission(){
		// Given
		BindingResult mockBindingResult = createMockBindingResultWithHasErrorsValueOf(false);
		NewContactFormModel newContactFormModel = createValidNewContactFormModel();
		assertThat(this.trainingSiteDomainObject.getUsers().size(), is(equalTo(0)));
		
		// When
		this.newContactController.onNewContact(newContactFormModel, mockBindingResult);
		
		// Then
		assertThat(this.trainingSiteDomainObject.getUsers().size(), is(equalTo(1)));
	}

	private BindingResult createMockBindingResultWithHasErrorsValueOf(boolean hasErrorsValue) {
		BindingResult mockBindingResult = Mockito.mock(BindingResult.class);
		BDDMockito.given(mockBindingResult.hasErrors()).willReturn(hasErrorsValue);
		return mockBindingResult;
	}

	private NewContactFormModel createValidNewContactFormModel() {
		NewContactFormModel newContactFormModel = new NewContactFormModel();
		newContactFormModel.setAge(20);
		newContactFormModel.setName("Brett Salisbury");
		return newContactFormModel;
	}
}
