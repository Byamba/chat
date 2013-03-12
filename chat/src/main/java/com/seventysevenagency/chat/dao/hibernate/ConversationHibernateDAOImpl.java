package com.seventysevenagency.chat.dao.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.seventysevenagency.chat.dao.ConversationDAO;
import com.seventysevenagency.chat.dao.DAOException;
import com.seventysevenagency.chat.domain.Conversation;
import com.seventysevenagency.chat.domain.Message;
@Repository
@Transactional
public class ConversationHibernateDAOImpl extends BaseHibernateDAO implements ConversationDAO {

	public Long create(Conversation conversation) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	public int deleteById(Long id) throws DAOException {
		// TODO Auto-generated method stub
		return 0;
	}

	public int delete(Conversation conversation) throws DAOException {
		// TODO Auto-generated method stub
		return 0;
	}

	public Conversation select(Long id) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	public Conversation selectLast() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	public void update(Conversation conversation) throws DAOException {
		// TODO Auto-generated method stub
		
	}

	public List<Conversation> selectAll() {
		List<Conversation> list = null;
		Query query = getSession()
				.createQuery("FROM Conversation");
		list = query.list();
		return list;
	}

	

}
