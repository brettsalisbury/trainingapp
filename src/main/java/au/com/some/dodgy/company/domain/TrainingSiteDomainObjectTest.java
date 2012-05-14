package au.com.some.dodgy.company.domain;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class TrainingSiteDomainObjectTest {
	
	private TrainingSiteDomainObject trainingSiteDomainObject;

	@Before
	public void setUp()
	{
		this.trainingSiteDomainObject = new TrainingSiteDomainObject();
	}
	
	@Test
	public void shouldAddNewUserToListOfUsers()
	{
		// given
		String user = new String();
		assertThat(this.trainingSiteDomainObject.getUsers().size(), is(equalTo(0)));
		
		// when
		this.trainingSiteDomainObject.addUser(user);
		
		// then
		assertThat(this.trainingSiteDomainObject.getUsers().size(), is(equalTo(1)));
	}
}
