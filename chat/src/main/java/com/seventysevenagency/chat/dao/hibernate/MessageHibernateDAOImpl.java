package com.seventysevenagency.chat.dao.hibernate;

import java.util.List;

import org.hibernate.Query;

import com.seventysevenagency.chat.dao.DAOException;
import com.seventysevenagency.chat.dao.MessageDAO;
import com.seventysevenagency.chat.domain.Conversation;
import com.seventysevenagency.chat.domain.Message;
import com.seventysevenagency.chat.domain.User;

public class MessageHibernateDAOImpl extends BaseHibernateDAO implements MessageDAO {

	public int create(Message message) throws DAOException {
		int id;
		id = (Integer) getSession().save(message);

		return id;
	}

	public void update(Message message) throws DAOException {
		getSession().update(message);

	}

	public Message find(int id) throws DAOException {
		Message result = null;
		result = (Message) getSession().createQuery("FROM Message WHERE id = :id")
				.setLong("id", id).uniqueResult();
		return result;
	}

	public void deleteById(int id) throws DAOException {
			getSession().delete(find(id));
	}

	@SuppressWarnings("unchecked")
	public List<Message> findByUser(User user) throws DAOException {
		List<Message> list = null;
		try {
			String hql = "FROM Message WHERE user_id = :user_id";
			Query query = getSession().createQuery(hql);
			query.setLong("user_id", user.getId());
			list = query.list();
		} catch (Exception e) {
			throw new DAOException(e);
		} finally {
			getSession().close();
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
		List<Message> result = null;
		Query query = getSession()
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
