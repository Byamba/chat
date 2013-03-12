package com.seventysevenagency.chat.dao.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.seventysevenagency.chat.dao.DAOException;
import com.seventysevenagency.chat.dao.MessageDAO;
import com.seventysevenagency.chat.domain.Conversation;
import com.seventysevenagency.chat.domain.Message;
import com.seventysevenagency.chat.domain.User;

public class MessageHibernateDAOImpl implements MessageDAO {

	public int create(Message message) throws DAOException {
		int id;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			id = (Integer) session.save(message);
		} catch (Exception e) {
			throw new DAOException(e);
		}
		return id;
	}

	public void update(Message message) throws DAOException {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.update(message);
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	public Message find(int id) throws DAOException {
		Message result = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		result = (Message) session.createQuery("FROM Message WHERE id = :id")
				.setLong("id", id).uniqueResult();
		return result;
	}

	public void deleteById(int id) throws DAOException {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.delete(find(id));
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<Message> findByUser(User user) throws DAOException {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		List<Message> list = null;
		try {
			String hql = "FROM Message WHERE user_id = :user_id";
			Query query = session.createQuery(hql);
			query.setLong("user_id", user.getId());
			list = query.list();
		} catch (Exception e) {
			throw new DAOException(e);
		} finally {
			session.close();
		}
		return list;
	}

	public List<Message> findByConversation(Conversation conversation)
			throws DAOException {
		return findByConversationId(conversation.getId());
	}

	@SuppressWarnings("unchecked")
	public List<Message> findByConversationId(int conversationId)
			throws DAOException {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		List<Message> result = null;
		Query query = session
				.createQuery("FROM Message WHERE conversation_id = :id");
		query.setParameter("id", conversationId);
		try {
			result = query.list();
		} catch (Exception e) {
			throw new DAOException(e);
		}
		return result;
	}
}
