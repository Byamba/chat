package com.seventysevenagency.chat.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
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

	public int create(Conversation conversation) {
		return (Integer) getSession().save(conversation);
	}

	public int deleteById(Long id) {
		Query query = getSession().createQuery(
				"DELETE Conversation WHERE id = :id");
		query.setParameter("id", id);
		int result = query.executeUpdate();

		return result;
	}

	public int delete(Conversation conversation) {
		getSession().delete(conversation);
		return 1;
	}

	public Conversation select(Long id) {
		return (Conversation) getSession()
				.createQuery("FROM Conversation WHERE id = :id")
				.setParameter("id", id).uniqueResult();
	}

	public Conversation selectLast() {
		return (Conversation) getSession().createCriteria(Conversation.class)
				.addOrder(Order.desc("id")).setMaxResults(1).uniqueResult();
	}

	public void update(Conversation conversation) {
		getSession().update(conversation);

	}

	public List<Conversation> selectAllPublic() {
		return getSession().createQuery("FROM Conversation WHERE type = :type")
				.setString("type", "public").list();
	}
	
	public List<Conversation> selectAllPrivate(int userId) {

		DetachedCriteria subCriteria = DetachedCriteria.forClass(Message.class);
		subCriteria.add(Restrictions.eq("id", userId));
		subCriteria.setProjection(Projections.property("id"));
		
		return getSession().createCriteria(Conversation.class).add(Subqueries.propertyEq("id", subCriteria)).list();
	}
	
	public List<Conversation> selectAll(int userId) {
		ArrayList<Conversation> result = new ArrayList<Conversation>();
		addConversationsToResult(result, selectAllPublic());
		addConversationsToResult(result, selectAllPrivate(userId));
		return result;
	}

	private void addConversationsToResult(ArrayList<Conversation> result,
			List<Conversation> publicConversations) {
		if(publicConversations != null) {
			result.addAll(publicConversations);
		}
	}

}
