package com.seventysevenagency.chat.dao;

import java.util.List;

import com.seventysevenagency.chat.domain.Conversation;

public interface ConversationDAO {
	public Long create(Conversation conversation);

	public int deleteById(Long id);

	public int delete(Conversation conversation);

	public Conversation select(Long id);

	public Conversation selectLast();

	public List<Conversation> selectAll(int userId);

	public void update(Conversation conversation);
}
