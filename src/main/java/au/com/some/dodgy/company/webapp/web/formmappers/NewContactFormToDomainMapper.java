package au.com.some.dodgy.company.webapp.web.formmappers;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import au.com.some.dodgy.company.domain.User;
import au.com.some.dodgy.company.webapp.web.formobjects.NewContactFormModel;

@Component
@Scope("singleton")
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
