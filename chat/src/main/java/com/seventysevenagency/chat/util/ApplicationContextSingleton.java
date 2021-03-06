package com.seventysevenagency.chat.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationContextSingleton {

	private static final ApplicationContext applicationContext;
	static {
			applicationContext = new ClassPathXmlApplicationContext(
					"/spring/config/BeanLocations.xml");
	}

	public static ApplicationContext get() {
		return applicationContext;
	}
}
