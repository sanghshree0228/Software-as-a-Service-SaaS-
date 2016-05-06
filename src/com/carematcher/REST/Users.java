package com.carematcher.REST;
 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.mindrot.jbcrypt.BCrypt;
import org.postgresql.util.PSQLException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import com.carematcher.Database.UserDAO;
import com.carematcher.Model.Message;
import com.carematcher.Model.Request;
import com.carematcher.Model.Role;
import com.carematcher.Model.User;
import com.carematcher.Model.Service;
 
@Path("/Users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class Users {
	
	public Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create();
	
	/*service provider get services he provide & 
	 * seeker get all services available from this method*/
	
	@GET
	public Response getService(@Context HttpServletRequest req) {
		Response response = null;
		//System.out.println(req);
		//this one to differentiate service provider and service seeker
		if (userIsProvider(req)) {
			try {
				List<Service> service = UserDAO.getService();
				//System.out.println(service.size());
				String json = gson.toJson(service);
				//System.out.println(json);
				response = Response.status(Status.OK).entity(json).build();
			} catch (SQLException e) {
				e.printStackTrace();
				Message m = new Message("Database Error.");
				String json = gson.toJson(m);
				response = Response.status(Status.INTERNAL_SERVER_ERROR).entity(json).build();
			}
		} 
		else if(userIsSeeker(req)){
			try {
				List<Service> service = UserDAO.getAllService();
				System.out.println(service.size());
				String json = gson.toJson(service);
				//System.out.println(json);
				response = Response.status(Status.OK).entity(json).build();
				//System.out.println(response);
			} catch (SQLException e) {
				e.printStackTrace();
				Message m = new Message("Database Error.");
				String json = gson.toJson(m);
				response = Response.status(Status.INTERNAL_SERVER_ERROR).entity(json).build();
			}
		}
		
		else {
			Message m = new Message("Permission denied.");
			String json = gson.toJson(m);
			response = Response.status(Status.UNAUTHORIZED).entity(json).build();
		}
		return response;
	}
	
	/* This method returns services seeker registered in 
	 * and providers service requests */
	@GET
	@Path("/service")
	public Response getMyService(@Context HttpServletRequest req) {
		Response response = null;
		//System.out.println(req);
		//this one to differentiate service provider and service seeker
		if (userIsProvider(req)) {
			try {
				/*Working fine!*/
				
				List<Request> request = UserDAO.getRequests();
				//System.out.println(service.size());
				String json = gson.toJson(request);
				//System.out.println(json);
				response = Response.status(Status.OK).entity(json).build();
			} catch (SQLException e) {
				e.printStackTrace();
				Message m = new Message("Database Error.");
				String json = gson.toJson(m);
				response = Response.status(Status.INTERNAL_SERVER_ERROR).entity(json).build();
			}
		} 
		else if(userIsSeeker(req)){
			try {
				List<Service> service = UserDAO.getUserService();
				System.out.println(service.size());
				String json = gson.toJson(service);
				//System.out.println(json);
				response = Response.status(Status.OK).entity(json).build();
				//System.out.println(response);
			} catch (SQLException e) {
				e.printStackTrace();
				Message m = new Message("Database Error.");
				String json = gson.toJson(m);
				response = Response.status(Status.INTERNAL_SERVER_ERROR).entity(json).build();
			}
		}
		
		else {
			Message m = new Message("Permission denied.");
			String json = gson.toJson(m);
			response = Response.status(Status.UNAUTHORIZED).entity(json).build();
		}
		return response;
	}
	
	/*This didnot work so let it be for time being*/
	@GET
	@Path("/{param}")
	public Response getmyServices(@Context HttpServletRequest req, @PathParam("param") int userid) {
		Response response = null;
		try{
				Service service = UserDAO.getService(userid);
				System.out.println("I got services");
			if (service != null) {
				String json = gson.toJson(service);
				response = Response.status(Status.OK).entity(json).build();
			} else {
				Message m = new Message(" You do not have any registered services.");
				String json = gson.toJson(m);
				response = Response.status(Status.NOT_FOUND).entity(json).build();
			}
			} catch (SQLException e) {
				e.printStackTrace();
				Message m = new Message("Server Error.");
				String jsonResponse = gson.toJson(m);
				response = Response.status(Status.INTERNAL_SERVER_ERROR).entity(jsonResponse).build();
			}
		
		return response;
	}
	/*This is to register new users*/
	@PUT
	public Response createUser(@Context HttpServletRequest req) {
		Response response = null;
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
			String jsonInput = br.readLine();
			User user = gson.fromJson(jsonInput, User.class);
			user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
			//System.out.printf("My password is",user.getPassword());
			try {
				UserDAO.insertUser(user);
				Message m = new Message("User Created.");
				String json = gson.toJson(m);
				response = Response.status(Status.OK).entity(json).build();
			} catch (MySQLIntegrityConstraintViolationException e) {
				//e.printStackTrace();
				Message m = new Message("Invalid Username or other input.");
				String message = gson.toJson(m);
				response = Response.status(Status.CONFLICT).entity(message).build();
			} catch (SQLException e) {
				e.printStackTrace();
				Message m = new Message("Server Error.");
				String jsonResponse = gson.toJson(m);
				response = Response.status(Status.INTERNAL_SERVER_ERROR).entity(jsonResponse).build();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
			Message m = new Message("Invalid Input.");
			String json = gson.toJson(m);
			response = Response.status(Status.BAD_REQUEST).entity(json).build();
		}
		return response;
	}
	
	/*This method to create new service*/
	@PUT
	@Path("/service")
	public Response createService(@Context HttpServletRequest req) {
		Response response = null;
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
			String jsonInput = br.readLine();
			//System.out.println(jsonInput);
			Service service = gson.fromJson(jsonInput, Service.class);
			System.out.println(service);
			//System.out.printf("My password is",user.getPassword());
			try {
				UserDAO.insertService(service);
				Message m = new Message("Service Created.");
				String json = gson.toJson(m);
				response = Response.status(Status.OK).entity(json).build();
			}catch(org.postgresql.util.PSQLException e)
			{
				Message m = new Message("There something wrong please logout and try again!!");
				String message = gson.toJson(m);
				response = Response.status(Status.CONFLICT).entity(message).build();
			}
			catch (MySQLIntegrityConstraintViolationException e) {
				//e.printStackTrace();
				Message m = new Message("Invalid Username or other input.");
				String message = gson.toJson(m);
				response = Response.status(Status.CONFLICT).entity(message).build();
			} catch (SQLException e) {
				e.printStackTrace();
				Message m = new Message("Server Error.");
				String jsonResponse = gson.toJson(m);
				response = Response.status(Status.INTERNAL_SERVER_ERROR).entity(jsonResponse).build();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
			Message m = new Message("Invalid Input.");
			String json = gson.toJson(m);
			response = Response.status(Status.BAD_REQUEST).entity(json).build();
		}
		return response;
	}
	
	/*This method to delete service*/
	@DELETE
	@Path("/{param}")
	public Response deleteUser(@Context HttpServletRequest req, @PathParam("param") int serviceid) {
		Response response = null;
		if (userIsProvider(req)) {
			try {
				UserDAO.deleteUser(serviceid);
				Message m = new Message("User deleted.");
				String json = gson.toJson(m);
				response = Response.status(Status.OK).entity(json).build();
			} catch (SQLException e) {
				e.printStackTrace();
				Message m = new Message("Server Error.");
				String json = gson.toJson(m);
				response = Response.status(Status.INTERNAL_SERVER_ERROR).entity(json).build();
			}
		} 
		else if (userIsSeeker(req)){
			try{
			System.out.println("In delete user service");
			UserDAO.deleteUserService(serviceid);
			Message m = new Message("Service deregistered.");
			String json = gson.toJson(m);
			response = Response.status(Status.OK).entity(json).build();}
			catch (SQLException e) {
				e.printStackTrace();
				Message m = new Message("Server Error.");
				String json = gson.toJson(m);
				response = Response.status(Status.INTERNAL_SERVER_ERROR).entity(json).build();
			}
			
		}
		else {
			Message m = new Message("Permission denied.");
			String json = gson.toJson(m);
			response = Response.status(Status.UNAUTHORIZED).entity(json).build();
		}
		return response;
	}
	
	/*This method to deregister from a service*/
//	@DELETE
//	@Path("/service/{param}")
//	
//	public Response deleteUserService(@Context HttpServletRequest req, @PathParam("param") int serviceid) {
//		Response response = null;
//		try {
//				System.out.println("In delete user service");
//				UserDAO.deleteUserService(serviceid);
//				Message m = new Message("Service deregistered.");
//				String json = gson.toJson(m);
//				response = Response.status(Status.OK).entity(json).build();
//			} catch (SQLException e) {
//				e.printStackTrace();
//				Message m = new Message("Server Error.");
//				String json = gson.toJson(m);
//				response = Response.status(Status.INTERNAL_SERVER_ERROR).entity(json).build();
//			}
//		
//		return response;
//	}
//	
	
	/*This method to register in a service*/
	@PUT
	@Path("/{param}")
	public Response registerInService(@Context HttpServletRequest req, @PathParam("param") int serviceid) throws SQLException {
		Response response = null;
		
			try {
				UserDAO.registerService(serviceid);
				Message m = new Message("Service registered.");
				String json = gson.toJson(m);
				response = Response.status(Status.OK).entity(json).build();
			} catch (PSQLException e) {
				//e.printStackTrace();
				Message m = new Message("You have already registered for this service");
				String json = gson.toJson(m);
				response = Response.status(Status.OK).entity(json).build();
				
			}catch (SQLException e) {
				e.printStackTrace();
				Message m = new Message("Server Error.");
				String json = gson.toJson(m);
				response = Response.status(Status.INTERNAL_SERVER_ERROR).entity(json).build();
			}
		
		return response;
	}

	//TODO add update method
	/*This method to check if the user is service provider*/
	public boolean userIsProvider(HttpServletRequest req) {
		String username = (String)req.getSession().getAttribute("username");
		Role role = null;
		try {
			role = UserDAO.getRole(username);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(role);
		System.out.println(username);
		return role == Role.ServiceProvider;
	}
	/*This method to check if the user is service seeker*/
	public boolean userIsSeeker(HttpServletRequest req) {
		String username = (String)req.getSession().getAttribute("username");
		Role role = null;
		try {
			role = UserDAO.getRole(username);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//System.out.println(role);
		//System.out.println(username);
		return role == Role.ServiceSeeker;
	}
 
}
