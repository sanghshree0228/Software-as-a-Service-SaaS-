package com.carematcher.Model;


import java.io.Serializable;


public class Request {
	private static final long serialVersionUID = -1196857906443889924L;
	private String ServiceName;
	public String getServiceName() {
		return ServiceName;
	}
	public void setServiceName(String serviceName) {
		ServiceName = serviceName;
	}
	public String getServiceType() {
		return ServiceType;
	}
	public void setServiceType(String serviceType) {
		ServiceType = serviceType;
	}
	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	public String getPhoneNumber() {
		return PhoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	private String ServiceType;
	private String FirstName;
	private String LastName;
	private String PhoneNumber;
	private String Email;
	public Request(String ServiceName, String ServiceType, String FirstName, String LastName,
			String PhoneNumber,String Email)
	{
		this.ServiceName=ServiceName;
		this.ServiceType=ServiceType;
		this.FirstName=FirstName;
		this.LastName=LastName;
		this.PhoneNumber=PhoneNumber;
		this.Email=Email;
		
	}

}
