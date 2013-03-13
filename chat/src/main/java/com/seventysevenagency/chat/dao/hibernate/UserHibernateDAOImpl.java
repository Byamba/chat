package com.seventysevenagency.chat.dao.hibernate;

import org.hibernate.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.seventysevenagency.chat.dao.UserDAO;
import com.seventysevenagency.chat.domain.User;
import com.seventysevenagency.chat.util.EncryptionHelper;
@Component
@Transactional
public class UserHibernateDAOImpl extends BaseHibernateDAO implements UserDAO {

	public int create(User user) {
		int id;

		user.setPassword(EncryptionHelper.md5(user.getPassword()));
		id = (Integer) getSession().save(user);

		return id;
	}

	public void update(User user) {
		getSession().update(user);
	}

	public User findById(int id) {
		User user = (User) getSession().createQuery("FROM User WHERE id = :id")
				.setParameter("id", id).uniqueResult();

		return user;
	}

	public int deleteById(int id) {

		Query query = getSession().createQuery("DELETE User WHERE id = :id");
		query.setParameter("id", id);
		int result = query.executeUpdate();

		return result;
	}

	public User findByUsername(String username) {
		User user = (User) getSession()
				.createQuery("FROM User WHERE username = :username")
				.setParameter("username", username).uniqueResult();

		return user;
	}
	public User authorize(String username, String password) {

		Query query = getSession()
				.createQuery("FROM User WHERE username = :username AND password = :password");
		query.setParameter("username", username);
		query.setParameter("password", EncryptionHelper.md5(password));

		User user = (User) query.uniqueResult();

		return user;
	}
}
