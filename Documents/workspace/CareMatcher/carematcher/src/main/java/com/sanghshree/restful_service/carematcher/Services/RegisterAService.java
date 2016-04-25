package com.sanghshree.restful_service.carematcher.Services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.shruthi.restful_service.carematcher.Connection.DB_Connection_Pool;

/**
 * This resource is used by service provider to register a new service in carematcher (exposed at "registerservice" path)
 */
@Path("/registerservice")

public class RegisterAService {


    /**
     * Method handling HTTP POST requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     * @throws SQLException 
     */
    @POST
    @Produces(MediaType.TEXT_PLAIN)    
    public String registerAService(@FormParam("Service_id") String Service_id,
    							  @FormParam("UserId") String UserId,
    							  @FormParam("Service_type") String Service_type,
    							  @FormParam("Company_name") String Company_name,
    							  @FormParam("Address") String Address,
    							  @FormParam("City") String City,
    							  @FormParam("State") String State,
    							  @FormParam("Zip_code") String Zip_code) throws SQLException
    {
    	DB_Connection_Pool dp= new DB_Connection_Pool();
    	Connection con=dp.sendConnection();
    	String query="INSERT INTO carematcher.Service_Provider(Service_id,UserId,Service_type,Company_name,Address,City,State,Zip_code) Values"
    			+ "("+Service_id+","+UserId+","+Service_type+","+Company_name+","+Address+","+City+","+State+","+Zip_code+")";
    	Statement stmt= con.createStatement();
    	ResultSet rs= stmt.executeQuery(query);
    	rs.close();
    	stmt.close();
    	con.close();    	
    	
    	return "Thank You! You have been successfully registered";
    }

}
