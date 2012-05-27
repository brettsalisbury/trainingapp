package au.com.some.dodgy.company.webapp.web.formmappers;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ch.lambdaj.Lambda;

import au.com.some.dodgy.company.domain.TrainingSiteDomainObject;
import au.com.some.dodgy.company.webapp.web.formobjects.WelcomePageModel;

@Component
@Scope("singleton")
public class WelcomePageModelToDomainMapper {

	public WelcomePageModel mapDomainToForm(
			TrainingSiteDomainObject trainingSiteDomainObject) {
		
		WelcomePageModel welcomePageModel = new WelcomePageModel();
		List<String> userNames = Lambda.extractProperty(trainingSiteDomainObject.getUsers(), "name");
		welcomePageModel.setUserNames(userNames);
		return welcomePageModel;
	}

}
