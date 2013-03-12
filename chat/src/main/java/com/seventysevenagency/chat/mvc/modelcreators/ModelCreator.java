package com.seventysevenagency.chat.mvc.modelcreators;

import javax.servlet.http.HttpServletRequest;

import com.seventysevenagency.chat.mvc.models.IModel;


public abstract class ModelCreator {
	public abstract IModel createModel(HttpServletRequest request);
}
