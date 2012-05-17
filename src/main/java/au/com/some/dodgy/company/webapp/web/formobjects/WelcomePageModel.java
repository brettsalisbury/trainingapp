package au.com.some.dodgy.company.webapp.web.formobjects;

import java.util.Collection;

public class WelcomePageModel {

	private Collection<String> userNames;
	
	public WelcomePageModel()
	{
		this.userNames = null;
	}
	
	public Collection<String> getUserNames() {
		return this.userNames;
	}
	
	public void setUserNames(Collection<String> extractString) {
		this.userNames = extractString;
	}

}
