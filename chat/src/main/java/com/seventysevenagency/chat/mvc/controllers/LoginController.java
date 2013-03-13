package com.seventysevenagency.chat.mvc.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.seventysevenagency.chat.dao.UserDAO;
import com.seventysevenagency.chat.domain.User;
import com.seventysevenagency.chat.mvc.models.IModel;
import com.seventysevenagency.chat.mvc.models.LoginModel;
import com.seventysevenagency.chat.util.ConnectedUsersListener;
@Component
public class LoginController extends ControllerBase {
	@Autowired
	private UserDAO userDAO;
	public void execute(IModel model, HttpServletRequest request) {
		this.redirectUrl = null;
		LoginModel loginModel = (LoginModel) model;
		String username = loginModel.getUsername();
		String password = loginModel.getPassword();
		if (username != null || password != null) {
			if (username == "" || password == "") {
				loginModel.addWarning("error", "Please fill in all fields");
			} else {
				User user = userDAO.authorize(username, password);
				if (user != null) {
					loginModel.addWarning("logged", "Logged");
					
					HttpSession userSession = request.getSession();
					Integer userID = user.getId();
					userSession.setAttribute("userid", userID);
					
					ConnectedUsersListener.addUser(userID);
					
					this.redirectUrl = "chatroom";
				} else {
					loginModel.addWarning("error",
							"Invalid username or password");
				}

			}
		}
	}
}
