package com.sanghshree.restful_service.carematcher.NewUsers;
import com.sanghshree.restful_service.carematcher.Connection.*;
import com.sanghshree.restful_service.carematcher.Model.*;
import java.sql.*;
import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.json.simple.JSONObject;
/**
 * This resource is used to register new user into carematcher (exposed at "registerusers" path)
 */
@Path("/getallusers")


public class ListAllUsers {
	/**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "JSON" media type.
     *
     * @return String that will be returned as a JSON response.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    
	public String all_users() throws SQLException
	{
		Connection con=null;
		Statement stmt=null;

		DB_Connection_Pool db= new DB_Connection_Pool();
		con= db.sendConnection();
		stmt= con.createStatement();
	    ResultSet rs = stmt.executeQuery( "SELECT * FROM carematcher.users" );
	    JSONObject obj = new JSONObject();
	    ArrayList<UserList> list=new ArrayList<UserList>();
	    UserList ul= null;
	     while ( rs.next() ) {
	    	 ul=new UserList();
	    	 ul.setFname(rs.getString("Fname"));
	    	 ul.setLname( rs.getString("Lname"));
	    	 ul.setEmail(rs.getString("Email"));
	    	 ul.setUsername(rs.getString("Username"));
	    	 ul.setPassword(rs.getString("Password"));
	    	 ul.setPhone(rs.getString("Phone"));
	    	 ul.setRegisteredAs( rs.getString("RegisteredAs"));
	         
	    	 list.add(ul);
	         //System.out.println(obj.toString()); 
	               
	}
	     rs.close();
	     stmt.close();
	     con.close();
	     obj.put("UserList", list);
	     System.out.println(obj.toString());
	     
	     return obj.toString();
}
	 public static void main(String args[]) throws SQLException     {
		 ListAllUsers lu= new ListAllUsers();
		 lu.all_users();
  }
}
