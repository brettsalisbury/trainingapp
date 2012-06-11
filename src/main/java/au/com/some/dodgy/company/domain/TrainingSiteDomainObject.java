package au.com.some.dodgy.company.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TrainingSiteDomainObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8018580498328285334L;
	private List<User> users;

	public TrainingSiteDomainObject()
	{
		this.users = new ArrayList<User>();
	}
	
	public Collection<User> getUsers() {
		return this.users;
	}

	public void addUser(User user) {
		this.users.add(user);
	}
	
	

}
