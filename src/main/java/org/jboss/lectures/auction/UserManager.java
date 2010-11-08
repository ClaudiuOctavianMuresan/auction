package org.jboss.lectures.auction;

import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.jboss.lectures.auction.entity.User;

@ViewScoped
@Named
public class UserManager implements Serializable {

	private static final long serialVersionUID = 1L;

	// @Inject private DatabaseStub database;

	// @PersistenceContext
	public EntityManager em;

	public void addUser(User user) {
		// database.addUser(user);

	}

	public User getUserByEmail(String email) {
		// return database.findUserByEmail(email);
		String jql = "SELECT u FROM User u WHERE u.email = :email) ";
		TypedQuery<User> query = em.createQuery(jql, User.class);
		query.setParameter("email", email);
		return query.getSingleResult();
	}
}
