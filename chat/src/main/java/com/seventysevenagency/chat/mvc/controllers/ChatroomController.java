package com.seventysevenagency.chat.mvc.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

@Component
public class ChatroomController extends ControllerBase {
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private MessageDAO messageDAO;
	@Autowired
	private ConversationDAO conversationDAO;
	private User activeUser;
	private List<Conversation> conversationList;

	public void execute(IModel model, HttpServletRequest request) {
		fetchDataFromSession(request);
		String requestMethod = request.getMethod();		
		ChatroomModel chatroomModel = (ChatroomModel) model;
		int conversationId = 0;
		try{
			 conversationId = chatroomModel.getConversationId();
		}catch(Exception e){
			
		}		
		conversationList = conversationDAO.selectAll(activeUser.getId());
		if(conversationId == 0){
			if(!conversationList.isEmpty()){
				Conversation conversation = conversationList.get(0);
				conversationId = conversation.getId();	
			}else{
				chatroomModel.setAction("conversation");
			}	
		}
		System.out.println(conversationId);
		System.out.println(request.getParameter("newConversationName"));
		if (requestMethod.equals("POST")) {
			handlePostRequests(request, chatroomModel);
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

	private void handlePostRequests(HttpServletRequest request,
			ChatroomModel chatroomModel) {
		if(validateModel(chatroomModel)) {
			Set<Message> messages = activeUser.getMessages();				
			Message msg =  chatroomModel.getMessage();
			msg.setUser(activeUser);
			long unixTime = System.currentTimeMillis()/1000L;
			msg.setDate(unixTime);
			messages.add(msg);
			userDAO.update(activeUser);
		} else if(!request.getParameter("newConversationName").isEmpty()) {
			Conversation newConversation = new Conversation();
			newConversation.setName(request.getParameter("newConversationName"));
			newConversation.setType("public");
			conversationDAO.create(newConversation);
			conversationList = conversationDAO.selectAll(activeUser.getId());
		}
	}

	private void fetchDataFromSession(HttpServletRequest request) {
		HttpSession userSession = request.getSession();
		int userId = (Integer) userSession.getAttribute("userid");
			activeUser = userDAO.findById(userId);
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
