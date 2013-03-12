package com.seventysevenagency.chat.mvc.models;

import com.seventysevenagency.chat.domain.User;

public class RegisterModel extends IModel {
	public User user;

	public RegisterModel() {
		user = new User();
	}
}
