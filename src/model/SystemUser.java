package model;

import java.io.Serializable;

public abstract class SystemUser implements Serializable{

	//Constants
	private static final long serialVersionUID = 1;
	//Atributes
	private String username;
	private String password;
	
	//Constructor
	public SystemUser(String user,String pass) {
		username = user;
		password = pass;
	}
	
	//Getters and Setters

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getUsername() {
		return username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	//***************************************************************************************************************************

	
	
	

}
