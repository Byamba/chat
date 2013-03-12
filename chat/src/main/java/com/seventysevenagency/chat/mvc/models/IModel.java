package com.seventysevenagency.chat.mvc.models;

import java.util.HashMap;
import java.util.Map;

public class IModel {
	private Map<String, String> errorMessages;
	
	public IModel() {
		errorMessages = new HashMap<String, String>();
	}
	
	public void addWarning(String tag, String message) {
		errorMessages.put(tag, message);
	}
	
	public String getWarning(String tag) {
		return errorMessages.get(tag);
	}
}
