package au.com.some.dodgy.company.functional.tests;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;

import static org.hamcrest.core.Is.is;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import au.com.some.dodgy.company.functional.common.ChromeWebDriverTestBase;
import au.com.some.dodgy.company.functional.common.Page;
import au.com.some.dodgy.company.functional.pages.NewContactPage;
import au.com.some.dodgy.company.functional.pages.WelcomePage;

public class NavigationIntegrationTest extends ChromeWebDriverTestBase {

	private Page currentPage = null;
	
	@Before
	public void setUp()
	{
		this.getDriver().get("http://localhost:8080/dodgyapp/");
	}
	
	@Test
	public void shouldNavigateThroughPagesCorrectly()
	{
		navigateToWelcomePage(); 
		navigateFromWelcomPageToNewContactPage();
		addValidNewContactAndAssertOnWelcomePage();
	}

	private void navigateToWelcomePage() {
		// given
		String expectedWelcomePageTitle = "Training app | Welcome Page";
		
		// when
		this.currentPage = new WelcomePage(this.getDriver());
		
		// then
		assertThat(this.currentPage.getPageTitle(), is(equalTo(expectedWelcomePageTitle)));
	}
	
	private void navigateFromWelcomPageToNewContactPage() {
		// given
		String expectedNewContactPageTitle = "Training app | New Contact";
		assertTrue(this.currentPage instanceof WelcomePage);
		
		// when
		this.currentPage = ((WelcomePage)currentPage).navigateToAddNewContact();
		
		// then
		assertTrue(this.currentPage instanceof NewContactPage);
		assertThat(this.currentPage.getPageTitle(), is(equalTo(expectedNewContactPageTitle)));
	}
	
	private void addValidNewContactAndAssertOnWelcomePage() {
		// given
		String expectedUserName = "Lex Luther";
		String expectedAge = "84";
		assertTrue(this.currentPage instanceof NewContactPage);
		
		// when
		((NewContactPage)currentPage).addUserDetails(expectedUserName, expectedAge);
		this.currentPage = ((NewContactPage)currentPage).addUser();
		
		// then
		assertTrue(this.currentPage instanceof WelcomePage);
		assertThat(((WelcomePage)currentPage).getDrivers().size(), is(equalTo(1)));
		assertThat(((WelcomePage)currentPage).getDrivers().get(0), is(equalTo(expectedUserName)));
	}
	
}
