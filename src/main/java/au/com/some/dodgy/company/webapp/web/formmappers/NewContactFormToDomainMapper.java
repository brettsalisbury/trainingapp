package au.com.some.dodgy.company.webapp.web.formmappers;

import au.com.some.dodgy.company.domain.User;
import au.com.some.dodgy.company.webapp.web.formobjects.NewContactFormModel;

public class NewContactFormToDomainMapper {

	public NewContactFormToDomainMapper()
	{
		
	}
	
	public User mapFormToDomain(NewContactFormModel newContactFormModel) {
		User user = new User();
		user.setAge(newContactFormModel.getAge());
		user.setName(newContactFormModel.getName());
		return user;
	}
	
	

}
