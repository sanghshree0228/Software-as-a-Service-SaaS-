package com.carematcher.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.mindrot.jbcrypt.BCrypt;

import com.carematcher.Model.Role;
import com.carematcher.Model.User;
import com.carematcher.Model.Service;
import com.carematcher.Model.*;
public class UserDAO {
	
//	public static ArrayList<User> getUsers() throws SQLException {
//		ArrayList<User> users = new ArrayList<User>();
//		try (Connection connection = Database.getInstance().getConnection()) {
//			try (PreparedStatement statment = connection.prepareStatement(
//					"SELECT user_id, username, first_name, middle_name, last_name, address,"
//					+ " apt_suite_other, city, state_code, zip_code, phone_number, email_address, birth_date"
//					+ " FROM carematcher.user NATURAL JOIN carematcher.user_login")) {
//				try (ResultSet rs = statment.executeQuery()) {
//					while (rs.next()) {
//						User user = new User(
//								rs.getInt("user_id"),
//								rs.getString("username"),
//								null,
//								rs.getString("first_name"),
//								rs.getString("middle_name"),
//								rs.getString("last_name"),
//								rs.getString("address"),
//								rs.getString("apt_suite_other"),
//								rs.getString("city"),
//								rs.getString("state_code"),
//								rs.getString("zip_code"),
//								rs.getString("phone_number"),
//								rs.getString("email_address"),
//								rs.getDate("birth_date")
//								);
//						users.add(user);
//					}
//				}
//			}
//		}
//		return users;
//	}
	
	//this is to show list of service for service provider on table
	public static ArrayList<Service> getService() throws SQLException {
		ArrayList<Service> service = new ArrayList<Service>();
		try (Connection connection = Database.getInstance().getConnection()) {
			try (PreparedStatement statment = connection.prepareStatement("SELECT \"serviceId\", \"serviceName\", \"serviceType\",\"companyName\", \"address\","+
					"\"aptSuiteOther\", \"city\", \"state\", \"zipCode\", \"phoneNumber\", \"emailAddress\",\"Description\",\"userId\""+
					"FROM carematcher.\"service\" join carematcher.login ON \"userId\"=\"user_id\";")) {
				try (ResultSet rs = statment.executeQuery()) {
					while (rs.next()) {
						//System.out.println("once");
						Service serv = new Service(
								rs.getInt("serviceId"),
								rs.getString("serviceName"),
								rs.getString("serviceType"),
								rs.getString("companyName"),
								rs.getString("address"),
								rs.getString("aptSuiteOther"),
								rs.getString("city"),
								rs.getString("state"),
								rs.getString("zipCode"),
								rs.getString("phoneNumber"),
								rs.getString("emailAddress"),
								rs.getString("Description"),
								rs.getInt("userId")
								);
						service.add(serv);
					}
				}
			}
		}
		System.out.println(service.size());
		return service;
	}

	public static ArrayList<Request> getRequests() throws SQLException {
		ArrayList<Request> req = new ArrayList<Request>();
		try (Connection connection = Database.getInstance().getConnection()) {
			try (PreparedStatement statment = connection.prepareStatement("SELECT \"serviceName\",\"serviceType\",\"first_name\","
					+ "\"last_name\",\"phone_number\",\"email_address\" "+
					" FROM carematcher.\"user\" JOIN carematcher.\"UserServices\" ON \"userId\"=\"user_id\"")) {
				try (ResultSet rs = statment.executeQuery()) {
					while (rs.next()) {
						//System.out.println("once");
						Request request = new Request(
								
								rs.getString("serviceName"),
								rs.getString("serviceType"),
								rs.getString("first_name"),
								rs.getString("last_name"),
								rs.getString("phone_number"),
								rs.getString("email_address")
								
								);
						req.add(request);
					}
				}
			}
		}
		//System.out.println(service.size());
		return req;
	}

	
	
	//this is to show all avilable service
	public static ArrayList<Service> getAllService() throws SQLException {
		ArrayList<Service> service = new ArrayList<Service>();
		//System.out.println();
		try (Connection connection = Database.getInstance().getConnection()) {
			try (PreparedStatement statment = connection.prepareStatement("SELECT \"serviceId\", \"serviceName\", \"serviceType\",\"companyName\", \"address\","+
					"\"aptSuiteOther\", \"city\", \"state\", \"zipCode\", \"phoneNumber\", \"emailAddress\",\"Description\",\"userId\""+
					"FROM carematcher.service")) {
				try (ResultSet rs = statment.executeQuery()) {
					while (rs.next()) {
						System.out.println("once");
						Service serv = new Service(
								rs.getInt("serviceId"),
								rs.getString("serviceName"),
								rs.getString("serviceType"),
								rs.getString("companyName"),
								rs.getString("address"),
								rs.getString("aptSuiteOther"),
								rs.getString("city"),
								rs.getString("state"),
								rs.getString("zipCode"),
								rs.getString("phoneNumber"),
								rs.getString("emailAddress"),
								rs.getString("Description"),
								rs.getInt("userId")
								);
						service.add(serv);
					}
				}
			}
		}
		System.out.printf("Total services are",service.size());
		return service;
	}
	//user perticular services
	public static ArrayList<Service> getUserService() throws SQLException {
		ArrayList<Service> service = new ArrayList<Service>();
		//System.out.println();
		try (Connection connection = Database.getInstance().getConnection()) {
			try (PreparedStatement statment = connection.prepareStatement("SELECT \"serviceId\", \"serviceName\", \"serviceType\",\"companyName\", \"address\","+
					"\"aptSuiteOther\", \"city\", \"state\", \"zipCode\", \"phoneNumber\", \"emailAddress\",\"Description\",\"userId\""+
					"FROM carematcher.\"UserServices\"  JOIN carematcher.\"login\" ON \"userId\"=\"user_id\";")) {
				try (ResultSet rs = statment.executeQuery()) {
					while (rs.next()) {
						System.out.println("once");
						Service serv = new Service(
								rs.getInt("serviceId"),
								rs.getString("serviceName"),
								rs.getString("serviceType"),
								rs.getString("companyName"),
								rs.getString("address"),
								rs.getString("aptSuiteOther"),
								rs.getString("city"),
								rs.getString("state"),
								rs.getString("zipCode"),
								rs.getString("phoneNumber"),
								rs.getString("emailAddress"),
								rs.getString("Description"),
								rs.getInt("userId")
								);
						service.add(serv);
					}
				}
			}
		}
		System.out.printf("Total services are",service.size());
		return service;
	}
	
	
	
	public static ArrayList<Service> getMyService() throws SQLException {
		ArrayList<Service> service = new ArrayList<Service>();
		System.out.println();
		try (Connection connection = Database.getInstance().getConnection()) {
			try (PreparedStatement statment = connection.prepareStatement("SELECT \"serviceId\", \"serviceName\", \"serviceType\",\"companyName\", \"address\","+
					"\"aptSuiteOther\", \"city\", \"state\", \"zipCode\", \"phoneNumber\", \"emailAddress\",\"Description\",\"userId\""+
					"FROM carematcher.UserServices;")) {
				try (ResultSet rs = statment.executeQuery()) {
					while (rs.next()) {
						//System.out.println("once");
						Service serv = new Service(
								rs.getInt("serviceId"),
								rs.getString("serviceName"),
								rs.getString("serviceType"),
								rs.getString("companyName"),
								rs.getString("address"),
								rs.getString("aptSuiteOther"),
								rs.getString("city"),
								rs.getString("state"),
								rs.getString("zipCode"),
								rs.getString("phoneNumber"),
								rs.getString("emailAddress"),
								rs.getString("Description"),
								rs.getInt("userId")
								);
						service.add(serv);
					}
				}
			}
		}
		System.out.println(service.size());
		return service;
	}
	
	public static Service getService(int userid) throws SQLException {
		Service returnService = null;
		try (Connection connection = Database.getInstance().getConnection()) {
			try (PreparedStatement statment = connection.prepareStatement(
					"SELECT \"serviceId\", \"serviceName\", \"serviceType\",\"companyName\", \"address\","
					+ "\"aptSuiteOther\", \"city\", \"state\", \"zipCode\", \"phoneNumber\", \"emailAddress\","
					+ " \"Description\",\"userId\""
					+ " FROM carematcher.\"UserServices\"  WHERE \"userId\" = ?")) {
				statment.setInt(1, userid);
				try (ResultSet rs = statment.executeQuery()) {
					if (rs.next()) {
						returnService = new Service(
								rs.getInt("serviceId"),
								rs.getString("serviceName"),
								rs.getString("serviceType"),
								rs.getString("companyName"),
								rs.getString("address"),
								rs.getString("aptSuiteOther"),
								rs.getString("city"),
								rs.getString("state"),
								rs.getString("zipCode"),
								rs.getString("phoneNumber"),
								rs.getString("emailAddress"),
								rs.getString("Description"),
								rs.getInt("userId")
								);
					}
				}
			}
		}
		return returnService;
	}
	
	//TODO update method
	public static boolean insertUser(User user) throws SQLException {
		boolean success = false;
		try (Connection connection = Database.getInstance().getConnection()) {
			connection.setAutoCommit(false);
			try (PreparedStatement userStatment = connection.prepareStatement(
					"INSERT INTO carematcher.user"
					+ " (first_name, middle_name, last_name, address, apt_suite_other, city, state_code, zip_code, phone_number, email_address, birth_date,role)"
					+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)", Statement.RETURN_GENERATED_KEYS)) {
				userStatment.setString(1, user.getFirstName());
				userStatment.setString(2, user.getMiddleName().trim().isEmpty() ? null : user.getMiddleName());
				userStatment.setString(3, user.getLastName());
				userStatment.setString(4, user.getAddress());
				userStatment.setString(5, user.getAptSuiteOther().trim().isEmpty() ? null : user.getAptSuiteOther());
				userStatment.setString(6, user.getCity());
				userStatment.setString(7, user.getState());
				userStatment.setString(8, user.getZipCode());
				userStatment.setString(9, user.getPhoneNumber());
				userStatment.setString(10, user.getEmailAddress().trim().isEmpty() ? null : user.getEmailAddress());
				userStatment.setDate(11, new Date(user.getBirthDate().getTime()));
				
				if(user.getRole().equals("ServiceProvider"))
				{
					user.setRoleid(1);
				}
				else{
					user.setRoleid(2);
				}
				userStatment.setInt(12, user.getRoleid());
				if (userStatment.executeUpdate() > 0) {
					try (ResultSet rs = userStatment.getGeneratedKeys()) {
						if (rs.next()) {//TODO  else break?
							user.setUserId(rs.getInt(1));
						}
						try (PreparedStatement userLoginStatement = connection.prepareStatement("INSERT INTO carematcher.user_login VALUES (?, ?, ?, ?)")) {
							userLoginStatement.setInt(1, user.getUserId());
							userLoginStatement.setString(2, user.getUsername());
							userLoginStatement.setString(3, user.getPassword());
							userLoginStatement.setInt(4, user.getRoleid());
							if (userLoginStatement.executeUpdate() > 0) {
								connection.commit();
								success = true;
							}
						}
					}
				}
			}
		}
		return success;
	}
	
	
	//this is for new service
	public static boolean insertService(Service serv) throws SQLException {
		boolean success = false;
		System.out.println(serv);
		
		try (Connection connection = Database.getInstance().getConnection()) {
			connection.setAutoCommit(false);
			try (PreparedStatement serviceStatement = connection.prepareStatement(
					"INSERT INTO carematcher.service"
					+ " (\"serviceName\", \"serviceType\", \"companyName\", \"address\", \"aptSuiteOther\", \"city\", \"state\", \"zipCode\", \"phoneNumber\", \"emailAddress\", \"Description\",\"userId\")"
					+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)")) {
				System.out.println(serv.getServiceName());
				serviceStatement.setString(1, serv.getServiceName());
				serviceStatement.setString(2, serv.getServiceType());
				serviceStatement.setString(3, serv.getCompanyName());
				serviceStatement.setString(4, serv.getAddress());
				serviceStatement.setString(5, serv.getAptSuiteOther().trim().isEmpty() ? null : serv.getAptSuiteOther());
				serviceStatement.setString(6, serv.getCity());
				serviceStatement.setString(7, serv.getState());
				serviceStatement.setString(8, serv.getZipCode());
				serviceStatement.setString(9, serv.getPhoneNumber());
				serviceStatement.setString(10, serv.getEmailAddress().trim().isEmpty() ? null : serv.getEmailAddress());
				serviceStatement.setString(11, serv.getDesc());
				serviceStatement.setInt(12, getLogin());
				serviceStatement.executeUpdate();
				connection.commit();
				
			}
		}
		return success;
	}
	
	
	
	public static int getServiceId(String username, Connection connection) throws SQLException {
		int userId = -1;
		try (PreparedStatement selectStatment = connection.prepareStatement(
				"SELECT service_id FROM service WHERE servicename = ?")) {
			selectStatment.setString(1, username);
			try (ResultSet rs = selectStatment.executeQuery()) {
				if (rs.next()) {
					userId = rs.getInt(1);
				}
			}
		}
		return userId;
	}
	
	public static boolean deleteUser(int serviceid) throws SQLException {
		boolean success = false;
		try (Connection connection = Database.getInstance().getConnection()) {
			connection.setAutoCommit(false);
			
		//	int userId = getUserId(username, connection);
//			
//			try (PreparedStatement userLoginStatment = connection.prepareStatement(
//					"DELETE FROM user_login WHERE user_id = ?")) {
//				userLoginStatment.setInt(1, userId);
				try (PreparedStatement userStatment = connection.prepareStatement(
						"DELETE FROM carematcher.service WHERE \"serviceId\" = ?")) {
					userStatment.setInt(1, serviceid);
					
					if (userStatment.executeUpdate() > 0) {
						connection.commit();
						success = true;
					}
				//}
			}
		}
		return success;
	}

	
	public static boolean deleteUserService(int serviceid) throws SQLException {
		boolean success = false;
		try (Connection connection = Database.getInstance().getConnection()) {
			connection.setAutoCommit(false);
			
				try (PreparedStatement userStatment = connection.prepareStatement(
						"DELETE FROM carematcher.\"UserServices\" WHERE \"serviceId\" = ?")) {
					userStatment.setInt(1, serviceid);
					
					if (userStatment.executeUpdate() > 0) {
						connection.commit();
						success = true;
					}

			}
		}
		return success;
	}

	
	
	
	public static boolean registerService(int serviceid) throws SQLException {
		boolean success = false;
		try (Connection connection = Database.getInstance().getConnection()) {
			connection.setAutoCommit(false);
			
				try (PreparedStatement userStatment = connection.prepareStatement(
						"INSERT INTO carematcher.\"UserServices\" "
								+ "SELECT \"serviceId\",\"serviceName\",\"serviceType\",\"companyName\",\"address\",\"aptSuiteOther\",\"city\",\"state\",\"zipCode\",\"phoneNumber\",\"emailAddress\",\"Description\",(select \"user_id\" from carematcher.login)" 

										+ " FROM carematcher.service WHERE \"serviceId\" = ?")) {
					userStatment.setInt(1, serviceid);
					
					if (userStatment.executeUpdate() > 0) {
						connection.commit();
						success = true;
					}
				//}
			}
		}
		System.out.println(success);
		return success;
	}
	
	
	//TODO add update method
	
	public static boolean credentialsAreValid(User user) throws SQLException {
		boolean success = false;
		try (Connection connection = Database.getInstance().getConnection()) {
			try (PreparedStatement statment = connection.prepareStatement(
					"SELECT password FROM carematcher.user_login WHERE username = ?")) {
				statment.setString(1, user.getUsername());
				try (ResultSet rs = statment.executeQuery()) {
					if (rs.next()) {
						String hashedPassword = rs.getString("password");
						success = BCrypt.checkpw(user.getPassword(), hashedPassword);
						
					}
				}
			}
		}
		return success;
	}
	
	public static Role getRole(String username) throws SQLException {
		Role role = null;
		try (Connection connection = Database.getInstance().getConnection()) {
			try (PreparedStatement statment = connection.prepareStatement(
					"SELECT role_id FROM carematcher.user_login WHERE username = ?")) {
				statment.setString(1, username);
				try (ResultSet rs = statment.executeQuery()) {
					if (rs.next()) {
						int roleId = rs.getInt("role_id");
						//System.out.println("I came here");
						role = Role.getRole(roleId);
					}
				}
			}
		}
		return role;
	}
	public static void login(String username) throws SQLException
	
	{
		try (Connection connection = Database.getInstance().getConnection()) {
			try (PreparedStatement statment = connection.prepareStatement(
					"INSERT INTO carematcher.\"login\" SELECT \"user_id\" from carematcher.user_login where \"username\"= ?")) {
				statment.setString(1, username);
				statment.executeUpdate();
				}
			}
		
	}
	public static int getLogin() throws SQLException {
		int user_id=0;
		try (Connection connection = Database.getInstance().getConnection()) {
			try (PreparedStatement statment = connection.prepareStatement(
					"SELECT user_id FROM carematcher.login")) {
				//statment.setString(1, username);//
				try (ResultSet rs = statment.executeQuery()) {
					if (rs.next()) {
						user_id = rs.getInt("user_id");
						//System.out.println("I came here");
						
					}
				}
			}
		}
		return user_id;
	}
}
