package com.sanghshree.restful_service.carematcher.Model;

public class UserList {
	/**
	 * 
	 */
	String Fname=null;
	String Lname=null;
	String Email=null;
	String Username=null;
	String Password=null;
	String Phone=null;
	String RegisteredAs=null;
	String UserId=null;
	
	public String getUserId() {
		return UserId;
	}
	public void setUserId(String userId) {
		UserId = userId;
	}
	public String getFname() {
		return Fname;
	}
	public void setFname(String fname) {
		Fname = fname;
	}
	public String getLname() {
		return Lname;
	}
	public void setLname(String lname) {
		Lname = lname;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getPhone() {
		return Phone;
	}
	public void setPhone(String phone) {
		Phone = phone;
	}
	public String getRegisteredAs() {
		return RegisteredAs;
	}
	public void setRegisteredAs(String registeredAs) {
		RegisteredAs = registeredAs;
	}
	

}
