package com.seventysevenagency.chat.mvc.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.transaction.annotation.Transactional;

import com.seventysevenagency.chat.dao.DAOException;
import com.seventysevenagency.chat.dao.MessageDAO;
import com.seventysevenagency.chat.dao.UserDAO;
import com.seventysevenagency.chat.dao.hibernate.MessageHibernateDAOImpl;
import com.seventysevenagency.chat.dao.hibernate.UserHibernateDAOImpl;
import com.seventysevenagency.chat.domain.Message;
import com.seventysevenagency.chat.domain.User;
import com.seventysevenagency.chat.mvc.models.ChatroomModel;
import com.seventysevenagency.chat.mvc.models.IModel;
import com.seventysevenagency.chat.util.ConnectedUsersListener;

@Transactional
public class ChatroomController extends ControllerBase {

	public void execute(IModel model, HttpServletRequest request) {
		
		String requestMethod = request.getMethod();
		ChatroomModel chatroomModel = (ChatroomModel) model;

		UserDAO userDao = new UserHibernateDAOImpl();
		MessageDAO messageDao = new MessageHibernateDAOImpl();		
		if (requestMethod.equals("POST") && validateModel(chatroomModel)) {
			HttpSession userSession = request.getSession();
			int userId = (int) userSession.getAttribute("userid");

			try {
				User activeUser = userDao.findById(userId);
				Set<Message> messages = activeUser.getMessages();
				Message msg =  chatroomModel.getMessage();
				msg.setUser(activeUser);
				long unixTime = System.currentTimeMillis()/1000L;
				msg.setDate(unixTime);
				messages.add(msg);
				userDao.flushChanges();
			} catch (DAOException e) {
				chatroomModel.addWarning("error", "Error sending message");
				e.printStackTrace();
			}
		} else if (chatroomModel.getAction() != null && chatroomModel.getAction().equals("logout")) {
			HttpSession userSession = request.getSession();
			userSession.removeAttribute("userid");
			return;
		}
		// Filling model with messages and active users to display
		try {
			List<Message> messageList = messageDao
					.findByConversationId(chatroomModel.getConversationId());
			List<User> usersList = new ArrayList<User>();
			Integer[] userIds = ConnectedUsersListener.getActiveUsers();
			for (Integer userId : userIds) {
				usersList.add(userDao.findById(userId));
			}

			chatroomModel.setMessageList(messageList);
			chatroomModel.setActiveUsersList(usersList);

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
