package com.nortexdev;

public class Person2 {
	private String nick;

	public Person2(String nick) {
		this.nick = nick;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	@Override
	public String toString() {
		return "Person2{" +
			"nick='" + nick + '\'' +
			'}';
	}
}
