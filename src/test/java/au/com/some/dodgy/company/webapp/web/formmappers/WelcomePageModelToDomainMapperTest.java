package au.com.some.dodgy.company.webapp.web.formmappers;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import java.util.Iterator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import au.com.some.dodgy.company.domain.TrainingSiteDomainObject;
import au.com.some.dodgy.company.domain.User;
import au.com.some.dodgy.company.webapp.web.formobjects.WelcomePageModel;



public class WelcomePageModelToDomainMapperTest {
	
	private WelcomePageModelToDomainMapper welcomePageMapperToDomainMapperTest;
	
	@Before
	public void setUp()
	{
		this.welcomePageMapperToDomainMapperTest = new WelcomePageModelToDomainMapper();
	}
	
	@Test
	public void shouldMapNameOfUserFromDomainObjectToModelObject()
	{
		// given
		TrainingSiteDomainObject trainingSiteDomainObject = new TrainingSiteDomainObject();
		User user1 = new User();
		user1.setName("Test 1");
		trainingSiteDomainObject.addUser(user1);
		
		// when
		WelcomePageModel welcomePageModel = this.welcomePageMapperToDomainMapperTest.mapDomainToForm(trainingSiteDomainObject);
		
		// then
		Iterator<String> names = welcomePageModel.getUserNames().iterator();
		assertThat(user1.getName(), is(equalTo(names.next().toString())));	
	}
	
	@Test
	public void shouldMapAllUsersFromDomainToModelObject()
	{
		// given
		TrainingSiteDomainObject trainingSiteDomainObject = new TrainingSiteDomainObject();
		User user1 = new User();
		user1.setName("Test 1");
		User user2 = new User();
		user2.setName("Test 2");
		trainingSiteDomainObject.addUser(user1);
		trainingSiteDomainObject.addUser(user2);
		
		// when
		WelcomePageModel welcomePageModel = this.welcomePageMapperToDomainMapperTest.mapDomainToForm(trainingSiteDomainObject);
		
		// then
		assertThat(welcomePageModel.getUserNames().size(), is(equalTo(2)));
	}
}