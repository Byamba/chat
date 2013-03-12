package com.seventysevenagency.chat.mvc.models;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.seventysevenagency.chat.domain.Message;
import com.seventysevenagency.chat.domain.User;

public class ChatroomModel extends IModel {
	private List<Message> messageList;
	private List<User> activeUsersList;
	private String action;
	private Message message;
	private Integer conversationId;

	public ChatroomModel() {
		messageList = new LinkedList<Message>();
		activeUsersList = new ArrayList<User>();
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public List<Message> getMessageList() {
		return messageList;
	}

	public void setMessageList(List<Message> messageList) {
		this.messageList = messageList;
	}

	public Integer getConversationId() {
		return conversationId;
	}

	public void setConversationId(Integer conversationId) {
		this.conversationId = conversationId;
	}

	public List<User> getActiveUsersList() {
		return activeUsersList;
	}

	public void setActiveUsersList(List<User> activeUsersList) {
		this.activeUsersList = activeUsersList;
	}

}
