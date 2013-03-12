package com.seventysevenagency.chat.mvc.modelcreators;

import javax.servlet.http.HttpServletRequest;

import com.seventysevenagency.chat.mvc.models.IModel;
import com.seventysevenagency.chat.mvc.models.LoginModel;


public class LoginModelCreator extends ModelCreator {

	@Override
	public IModel createModel(HttpServletRequest request) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		LoginModel model = new LoginModel();
		model.setUsername(username);
		model.setPassword(password);
		
		return model;
	}

}
