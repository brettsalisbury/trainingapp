package au.com.some.dodgy.company.webapp.web.formmappers;

import au.com.some.dodgy.company.domain.TrainingSiteDomainObject;
import au.com.some.dodgy.company.webapp.web.formobjects.WelcomePageModel;
import ch.lambdaj.Lambda;

import java.util.List;

public class WelcomePageModelToDomainMapper {

	public WelcomePageModel mapDomainToForm(
			TrainingSiteDomainObject trainingSiteDomainObject) {
		
		WelcomePageModel welcomePageModel = new WelcomePageModel();
		List<String> userNames = Lambda.extractProperty(trainingSiteDomainObject.getUsers(), "name");
		welcomePageModel.setUserNames(userNames);
		return welcomePageModel;
	}

}
