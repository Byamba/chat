package com.seventysevenagency.chat.mvc.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seventysevenagency.chat.dao.ConversationDAO;
import com.seventysevenagency.chat.dao.DAOException;
import com.seventysevenagency.chat.dao.MessageDAO;
import com.seventysevenagency.chat.dao.UserDAO;
import com.seventysevenagency.chat.domain.Conversation;
import com.seventysevenagency.chat.domain.Message;
import com.seventysevenagency.chat.domain.User;
import com.seventysevenagency.chat.mvc.models.ChatroomModel;
import com.seventysevenagency.chat.mvc.models.IModel;
import com.seventysevenagency.chat.util.ConnectedUsersListener;

@Service
public class ChatroomController extends ControllerBase {
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private MessageDAO messageDAO;
	@Autowired
	private ConversationDAO conversationDAO;
	public void execute(IModel model, HttpServletRequest request) {
		String requestMethod = request.getMethod();
		ChatroomModel chatroomModel = (ChatroomModel) model;
		int conversationId = 0;
		try{
			 conversationId = chatroomModel.getConversationId();
		}catch(Exception e){
			
		}
		System.out.println(conversationId);
		userDAO = applicationContext.getBean(UserDAO.class);
		messageDAO = applicationContext.getBean(MessageDAO.class);	
		conversationDAO = applicationContext.getBean(ConversationDAO.class);
		List<Conversation> conversationList = conversationDAO.selectAll();
		if(conversationId == 0){
			Conversation conversation = conversationList.get(0);
			conversationId = conversation.getId();			
		}
	
		if (requestMethod.equals("POST") && validateModel(chatroomModel)) {
			HttpSession userSession = request.getSession();
			int userId = (Integer) userSession.getAttribute("userid");
				User activeUser = userDAO.findById(userId);
				Set<Message> messages = activeUser.getMessages();
				Message msg =  chatroomModel.getMessage();
				msg.setUser(activeUser);
				long unixTime = System.currentTimeMillis()/1000L;
				msg.setDate(unixTime);
				messages.add(msg);
		} else if (chatroomModel.getAction() != null && chatroomModel.getAction().equals("logout")) {
			HttpSession userSession = request.getSession();
			userSession.removeAttribute("userid");
			return;
		}
		// Filling model with messages and active users to display
		try {
			List<Message> messageList = messageDAO
					.findByConversationId(conversationId);
			List<User> usersList = new ArrayList<User>();
			Integer[] userIds = ConnectedUsersListener.getActiveUsers();
			for (Integer userId : userIds) {
				usersList.add(userDAO.findById(userId));
			}
			chatroomModel.setConversationId(conversationId);
			chatroomModel.setMessageList(messageList);
			chatroomModel.setActiveUsersList(usersList);
			chatroomModel.setConversationList(conversationList);
			
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	public boolean validateModel(ChatroomModel chatroomModel) {
		boolean isValid = true;

		Message newMessage = chatroomModel.getMessage();

		if (newMessage == null || newMessage.getText().isEmpty()) {
			isValid = false;
			chatroomModel.addWarning("message_error",
					"Please provide message to send");
		}
		return isValid;
	}
}
