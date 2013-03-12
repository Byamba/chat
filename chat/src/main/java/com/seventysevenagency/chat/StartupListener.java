package com.seventysevenagency.chat;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.seventysevenagency.chat.util.ApplicationContextSingleton;

/**
 * Application Lifecycle Listener implementation class StartupListener
 */
public class StartupListener implements ServletContextListener {

	public void contextInitialized(ServletContextEvent arg0) {
		ApplicationContextSingleton.get();
	}

	public void contextDestroyed(ServletContextEvent arg0) {
	}

}
