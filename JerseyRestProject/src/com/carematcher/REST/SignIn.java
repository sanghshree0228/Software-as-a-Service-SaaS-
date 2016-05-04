package com.carematcher.REST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.sql.SQLException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.carematcher.database.UserDAO;
import com.carematcher.Model.Message;
import com.carematcher.Model.Role;
import com.carematcher.Model.User;

@Path("/SignIn")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SignIn {
	
	public Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create();

	@POST
	public Response login(@Context HttpServletRequest req, @Context HttpServletResponse res) throws IOException, SQLException, URISyntaxException {
		Response response = null;
		BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
		String jsonIn = br.readLine();
		User user = gson.fromJson(jsonIn, User.class);
		if (UserDAO.credentialsAreValid(user)) {
			req.getSession().setAttribute("username", user.getUsername());
			Cookie usernameCookie = new Cookie("username", user.getUsername());
			usernameCookie.setHttpOnly(false);
			usernameCookie.setPath(req.getContextPath() + "/");
			res.addCookie(usernameCookie);
			String username=user.getUsername();
			if (UserDAO.getRole(user.getUsername()) == Role.ServiceProvider) {
				
				Message m = new Message("Login Successful.", true, "./Admin.html", false);
				UserDAO.login(username);
				String jsonOut = gson.toJson(m);
				response = Response.status(Status.OK).type("application/json").entity(jsonOut).build();
			} else {
				Message m = new Message("Login Successful.", true, "User.html", false);
				String jsonOut = gson.toJson(m);
				UserDAO.login(username);
				response = Response.status(Status.OK).type("application/json").entity(jsonOut).build();
			}
		} else {
			Message m = new Message("Invalid Username / Password.");
			String jsonOut = gson.toJson(m);
			response = Response.status(Status.FORBIDDEN).entity(jsonOut).build();
		}
		return response;
	}
}
