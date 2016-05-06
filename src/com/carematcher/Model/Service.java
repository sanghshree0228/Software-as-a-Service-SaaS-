package com.carematcher.Model;
import java.io.Serializable;


public class Service implements Serializable  {
	//private static final long serialVersionUID = -1196857906443889924L;
	private int serviceId;
	private String serviceName;
	private String serviceType;
	private String companyName;
	private String address;
	private String aptSuiteOther;
	private String city;
	private String state;
	private String zipCode;
	private String phoneNumber;
	private String emailAddress;
	private String desc;
	private int userId;
	private String userName;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	

	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public int getServiceId() {
		return serviceId;
	}
	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAptSuiteOther() {
		return aptSuiteOther;
	}
	public void setAptSuiteOther(String aptSuiteOther) {
		this.aptSuiteOther = aptSuiteOther;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
		public Service(int serviceId,String serviceName,String serviceType, String companyName,String address,
	String aptSuiteOther,String city,String state,String zipCode,String phoneNumber,String emailAddress, String desc,int userid)
		{
			this.serviceId=serviceId;
			this.serviceName=serviceName;
			this.serviceType=serviceType;
			this.companyName=companyName;
			this.address=address;
			this.aptSuiteOther=aptSuiteOther;
			this.city=city;
			this.state=state;
			this.zipCode=zipCode;
			this.phoneNumber=phoneNumber;
			this.emailAddress=emailAddress;
			this.desc=desc;
			this.userId=userid;
		}

}
