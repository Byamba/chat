package com.seventysevenagency.chat.mvc.controllers;

import org.springframework.context.ApplicationContext;

import com.seventysevenagency.chat.util.ApplicationContextSingleton;


public abstract class ControllerBase implements IController {
	protected String redirectUrl = null;
	public String getRedirectUrl() {
		return redirectUrl;
	}
	protected  ApplicationContext applicationContext = ApplicationContextSingleton.get();
}
