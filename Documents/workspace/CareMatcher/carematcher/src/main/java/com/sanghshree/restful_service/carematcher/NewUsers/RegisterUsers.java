package com.sanghshree.restful_service.carematcher.NewUsers;
import com.sanghshree.restful_service.carematcher.Connection.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * This resource is used to register new user into carematcher (exposed at "registerusers" path)
 */
@Path("/registerusers")

public class RegisterUsers {

    /**
     * Method handling HTTP POST requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     * @throws SQLException 
     */
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String registeredUsers(@FormParam("Fname") String Fname,
    							  @FormParam("Lname") String Lname,
    							  @FormParam("Email") String Email,
    							  @FormParam("Phone") String Phone,
    							  @FormParam("Password") String Password,
    							  @FormParam("RegisteredAs") String RegisteredAs) throws SQLException
    {
    	DB_Connection_Pool dp= new DB_Connection_Pool();
    	Connection con=dp.sendConnection();
    	String query="INSERT INTO carematcher.Users(Fname,Lname,Email,Phone,Username,Password,RegisteredAs) Values"
    			+ "("+Fname+","+Lname+","+Email+","+Phone+","+Email+","+Password+","+RegisteredAs+")";
    	Statement stmt= con.createStatement();
    	ResultSet rs= stmt.executeQuery(query);
    	rs.close();
    	stmt.close();
    	con.close();    	
    	
    	return "Thank You! You have been successfully registered";
    }


}
