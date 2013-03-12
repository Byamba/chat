package com.seventysevenagency.chat.domain.builders;

import java.util.Set;

import com.seventysevenagency.chat.domain.Message;
import com.seventysevenagency.chat.domain.User;

public class UserBuilder {
		private User newUser = new User();

		public UserBuilder setId(int id) {
			newUser.setId(id);
			return this;
		}

		public UserBuilder setName(String name) {
			newUser.setName(name);
			return this;
		}

		public UserBuilder setSurname(String surname) {
			newUser.setSurname(surname);
			return this;
		}

		public UserBuilder setUsername(String username) {
			newUser.setUsername(username);
			return this;
		}

		public UserBuilder setPassword(String password) {
			newUser.setPassword(password);
			return this;
		}

		public UserBuilder setEmail(String email) {
			newUser.setEmail(email);
			return this;
		}

		public UserBuilder setMessages(Set<Message> messages) {
			newUser.setMessages(messages);
			return this;
		}

		public User build() {
			return newUser;
		}
	}