package au.com.some.dodgy.company.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class TrainingSiteDomainObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8018580498328285334L;
	private List<String> users;

	public TrainingSiteDomainObject()
	{
		this.users = new ArrayList<String>();
	}
	
	public Collection<String> getUsers() {
		return this.users;
	}

	public void addUser(String string) {
		this.users.add(string);
	}

}
