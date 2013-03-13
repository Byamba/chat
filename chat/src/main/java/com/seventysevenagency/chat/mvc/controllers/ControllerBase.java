package com.seventysevenagency.chat.mvc.controllers;



public abstract class ControllerBase implements IController {
	protected String redirectUrl = null;
	public String getRedirectUrl() {
		return redirectUrl;
	}
}
