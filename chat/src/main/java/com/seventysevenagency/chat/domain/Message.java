package com.seventysevenagency.chat.domain;

public class Message {
	private int id;
	private int conversationId;
	private User user;
	private String text;
	private long date;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getConversationId() {
		return conversationId;
	}

	public void setConversationId(int mConversationId) {
		this.conversationId = mConversationId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public long getDate() {
		return date;
	}

	public void setDate(long unixTime) {
		this.date = unixTime;
	}

}
