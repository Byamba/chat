package com.seventysevenagency.chat.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BaseHibernateDAO {
	@Autowired
	protected SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
	   this.sessionFactory = sessionFactory;
	}
	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	 
}
