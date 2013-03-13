package com.seventysevenagency.chat.mvc.mapping;

import com.seventysevenagency.chat.mvc.controllers.IController;
import com.seventysevenagency.chat.mvc.modelcreators.ModelCreator;

public class UrlMapping {

	private ModelCreator modelCreator;
	private Class controller;
	private String jsp;
	private String url;

	public ModelCreator getModelCreator() {
		return modelCreator;
	}

	public void setModelCreator(ModelCreator modelCreator) {
		this.modelCreator = modelCreator;
	}

	public Class getController() {
		return controller;
	}

	public void setController(Class controller) {
		this.controller = controller;
	}

	public String getJsp() {
		return jsp;
	}

	public void setJsp(String jsp) {
		this.jsp = jsp;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
