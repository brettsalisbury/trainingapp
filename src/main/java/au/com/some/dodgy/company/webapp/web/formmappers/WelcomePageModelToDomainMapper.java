package au.com.some.dodgy.company.webapp.web.formmappers;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import au.com.some.dodgy.company.domain.TrainingSiteDomainObject;
import au.com.some.dodgy.company.webapp.web.formobjects.WelcomePageModel;

@Component
@Scope("singleton")
public class WelcomePageModelToDomainMapper {

	public WelcomePageModel mapDomainToForm(
			TrainingSiteDomainObject trainingSiteDomainObject) {
		
		WelcomePageModel welcomePageModel = new WelcomePageModel();
		welcomePageModel.getUserNames().add("LOLCatzs");
		return welcomePageModel;
	}

}
