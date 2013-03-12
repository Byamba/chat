package com.seventysevenagency.chat.mvc.controllers;

import javax.servlet.http.HttpServletRequest;

import com.seventysevenagency.chat.mvc.models.IModel;

public interface IController {
	String getRedirectUrl();
	void execute(IModel model, HttpServletRequest request);
}
