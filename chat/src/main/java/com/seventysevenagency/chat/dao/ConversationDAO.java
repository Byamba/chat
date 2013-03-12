package com.seventysevenagency.chat.dao;

import com.seventysevenagency.chat.domain.Conversation;

public interface ConversationDAO {
	public Long create(Conversation conversation) throws DAOException;
	public int deleteById(Long id)  throws DAOException;
	public int delete(Conversation conversation)  throws DAOException;
	public Conversation select(Long id)  throws DAOException;
	public Conversation selectLast()  throws DAOException;
	public void update(Conversation conversation) throws DAOException;
}
