package au.com.some.dodgy.company.webapp.web.controllers;

import au.com.some.dodgy.company.domain.TrainingSiteDomainObject;
import au.com.some.dodgy.company.domain.User;
import au.com.some.dodgy.company.webapp.web.formmappers.NewContactFormToDomainMapper;
import au.com.some.dodgy.company.webapp.web.formobjects.NewContactFormModel;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.ModelAndViewAssert.assertModelAttributeValue;
import static org.springframework.test.web.ModelAndViewAssert.assertViewName;

public class NewContactControllerTest {

    private NewContactController newContactController;
    private TrainingSiteDomainObject trainingSiteDomainObject;
    private NewContactFormToDomainMapper newContactFormToDomainMapper;

    @Before
    public void setUp() {
        this.trainingSiteDomainObject = new TrainingSiteDomainObject();
        this.newContactFormToDomainMapper = mock(NewContactFormToDomainMapper.class);
        this.newContactController = new NewContactController(this.trainingSiteDomainObject, this.newContactFormToDomainMapper);
        this.newContactController.setFormView("newContact");
        this.newContactController.setSuccessView("redirect:welcomePage");
    }

    @Test
    public void shouldReturnNewContactViewOnPageLoad() throws Exception {
        // Given
        String expectedViewName = "newContact";

        // When
        ModelAndView actualModelAndView = callControllerWithMethod("GET");

        // Then
        assertViewName(actualModelAndView, expectedViewName);
    }

    @Test
    public void shouldReturnEmptyNewContractFormModelOnPageLoad() throws Exception {
        // Given
        String expectedModelAttributeName = "newContactFormModel";

        NewContactFormModel expectedModelObject = new NewContactFormModel();

        // When
        ModelAndView newContactModelAndView = callControllerWithMethod("GET");

        // Then
        assertModelAttributeValue(newContactModelAndView,
                expectedModelAttributeName, expectedModelObject);
    }

    @Test
    @Ignore
    public void shouldRedirectToWelcomePageOnValidAddContactRequest() throws Exception {
        validateViewNameGivenModelValidity("redirect:welcomePage", true);
    }

    @Test
    public void shouldStayInNewContactPageOnInvalidAddContactRequest() throws Exception {
        validateViewNameGivenModelValidity("newContact", false);
    }

    private void validateViewNameGivenModelValidity(String expectedViewName, boolean modelValidity) throws Exception {
        // Given
        given(this.newContactFormToDomainMapper.mapFormToDomain(Mockito.any(NewContactFormModel.class))).willReturn(new User());

        // When
        ModelAndView actualModelAndView = callControllerWithMethod("POST");

        // Then
        assertViewName(actualModelAndView, expectedViewName);
    }

    @Test
    @Ignore
    public void shouldAddNewUserToDomainModelOnValidSubmission() throws Exception {
        // Given
        BindingResult mockBindingResult = createMockBindingResultWithHasErrorsValueOf(false);
        NewContactFormModel newContactFormModel = createValidNewContactFormModel();
        assertThat(this.trainingSiteDomainObject.getUsers().size(), is(equalTo(0)));

        // When
        callControllerWithMethod("POST");
//        this.newContactController.onNewContact(newContactFormModel, mockBindingResult);

        // Then
        assertThat(this.trainingSiteDomainObject.getUsers().size(), is(equalTo(1)));
    }

    private ModelAndView callControllerWithMethod(String method) throws Exception {
        MockHttpServletRequest mockRequestServletRequest = new MockHttpServletRequest();
        mockRequestServletRequest.setMethod(method);
        MockHttpServletResponse mockHttpServletResponse = new MockHttpServletResponse();

        // When
        return this.newContactController
                .handleRequest(mockRequestServletRequest, mockHttpServletResponse);
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
