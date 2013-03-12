package com.seventysevenagency.chat.dao;

public class DAOException extends Exception {
	private static final long serialVersionUID = -5508359399429770876L;

	public DAOException(Throwable e) {
		super("DB Exception", e);
	}
}
