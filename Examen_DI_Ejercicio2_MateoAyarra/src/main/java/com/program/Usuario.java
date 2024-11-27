package com.program;

import java.io.Serializable;
import java.util.Objects;

public class Usuario implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	
	public Usuario(String username, String password) {
		this.username = username;
		this.password = password;
	}
	public Usuario(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	
	public boolean checkPassword(String pass) {
		return pass.equals(password);
	}

	@Override
	public int hashCode() {
		return Objects.hash(username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(username, other.username);
	}
	@Override
	public String toString() {
		return username;
	}
	
}
