package com.michael.pan.foodorder.Model;

public class User {
	private String Name;
	private String Password;

	public User() {
	}

	public User(String name, String password){
		this.Name = name;
		this.Password = password;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}
}
