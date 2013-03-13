package com.seventysevenagency.chat.dao.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.seventysevenagency.chat.dao.ConversationDAO;
import com.seventysevenagency.chat.dao.DAOException;
import com.seventysevenagency.chat.domain.Conversation;
import com.seventysevenagency.chat.domain.Message;

@Repository
@Transactional
public class ConversationHibernateDAOImpl extends BaseHibernateDAO implements
		ConversationDAO {

	public Long create(Conversation conversation) throws DAOException {
		return (Long) getSession().save(conversation);
	}

	public int deleteById(Long id) throws DAOException {
		Query query = getSession().createQuery(
				"DELETE Conversation WHERE id = :id");
		query.setParameter("id", id);
		int result = query.executeUpdate();

		return result;
	}

	public int delete(Conversation conversation) throws DAOException {
		getSession().delete(conversation);
		return 1;
	}

	public Conversation select(Long id) throws DAOException {
		return (Conversation) getSession()
				.createQuery("FROM Conversation WHERE id = :id")
				.setParameter("id", id).uniqueResult();
	}

	public Conversation selectLast() throws DAOException {
		return (Conversation) getSession().createCriteria(Conversation.class)
				.addOrder(Order.desc("id")).setMaxResults(1).uniqueResult();
	}

	public void update(Conversation conversation) throws DAOException {
		getSession().update(conversation);

	}

	public List<Conversation> selectAll() {
		return getSession().createQuery("FROM Conversation").list();
	}

}
