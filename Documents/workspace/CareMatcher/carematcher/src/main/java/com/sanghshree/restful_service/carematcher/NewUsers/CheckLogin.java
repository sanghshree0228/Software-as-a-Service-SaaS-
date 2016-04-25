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
 * This resource is used to authenticate new user of carematcher (exposed at "logincheck" path)
 */
@Path("/logincheck")


public class CheckLogin {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     * @throws SQLException 
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public boolean checkUser(@FormParam("Username") String Username,
			  				@FormParam("Password") String Password
    						) throws SQLException
    {
    	DB_Connection_Pool dp= new DB_Connection_Pool();
    	Connection con=dp.sendConnection();
    	String query="SELECT COUNT(*) FROM carematcher.Users WHERE Username="+Username+" AND Password="+Password+"";
    	Statement stmt= con.createStatement();
    	ResultSet rs= stmt.executeQuery(query);
    	boolean authentic=false; 
    	while(rs.next())
    	{
    		int count=rs.getInt(1);
    		if(count==1)
    		{
    			authentic=true;
    		}
    	}
    	rs.close();
    	stmt.close();
    	con.close();   
    	return authentic;
    	
    }
    

}
