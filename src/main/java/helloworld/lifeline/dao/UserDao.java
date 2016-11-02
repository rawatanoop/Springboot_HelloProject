package helloworld.lifeline.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import helloworld.lifeline.entity.User;

@Repository
public class UserDao implements IUserDao<User> {

	@Autowired
	private SessionFactory _sessionFactory;

	private Session getSession() {
		return _sessionFactory.getCurrentSession();
	}

	@Override
	@Transactional
	public User save(User volunteer) {

		Serializable userID = getSession().save(volunteer);
		User user = getSession().load(User.class, userID);
		if (user.getId() == userID)
			return user;
		return null;
	}


	@Override
	@Transactional
	public User getById(Integer userID) {
		User user = getSession().load(User.class, userID);
		if (user == null)
			return null;
		if (user.getId() == userID)
			return user;
		return null;
	}


	@Override
	@Transactional
	public User getByEmail(String email) {
		@SuppressWarnings("rawtypes")
		List list = getSession().getNamedQuery("User.findByEmail").setString("email", email).list();
		if (list == null || list.isEmpty())
			return null;

		return (User) list.get(0);
	}

	@Override
	public void delete(User entity) {
		
	}

	@Override
	public List<User> getAll() {
		return null;
	}

	@Override
	public void update(User entity) {
		
	}

}
