package au.com.some.dodgy.company.webapp.web.formobjects;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import au.com.some.dodgy.company.domain.User;

public class WelcomePageModel {

	private List<String> userNames;
	
	public WelcomePageModel()
	{
		this.userNames = new ArrayList<String>();
	}
	public Collection<String> getUserNames() {
		return this.userNames;
	}

}
