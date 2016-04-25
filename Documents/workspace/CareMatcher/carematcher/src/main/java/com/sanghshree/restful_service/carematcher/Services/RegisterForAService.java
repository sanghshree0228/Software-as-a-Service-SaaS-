package com.sanghshree.restful_service.carematcher.Services;
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
 * This resource is used by service seeker to register a new service in carematcher (exposed at "registerforservice" path)
 */
@Path("/registerforservice")

public class RegisterForAService {

    /**
     * Method handling HTTP POST requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     * @throws SQLException 
     */
	@POST
    @Produces(MediaType.TEXT_PLAIN)
    public String registerAService(@FormParam("User_Id") String User_Id,
    							  @FormParam("Service_id") String Service_id,
    							  @FormParam("Service_start_date") String Service_start_date,
    							  @FormParam("Service_end_date") String Service_end_date
    							  ) throws SQLException
    {
    	DB_Connection_Pool dp= new DB_Connection_Pool();
    	Connection con=dp.sendConnection();
    	String query="INSERT INTO carematcher.Service_Provider(User_Id,Service_id,Service_start_date,Service_end_date) Values"
    			+ "("+User_Id+","+Service_id+","+Service_start_date+","+Service_end_date+")";
    	Statement stmt= con.createStatement();
    	ResultSet rs= stmt.executeQuery(query);
    	rs.close();
    	stmt.close();
    	con.close();    	
    	
    	return "Thank You! You have been successfully registered";
    }
}


