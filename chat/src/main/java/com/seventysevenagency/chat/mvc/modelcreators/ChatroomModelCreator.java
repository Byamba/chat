package com.seventysevenagency.chat.mvc.modelcreators;

import javax.servlet.http.HttpServletRequest;

import com.seventysevenagency.chat.domain.Message;
import com.seventysevenagency.chat.mvc.models.ChatroomModel;
import com.seventysevenagency.chat.mvc.models.IModel;

public class ChatroomModelCreator extends ModelCreator {

	@Override
	public IModel createModel(HttpServletRequest request) {
		String messageText = request.getParameter("message");
		String conversation = request.getParameter("conversation");
		Integer conversationId;
		//Integer conversationId = 1;
		//FIXME: implement conversation creation		

		String logout = request.getParameter("logout");

		ChatroomModel chatroomModel = new ChatroomModel();
		if (conversation !=null && !conversation.isEmpty()) {
			conversationId = Integer.valueOf(conversation);
			chatroomModel.setConversationId(conversationId);
			
		}else{
			conversationId = 1;
		}
		if(messageText != null && !messageText.isEmpty()){
			Message message = new Message();
			message.setText(messageText);
			message.setConversationId(conversationId);
			chatroomModel.setMessage(message);
		}		
		chatroomModel.setAction(logout);

		return chatroomModel;
	}

}
