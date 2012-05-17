package au.com.some.dodgy.company.webapp.web.formmappers;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import au.com.some.dodgy.company.domain.User;
import au.com.some.dodgy.company.webapp.web.formobjects.NewContactFormModel;


public class NewContactFormToDomainMapperTest {

	private NewContactFormToDomainMapper newContactFormToDomainMapper;

	@Before
	public void setUp()
	{
		this.newContactFormToDomainMapper = new NewContactFormToDomainMapper();
	}
	
	@Test
	public void shouldMapAgeFieldFromFormObjectToDomainObject()
	{
		// given
		NewContactFormModel newContactFormModel = new NewContactFormModel();
		newContactFormModel.setAge(20);
		
		// when
		User user = this.newContactFormToDomainMapper.mapFormToDomain(newContactFormModel);
		
		// then
		assertThat(user.getAge(), is(equalTo(newContactFormModel.getAge())));
	}
	
	@Test
	public void shouldMapNameFieldFromFormObjectToDomainObject()
	{
		// given
		NewContactFormModel newContactFormModel = new NewContactFormModel();
		newContactFormModel.setName("Test name");
		
		// when
		User user = this.newContactFormToDomainMapper.mapFormToDomain(newContactFormModel);
		
		// then
		assertThat(user.getName(), is(equalTo(newContactFormModel.getName())));
	}
}
