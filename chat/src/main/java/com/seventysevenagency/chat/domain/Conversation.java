package com.seventysevenagency.chat.domain;

import com.seventysevenagency.chat.types.ConversationType.Type;

public class Conversation {	
	private int id;
	private Type type;
	private String name;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type mType) {
		this.type = mType;
	}
	public String getName() {
		return name;
	}
	public void setName(String mName) {
		this.name = mName;
	}
	
}
