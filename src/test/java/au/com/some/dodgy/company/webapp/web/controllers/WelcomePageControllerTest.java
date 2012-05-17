package au.com.some.dodgy.company.webapp.web.controllers;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.ModelAndViewAssert.assertModelAttributeAvailable;
import static org.springframework.test.web.ModelAndViewAssert.assertViewName;

import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.web.servlet.ModelAndView;

import au.com.some.dodgy.company.domain.TrainingSiteDomainObject;
import au.com.some.dodgy.company.domain.User;
import au.com.some.dodgy.company.webapp.web.controllers.WelcomePageController;
import au.com.some.dodgy.company.webapp.web.formmappers.WelcomePageModelToDomainMapper;
import au.com.some.dodgy.company.webapp.web.formobjects.WelcomePageModel;

public class WelcomePageControllerTest {

	private WelcomePageController welcomePageController;
	private TrainingSiteDomainObject trainingSiteDomainObject;
	private WelcomePageModelToDomainMapper welcomePageModelToDomainMapper;

	@Before
	public void setUp()
	{
		this.trainingSiteDomainObject = new TrainingSiteDomainObject();
		this.welcomePageModelToDomainMapper = mock(WelcomePageModelToDomainMapper.class);
		this.welcomePageController = new WelcomePageController(this.trainingSiteDomainObject, this.welcomePageModelToDomainMapper);
	}
	
	@Test
	public void testShouldReturnHelloWorldViewOnPageLoad() {
		// given
		String expectedViewName = "welcomePage";
		
		// when
		ModelAndView actualModelAndView = this.welcomePageController.onPageLoad();
		
		// then
		assertViewName(actualModelAndView, expectedViewName);
	}
	
	@Test
	public void shouldReturnAllActiveUsersInModelOnPageLoad() {
		// given
		String expectedModelAttributeName = "welcomePageModel";
		
		// when
		ModelAndView actualModelAndView = this.welcomePageController.onPageLoad();
		
		// then
		assertModelAttributeAvailable(actualModelAndView, expectedModelAttributeName);	
	}
	
	@Test
	public void shouldMapFromDomainToFormObjectOnPageLoad()
	{
		// given
		String expectedModelAttributeName = "welcomePageModel";
		WelcomePageModel expectedWelcomePageModel = new WelcomePageModel();
		given(this.welcomePageModelToDomainMapper.mapDomainToForm(this.trainingSiteDomainObject)).willReturn(expectedWelcomePageModel);
		
		// when
		ModelAndView actualModelAndView = this.welcomePageController.onPageLoad();
		
		// then
		WelcomePageModel actualModel = (WelcomePageModel)actualModelAndView.getModel().get(expectedModelAttributeName);
		assertThat(actualModel, is(equalTo(expectedWelcomePageModel)));
	}

}
