package com.seventysevenagency.chat.util;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class ConnectedUsersListener implements HttpSessionListener {

	
	/**
	 * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
	 */
	public void sessionCreated(HttpSessionEvent event) {
		HttpSession session = event.getSession();
		addUser((Integer) session.getAttribute("userid"));
		System.out.println("Session_Created" + (String) session.getAttribute("userid"));
	}

	/**
	 * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
	 */
	public void sessionDestroyed(HttpSessionEvent event) {
		HttpSession session = event.getSession();
		removeUser( (Integer) session.getAttribute("userid"));
	}
	
	private static Set<Integer> userInfo = new HashSet<Integer>();

	public static void removeUser(Integer userId) {
		userInfo.remove(userId);
	}

	public static void addUser(Integer userId) {
		userInfo.add(userId);
	}

	public static Integer[] getActiveUsers() {
		for (Integer int1 : userInfo) {
			System.out.println(int1);
		}
		Integer[] usernameArray = new Integer[userInfo.size()];
		return userInfo.toArray(usernameArray);
	}

}
