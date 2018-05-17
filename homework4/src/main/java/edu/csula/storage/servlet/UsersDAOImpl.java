package edu.csula.storage.servlet;

import java.util.Collection;
import java.util.ArrayList;
import java.util.Optional;
import java.util.List;
import java.util.Iterator;

import javax.servlet.http.HttpSession;

import edu.csula.models.User;
import edu.csula.storage.UsersDAO;

/**
 * To abstract the storage access from the business layer using HttpSession
 */
public class UsersDAOImpl implements UsersDAO {
	private final HttpSession context;
	protected static final String CONTEXT_NAME = "users";

	public UsersDAOImpl(HttpSession context) {
		this.context = context;
	}

	@Override
	public boolean authenticate(String username, String password) {
		// TODO: check if username/password combination is valid and store the
		//       username/password into the session
		Collection<User> users = new ArrayList<User>();
		users.add(new User(0,"admin","cs3220password"));
		if(users == null){
			return false;
		}else{
			for(User user : users){
				if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
					this.context.setAttribute(CONTEXT_NAME, user);
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public Optional<User> getAuthenticatedUser() {
		// TODO: return the authenticated user if there is any

		// Collection<User> users = (ArrayList<User>) context.getAttribute(CONTEXT_NAME);
		// if(users == null){
		// 	return Optional.empty();
		// }
		// for(User user : users){
		// 	if(authenticate(user.getUsername(),user.getPassword())){
		// 		return Optional.of(user);
		// 	}
		// }
		// return Optional.empty();

		User user = (User) context.getAttribute(CONTEXT_NAME);
		if(user == null){
			return Optional.empty();
		}
		if(authenticate(user.getUsername(),user.getPassword())){
				return Optional.of(user);
			}
		return Optional.empty();
	}

	@Override
	public void logout() {
		// TOOD: log user out using `invalidate`
		// User user = (User) context.getAttribute(CONTEXT_NAME);
		// if(authenticate(user.getUsername(),user.getPassword())){
		// 	this.context.invalidate();
		// 	// this.context.setAttribute(CONTEXT_NAME, null);
		// }
		if(getAuthenticatedUser().isPresent()){
			this.context.invalidate();
		}
	}
}
